package de.hechler.patrick.zeugs.check.objects;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.DirectoryStream;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.jar.Attributes.Name;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import de.hechler.patrick.zeugs.check.anotations.CheckClass;
import de.hechler.patrick.zeugs.check.interfaces.TriConsumer;
import de.hechler.patrick.zeugs.check.interfaces.TwoValues;

/**
 * the {@link BigChecker} is used to check multiple classes.<br>
 * to generate a {@link BigChecker} the {@code generateBigChecker(...)} methods can be used. <br>
 * the two constructors also can be used to create {@link BigChecker} instances.
 * <p>
 * to run multiple checks without caring about the {@link BigChecker} object, the
 * {@code checkAll(...)} methods are recommended.
 * <p>
 * with the {@code tryGenerateBigChecker(...)} it is possible to read the checked classes from
 * packages.<br>
 * with the {@code tryCheckAll(...)} it is possible to check checked classes read from packages.<br>
 * because of the java {@link ClassLoader} nature the {@code try...} methods can not guarantee to
 * succeed.<br>
 * the {@code try...} methods try to find the source of the class loader and all its parents.<br>
 * then the {@code try...} methods try to search in the sources from the class loaders.
 * <p>
 * even if it is possible to subclass this class it can not affect the check process, because all
 * non {@code private} methods are marked either as {@code static} or {@code final}
 * (and {@code public}).
 * 
 * @author Patrick
 */
public class BigChecker implements Runnable, Supplier <BigCheckResult>, TriConsumer <TwoValues <Class <?>, Checker>, Map <String, Class <?>>, Map <Class <?>, CheckResult>> {
	
	/**
	 * the {@link Iterator} wich supplies this {@link BigChecker} with the classes and checkers wich
	 * should be checked.
	 */
	private final Iterator <TwoValues <Class <?>, Checker>> checkers;
	/**
	 * the maximum amount of checker threads wich should be created or {@code -1} for infinity.<br>
	 * when set to {@code 0} no checker threads will be created and all checks will be executed in the
	 * same thread.
	 */
	private volatile int maxCheckers;
	/**
	 * the current number of checker threads.
	 */
	private volatile int currentCheckers;
	/**
	 * the result of this {@link BigChecker}
	 */
	private BigCheckResult result;
	
	/**
	 * creates a new {@link BigChecker} from the given {@link Iterator}.<br>
	 * this constructor uses the current number of available Processors for the JVM
	 * ({@link Runtime#availableProcessors()}) as its {@link #maxCheckers}.
	 * 
	 * @param checkers
	 *            the {@link Iterator} wich supplies this big checker with it's classes and checkers
	 */
	public BigChecker(Iterator <TwoValues <Class <?>, Checker>> checkers) {
		this(checkers, Runtime.getRuntime().availableProcessors());
	}
	
	/**
	 * creates a new {@link BigChecker} from the given {@link Iterator} and the given
	 * {@code macCheckers}.
	 * 
	 * @param checkers
	 *            the {@link Iterator} wich supplies this big checker with it's classes and checkers
	 * @param maxCheckers
	 *            the maximum number of threads which should be created for the checker threads or
	 *            {@code -1} for infinity
	 */
	public BigChecker(Iterator <TwoValues <Class <?>, Checker>> checkers, int maxCheckers) {
		this.checkers = checkers;
		this.maxCheckers = maxCheckers < -1 ? 0 : maxCheckers;
		this.currentCheckers = 0;
		this.result = null;
	}
	
	
	/**
	 * returns the current maximal number of checker threads
	 * 
	 * @return
	 *             the current maximal number of checker threads
	 */
	public final int getMaxCheckers() { return maxCheckers; }
	
	/**
	 * sets the maximal value of checker threads
	 * 
	 * @param maxCheckers
	 *            the new maximal value of checker threads
	 * @throws IllegalArgumentException
	 *             if {@code maxCheckers} is smaller than {@code -1}
	 */
	public final void setMaxCheckers(int maxCheckers) throws IllegalArgumentException {
		if (maxCheckers < -1) {
			throw new IllegalArgumentException("max workers can eiter be -1, 0 or any other value greather than zero");
		}
		this.maxCheckers = maxCheckers;
	}
	
	/**
	 * returns <code>true</code> if this {@link BigChecker} has already been executed
	 * 
	 * @return
	 *             <code>true</code> if this {@link BigChecker} has already been executed
	 */
	public final boolean checkedAlready() {
		return this.result != null;
	}
	
	/**
	 * returns the {@link BigCheckResult} of this {@link BigChecker}.<br>
	 * if the {@link #result} has not yet been generated the {@link #run()} method is called
	 */
	@Override
	public final BigCheckResult get() {
		if (this.result == null) {
			run();
		}
		return this.result;
	}
	
	/**
	 * generates the {@link #result} of this {@link BigChecker}.
	 * <p>
	 * unlike the {@link Checker}, this method will directly return without doing anything, when the
	 * {@link #result} has already been generated.
	 * <p>
	 * this method will return after all class checks has been executed.
	 */
	@Override
	public final void run() {
		if (this.result != null) {
			return;
		}
		long start = System.currentTimeMillis();
		if ( !this.checkers.hasNext()) {
			this.result = new BigCheckResult(Collections.emptyMap(), Collections.emptyMap(), start, System.currentTimeMillis());
			return;
		}
		List <TwoValues <Class <?>, Checker>> singleThreadClasses = new ArrayList <>();
		Map <String, Class <?>> classes = new HashMap <>();
		Map <Class <?>, CheckResult> results = new HashMap <>();
		TwoValues <Class <?>, Checker> next = this.checkers.next();
		while (this.checkers.hasNext()) {
			CheckClass checkClass = next.getValueA().getAnnotation(CheckClass.class);
			if (checkClass == null || checkClass.singleThread()) {
				singleThreadClasses.add(next);
				continue;
			}
			if (this.maxCheckers == -1 || this.currentCheckers < this.maxCheckers) {
				final TwoValues <Class <?>, Checker> finalNext = next;
				new Thread(() -> {
					this.currentCheckers ++ ;
					try {
						accept(finalNext, classes, results);
					} finally {
						this.currentCheckers -- ;
					}
				}).start();
			} else if (this.maxCheckers == 0) {
				accept(next, classes, results);
			} else {
				while (this.maxCheckers <= this.currentCheckers && this.maxCheckers != -1) {
					try {
						synchronized (this) {
							wait(100);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			next = this.checkers.next();
		}
		while (this.currentCheckers > 0) {
			try {
				synchronized (this) {
					if (this.currentCheckers == 0) {
						break;
					}
					wait(100);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		singleThreadClasses.forEach(tv -> this.accept(tv, classes, results));
		long end = System.currentTimeMillis();
		this.result = new BigCheckResult(classes, results, start, end);
	}
	
	/**
	 * adds the {@code twoVals} to the {@code classes} and the {@code results} maps.
	 * 
	 * @param twoVals
	 *            the checked class and the result of the class check
	 * @param classes
	 *            the map containing the checked classes refereed by their names
	 * @param results
	 *            the map containing the results of the executed class checks refereed by the classes
	 */
	@Override
	public final void accept(TwoValues <Class <?>, Checker> twoVals, Map <String, Class <?>> classes, Map <Class <?>, CheckResult> results) {
		Class <?> cls = twoVals.getValueA();
		Checker checker = twoVals.getValueB();
		CheckResult res = checker.get();
		synchronized (this) {
			BigCheckResult.put(classes, results, cls, res);
			notifyAll();
		}
	}
	
	/**
	 * works like {@link #checkAll(boolean, Class...)}, but instead of an class array this class uses
	 * the full class names and the given {@link ClassLoader} to get the classes which should be
	 * checked.
	 * 
	 * @param needEnabedCheckClass
	 *            if only classes annotated with {@link CheckClass} should be checked
	 * @param classLoader
	 *            the loader used to load classes
	 * @param fullClassNames
	 *            an array containing all full class names from the classes which should be checked
	 * @return the result of all checks
	 */
	public static BigCheckResult checkAll(boolean needEnabedCheckClass, ClassLoader classLoader, String... fullClassNames) {
		Iterator <Class <?>> classIter = new ConvertingArrayIterator <String, Class <?>>(fullClassNames, str -> {
			try {
				return Class.forName(str, false, classLoader);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		});
		Iterator <TwoValues <Class <?>, Checker>> tvIter = new CheckerIterator(classIter);
		BigChecker bigChecker = new BigChecker(tvIter);
		return bigChecker.get();
	}
	
	/**
	 * works like {@link #checkAll(boolean, ClassLoader, String...)}, but uses to load classes the class
	 * loader of the {@link BigChecker} class.
	 * 
	 * @param needEnabedCheckClass
	 *            if only classes annotated with {@link CheckClass} should be checked
	 * @param classes
	 *            the array containing all classes which should be checked
	 * @return the result of all checks
	 * @see #checkAll(boolean, Iterator)
	 */
	public static BigCheckResult checkAll(boolean needEnabedCheckClass, String... fullClassNames) {
		ClassLoader classLoader = BigChecker.class.getClassLoader();
		Iterator <Class <?>> classIter = new ConvertingArrayIterator <String, Class <?>>(fullClassNames, str -> {
			try {
				return Class.forName(str, false, classLoader);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		});
		Iterator <TwoValues <Class <?>, Checker>> tvIter = new CheckerIterator(classIter);
		BigChecker bigChecker = new BigChecker(tvIter);
		return bigChecker.get();
	}
	
	/**
	 * works like {@link #checkAll(boolean, Iterator)}, but uses instead of an {@link Iterator} an
	 * {@link Class} array.
	 * 
	 * @param needEnabedCheckClass
	 *            if only classes annotated with {@link CheckClass} should be checked
	 * @param classes
	 *            the array containing all classes which should be checked
	 * @return the result of all checks
	 * @see #checkAll(boolean, Iterator)
	 */
	public static BigCheckResult checkAll(boolean needEnabedCheckClass, Class <?>... classes) {
		Iterator <TwoValues <Class <?>, Checker>> tvIter = new ConvertingArrayIterator <>(classes, cls -> new TwoValuesImpl <>(cls, Checker.generateChecker(cls)));
		BigChecker bigChecker = new BigChecker(tvIter);
		return bigChecker.get();
	}
	
	/**
	 * checks all classes from the iterator.<br>
	 * if {@code needEnabledCheckClass} is <code>true</code> only classes which are annotated with
	 * {@link CheckClass} and are not {@link CheckClass#disabled()} are checked.
	 * 
	 * @param needEnabedCheckClass
	 *            if only classes annotated with {@link CheckClass} should be checked
	 * @param iter
	 *            the {@link Iterator} which iterates over all {@link Class} objects to check
	 * @return the result of all executed checks
	 */
	public static BigCheckResult checkAll(boolean needEnabedCheckClass, Iterator <Class <?>> iter) {
		Iterator <TwoValues <Class <?>, Checker>> tvIter = new CheckerIterator(iter);
		BigChecker bigChecker = new BigChecker(tvIter);
		return bigChecker.get();
	}
	
	/**
	 * this will generate a {@link BigChecker} ready to check the given {@code classes}.
	 * <p>
	 * if {@code needCheckClass} is <code>true</code> only classes annotated with {@link CheckClass}
	 * will be given to the {@link BigChecker}.<br>
	 * if {@code needCheckClass} is <code>false</code> all classes in the array will be given to the
	 * {@link BigChecker}.
	 * 
	 * @param needCheckClass
	 *            if the given classes need to be annotated with {@link CheckClass} and not be
	 *            {@link CheckClass#disabled()}.
	 * @param classes
	 *            the array containing the classes to check
	 * @return the generated {@link BigChecker}
	 */
	public static BigChecker generateBigChecker(boolean needCheckClass, Class <?>... classes) {
		Iterator <TwoValues <Class <?>, Checker>> iter = generateIterator(needCheckClass, classes);
		return new BigChecker(iter);
	}
	
	/**
	 * this will generate a {@link BigChecker} ready to check the given {@code classes}.
	 * <p>
	 * if {@code needCheckClass} is <code>true</code> only classes annotated with {@link CheckClass}
	 * will be given to the {@link BigChecker}.<br>
	 * if {@code needCheckClass} is <code>false</code> all classes in the array will be given to the
	 * {@link BigChecker}.
	 * <p>
	 * {@code maxWorkers} defines how much threads should be started for the checkers to execute the
	 * checks.<br>
	 * every checker will run in a own thread and not in multiple threads, so no checker has to care for
	 * the Multithreading.<br>
	 * if {@code maxWorters} is {@code 0} no threads will be created and all checks are executed in the
	 * calling threads of the {@link BigChecker#run()}.<br>
	 * if {@code maxWorters} is {@code -1} the maximum amount of workers will be infinity.<br>
	 * if {@code maxWorkers} is a negative value smaller than {@code -1} an
	 * {@link IllegalArgumentException} will be thrown.
	 * 
	 * @param needCheckClass
	 *            if the given classes need to be annotated with {@link CheckClass} and not be
	 *            {@link CheckClass#disabled()}.
	 * @param maxWorkers
	 *            the maximum amount of worker running threads, {@code -1} for infinity or {@code 0}
	 *            when all checks should be executed in the same thread.
	 * @param classes
	 *            the array containing the classes to check
	 * @return the generated {@link BigChecker}
	 */
	public static BigChecker generateBigChecker(boolean needCheckClass, int maxWorkers, Class <?>... classes) {
		if (maxWorkers < -1) {
			throw new IllegalArgumentException("max workers < -1 maxWorkers=" + maxWorkers);
		}
		Iterator <TwoValues <Class <?>, Checker>> iter = generateIterator(needCheckClass, classes);
		return new BigChecker(iter, maxWorkers);
	}
	
	/**
	 * this will generate a {@link BigChecker} ready to check the given {@code classes}.
	 * <p>
	 * if {@code needCheckClass} is <code>true</code> only classes annotated with {@link CheckClass}
	 * will be given to the {@link BigChecker}.<br>
	 * if {@code needCheckClass} is <code>false</code> all classes in the array will be given to the
	 * {@link BigChecker}.
	 * 
	 * @param needCheckClass
	 *            if the given classes need to be annotated with {@link CheckClass} and not be
	 *            {@link CheckClass#disabled()}.
	 * @param classes
	 *            the iterator containing the classes to check
	 * @return the generated {@link BigChecker}
	 */
	public static BigChecker generateBigChecker(boolean needCheckClass, Iterator <Class <?>> classes) {
		Iterator <TwoValues <Class <?>, Checker>> iter = getIterator(needCheckClass, classes);
		return new BigChecker(iter);
	}
	
	/**
	 * this will generate a {@link BigChecker} ready to check the given {@code classes}.
	 * <p>
	 * if {@code needCheckClass} is <code>true</code> only classes annotated with {@link CheckClass}
	 * will be given to the {@link BigChecker}.<br>
	 * if {@code needCheckClass} is <code>false</code> all classes in the array will be given to the
	 * {@link BigChecker}.
	 * <p>
	 * {@code maxWorkers} defines how much threads should be started for the checkers to execute the
	 * checks.<br>
	 * every checker will run in a own thread and not in multiple threads, so no checker has to care for
	 * the Multithreading.<br>
	 * if {@code maxWorters} is {@code 0} no threads will be created and all checks are executed in the
	 * calling threads of the {@link BigChecker#run()}.<br>
	 * if {@code maxWorters} is {@code -1} the maximum amount of workers will be infinity.<br>
	 * if {@code maxWorkers} is a negative value smaller than {@code -1} an
	 * {@link IllegalArgumentException} will be thrown.
	 * 
	 * @param needCheckClass
	 *            if the given classes need to be annotated with {@link CheckClass} and not be
	 *            {@link CheckClass#disabled()}.
	 * @param maxWorkers
	 *            the maximum amount of worker running threads, {@code -1} for infinity or {@code 0}
	 *            when all checks should be executed in the same thread.
	 * @param classes
	 *            the iterator containing the classes to check
	 * @return the generated {@link BigChecker}
	 */
	public static BigChecker generateBigChecker(boolean needCheckClass, int maxWorkers, Iterator <Class <?>> classes) {
		Iterator <TwoValues <Class <?>, Checker>> iter = getIterator(needCheckClass, classes);
		return new BigChecker(iter, maxWorkers);
	}
	
	private static Iterator <TwoValues <Class <?>, Checker>> generateIterator(boolean needCheckClass, Class <?>... classes) {
		if (needCheckClass) {
			return new CheckerIterator(new ArrayIterator <>(classes));
		} else {
			return new ConvertingArrayIterator <>(classes, cls -> new TwoValuesImpl <>(cls, Checker.generateChecker(cls)));
		}
	}
	
	private static Iterator <TwoValues <Class <?>, Checker>> getIterator(boolean needCheckClass, Iterator <Class <?>> classes) {
		if (needCheckClass) {
			return new CheckerIterator(classes);
		} else {
			return new Iterator <TwoValues <Class <?>, Checker>>() {
				
				@Override
				public boolean hasNext() {
					return classes.hasNext();
				}
				
				@Override
				public TwoValues <Class <?>, Checker> next() {
					Class <?> cls = classes.next();
					Checker checker = Checker.generateChecker(cls);
					return new TwoValuesImpl <Class <?>, Checker>(cls, checker);
				}
				
			};
		}
	}
	
	/**
	 * like {@link #tryCheckAll(boolean, String, ClassLoader)} with
	 * {@code tryCheckAll(subPackages, package.getName(), Checker.class.getClassLoader())}.
	 * 
	 * @param subPackages
	 *            <code>true</code> if also classes in subPackages should be loaded
	 * @param pakage
	 *            the package in which the classes should be searched
	 * @param loader
	 *            the loader used to find/load classes
	 * @see #tryCheckAll(boolean, String, ClassLoader)
	 */
	public static BigCheckResult tryCheckAll(boolean subPackages, Package pakage) {
		return tryCheckAll(subPackages, pakage.getName(), Checker.class.getClassLoader());
	}
	
	/**
	 * like {@link #tryCheckAll(boolean, String, ClassLoader)} with
	 * {@code tryCheckAll(subPackages, package, Checker.class.getClassLoader())}.
	 * 
	 * @param subPackages
	 *            <code>true</code> if also classes in subPackages should be loaded
	 * @param pakage
	 *            the name of the package (like {@link Package#getName()}) in which the classes should
	 *            be searched
	 * @param loader
	 *            the loader used to find/load classes
	 * @see #tryCheckAll(boolean, String, ClassLoader)
	 */
	public static BigCheckResult tryCheckAll(boolean subPackages, String pakage) {
		return tryCheckAll(subPackages, pakage, Checker.class.getClassLoader());
	}
	
	/**
	 * like {@link #tryCheckAll(boolean, String, ClassLoader)} with
	 * {@code tryCheckAll(subPackages, package.getName(), loader)}.
	 * 
	 * @param subPackages
	 *            <code>true</code> if also classes in subPackages should be loaded
	 * @param pakage
	 *            the package in which the classes should be searched
	 * @param loader
	 *            the loader used to find/load classes
	 * @see #tryCheckAll(boolean, String, ClassLoader)
	 */
	public static BigCheckResult tryCheckAll(boolean subPackages, Package pakage, ClassLoader loader) {
		return tryCheckAll(subPackages, pakage.getName(), loader);
	}
	
	/**
	 * like <code>{@link #tryGenerateBigChecker(boolean, String, ClassLoader)}.{@link #get()}</code>.
	 * 
	 * @param subPackages
	 *            <code>true</code> if also classes in subPackages should be loaded
	 * @param pakage
	 *            the name of the package (like {@link Package#getName()}) in which the classes should
	 *            be searched
	 * @param loader
	 *            the loader used to find/load classes
	 * @see #tryCheckAll(boolean, String, ClassLoader)
	 */
	public static BigCheckResult tryCheckAll(boolean subPackages, String pakage, ClassLoader loader) {
		return tryGenerateBigChecker(subPackages, pakage, loader, false).get();
	}
	
	/**
	 * like {@link #tryGenerateBigChecker(boolean, String, ClassLoader)} with
	 * {@code tryGetClassesForPackage(subPackages, package.getName(), BigChecker.class.getClassLoader())}.
	 * 
	 * @param subPackages
	 *            <code>true</code> if also classes in subPackages should be loaded
	 * @param pakage
	 *            the package in which the classes should be searched
	 * @param loader
	 *            the loader used to find/load classes
	 * @see #tryGenerateBigChecker(boolean, String, ClassLoader, boolean)
	 */
	public static BigChecker tryGenerateBigChecker(boolean subPackages, Package pakage) {
		return tryGenerateBigChecker(subPackages, pakage.getName(), BigChecker.class.getClassLoader(), false);
	}
	
	/**
	 * like {@link #tryGenerateBigChecker(boolean, String, ClassLoader)} with
	 * {@code tryGetClassesForPackage(subPackages, package, BigChecker.class.getClassLoader())}.
	 * 
	 * @param subPackages
	 *            <code>true</code> if also classes in subPackages should be loaded
	 * @param pakage
	 *            the name of the package (like {@link Package#getName()}) in which the classes should
	 *            be searched
	 * @param loader
	 *            the loader used to find/load classes
	 * @see #tryGenerateBigChecker(boolean, String, ClassLoader, boolean)
	 */
	public static BigChecker tryGenerateBigChecker(boolean subPackages, String pakage) {
		return tryGenerateBigChecker(subPackages, pakage, BigChecker.class.getClassLoader(), false);
	}
	
	/**
	 * like {@link #tryGenerateBigChecker(boolean, String, ClassLoader, boolean)} with
	 * {@code tryGetClassesForPackage(subPackages, package.getName(), loader, true)}.
	 * 
	 * @param subPackages
	 *            <code>true</code> if also classes in subPackages should be loaded
	 * @param pakage
	 *            the package in which the classes should be searched
	 * @param loader
	 *            the loader used to find/load classes
	 * @see #tryGenerateBigChecker(boolean, String, ClassLoader, boolean)
	 */
	public static BigChecker tryGenerateBigChecker(boolean subPackages, Package pakage, ClassLoader loader) {
		return tryGenerateBigChecker(subPackages, pakage.getName(), loader, false);
	}
	
	/**
	 * like {@link #tryGenerateBigChecker(String, boolean, ClassLoader, boolean)} with
	 * {@code tryGetClassesForPackage(subPackages, package, loader, true)}.
	 * 
	 * @param subPackages
	 *            <code>true</code> if also classes in subPackages should be loaded
	 * @param pakage
	 *            the name of the package (like {@link Package#getName()}) in which the classes should
	 *            be searched
	 * @param loader
	 *            the loader used to find/load classes
	 * @see #tryGenerateBigChecker(boolean, String, ClassLoader, boolean)
	 */
	public static BigChecker tryGenerateBigChecker(boolean subPackages, String pakage, ClassLoader loader) {
		return tryGenerateBigChecker(subPackages, pakage, loader, false);
	}
	
	/**
	 * tries to check all classes from the given package.<br>
	 * if {@code subPackages} is <code>true</code> this method also tries to check the classes in
	 * supPackages.
	 * <p>
	 * a class is in a package, when the {@link Class#forName(String, boolean, ClassLoader)} method
	 * finds the class, when the name starts with {@code pakage + "." + restName}.<br>
	 * if {@code subPackages} is <code>false</code> {@code restName} is not allowed to contain a
	 * {@code '.'}.
	 * <p>
	 * if {@code bailError} is <code>true</code>, errors on loading/finding the classes will be
	 * re-thrown.<br>
	 * if {@code bailError} is <code>false</code>, errors on loading/finding the classes will be
	 * suppressed.
	 * <p>
	 * this method checks only classes wich are annotated with {@link CheckClass} and are not
	 * ({@link CheckClass#disabled()}).
	 * <p>
	 * this method can not guarantee to find any classes in the given package or any sub-package!
	 * 
	 * @param subPackages
	 *            <code>true</code> if also classes in subPackages should be loaded
	 * @param pakage
	 *            the name of the package (like {@link Package#getName()})
	 * @param loader
	 *            the loader used to find/load classes
	 * @param bailError
	 *            if errors on loading/finding classes should be re-thrown (<code>true</code>) or
	 *            suppressed (<code>false</code>)
	 * @return the result of all executed checks
	 */
	public static BigChecker tryGenerateBigChecker(boolean subPackages, String pakage, ClassLoader loader, boolean bailError) {
		Set <Class <?>> classes = tryGetClassesForPackage(pakage, subPackages, loader, bailError);
		CheckerIterator iter = new CheckerIterator(classes.iterator());
		return new BigChecker(iter);
	}
	
	/**
	 * orig description:<br>
	 * Scans all classloaders for the current thread for loaded jars, and then scans
	 * each jar for the package name in question, listing all classes directly under
	 * the package name in question. Assumes directory structure in jar file and class
	 * package naming follow java conventions (i.e. com.example.test.MyTest would be in
	 * /com/example/test/MyTest.class)
	 * <p>
	 * in addition this method also scans for directories, where also is assumed, that the classes are
	 * placed followed by the java conventions. (i.e. <code>com.example.test.MyTest</code> would be in
	 * <code>directory/com/example/test/MyTest.class</code>)
	 * <p>
	 * this method also reads the jars Class-Path for other jars and directories. for the jars and
	 * directories referred in the jars are scanned with the same rules as defined here.<br>
	 * it is ensured that no jar/directory is scanned exactly one time.
	 * <p>
	 * if {@code bailError} is <code>true</code> all errors will be wrapped in a
	 * {@link RuntimeException}
	 * and then thrown.<br>
	 * a {@link RuntimeException} will also be thrown if something unexpected happens.<br>
	 * 
	 * @param packageName
	 *            the name of the package for which the classes should be searched
	 * @param allowSubPackages
	 *            <code>true</code> is also classes in sub packages should be found
	 * @param loader
	 *            the {@link ClassLoader} which should be used to find the URLs and to load classes
	 * @param bailError
	 *            if all {@link Exception} should be re-thrown wrapped in {@link RuntimeException} and
	 *            if a {@link RuntimeException} should be thrown, when something is not as expected.
	 * @see https://stackoverflow.com/questions/1156552/java-package-introspection
	 * @see https://stackoverflow.com/a/1157352/18252455
	 * @see https://creativecommons.org/licenses/by-sa/2.5/
	 * @see https://creativecommons.org/licenses/by-sa/2.5/legalcode
	 */
	public static Set <Class <?>> tryGetClassesForPackage(String packageName, boolean allowSubPackages, ClassLoader loader, boolean bailError) {
		Set <URL> jarUrls = new HashSet <URL>();
		Set <Path> directorys = new HashSet <Path>();
		findClassPools(loader, jarUrls, directorys, bailError);
		Set <Class <?>> jarClasses = findJarClasses(allowSubPackages, packageName, jarUrls, directorys, loader, bailError);
		Set <Class <?>> dirClasses = findDirClasses(allowSubPackages, packageName, directorys, loader, bailError);
		jarClasses.addAll(dirClasses);
		return jarClasses;
	}
	
	private static Set <Class <?>> findDirClasses(boolean subPackages, String packageName, Set <Path> directorys, ClassLoader loader, boolean bailError) {
		Filter <Path> filter;
		Set <Class <?>> result = new HashSet <>();
		for (Path startPath : directorys) {
			String packagePath = packageName.replace(".", startPath.getFileSystem().getSeparator());
			final Path searchPath = startPath.resolve(packagePath).toAbsolutePath();
			if (subPackages) {
				filter = p -> {
					p = p.toAbsolutePath();
					Path other;
					if (p.getNameCount() >= searchPath.getNameCount()) {
						other = searchPath;
					} else {
						other = searchPath.subpath(0, p.getNameCount());
					}
					if (p.startsWith(other)) {
						return true;
					} else {
						return false;
					}
				};
			} else {
				filter = p -> {
					p = p.toAbsolutePath();
					if (p.getNameCount() > searchPath.getNameCount() + 1) {
						return false;
					} else if (p.toAbsolutePath().startsWith(searchPath)) {
						return true;
					} else {
						return false;
					}
				};
			}
			if (Files.exists(searchPath)) {
				findDirClassFilesRecursive(filter, searchPath, startPath, result, loader, bailError);
			} // the package does not have to exist in every directory
		}
		return result;
	}
	
	private static void findDirClassFilesRecursive(Filter <Path> filter, Path path, Path start, Set <Class <?>> classes, ClassLoader loader, boolean bailError) {
		try (DirectoryStream <Path> dirStream = Files.newDirectoryStream(path, filter)) {
			for (Path p : dirStream) {
				if (Files.isDirectory(p)) {
					findDirClassFilesRecursive(filter, p, start, classes, loader, bailError);
				} else {
					Path subp = p.subpath(start.getNameCount(), p.getNameCount());
					String str = subp.toString();
					if (str.endsWith(".class")) {
						str = str.substring(0, str.length() - 6);
						String sep = p.getFileSystem().getSeparator();
						if (str.startsWith(sep)) {
							str = str.substring(sep.length());
						}
						if (str.endsWith(sep)) {
							str = str.substring(0, str.length() - sep.length());
						}
						String fullClassName = str.replace(sep, ".");
						try {
							Class <?> cls = Class.forName(fullClassName, false, loader);
							classes.add(cls);
						} catch (ClassNotFoundException e) {
							if (bailError) {
								throw new RuntimeException(e);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			if (bailError) {
				throw new RuntimeException(e);
			}
		}
	}
	
	private static Set <Class <?>> findJarClasses(boolean subPackages, String packageName, Set <URL> nextJarUrls, Set <Path> directories, ClassLoader loader, boolean bailError) {
		String packagePath = packageName.replace('.', '/');
		Set <Class <?>> result = new HashSet <>();
		Set <URL> allJarUrls = new HashSet <>();
		while (true) {
			Set <URL> thisJarUrls = new HashSet <>(nextJarUrls);
			thisJarUrls.removeAll(allJarUrls);
			if (thisJarUrls.isEmpty()) {
				break;
			}
			allJarUrls.addAll(thisJarUrls);
			for (URL url : thisJarUrls) {
				try (JarInputStream stream = new JarInputStream(url.openStream())) {
					// may want better way to open url connections
					readJarClassPath(stream, nextJarUrls, directories, bailError);
					JarEntry entry = stream.getNextJarEntry();
					while (entry != null) {
						String name = entry.getName();
						int i = name.lastIndexOf("/");
						
						if (i > 0 && name.endsWith(".class")) {
							try {
								if (subPackages) {
									if (name.substring(0, i).startsWith(packagePath)) {
										result.add(Class.forName(name.substring(0, name.length() - 6).replace("/", "."), false, loader));
									}
								} else {
									if (name.substring(0, i).equals(packagePath)) {
										result.add(Class.forName(name.substring(0, name.length() - 6).replace("/", "."), false, loader));
									}
								}
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
						entry = stream.getNextJarEntry();
					}
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	private static void readJarClassPath(JarInputStream stream, Set <URL> jarUrls, Set <Path> directories, boolean bailError) {
		Object classPathObj = stream.getManifest().getMainAttributes().get(new Name("Class-Path"));
		if (classPathObj == null) {
			return;
		}
		if (classPathObj instanceof String) {
			String[] entries = ((String) classPathObj).split("\\s+");// should also work with a single space (" ")
			for (String entry : entries) {
				try {
					URL url = new URL(entry);
					addFromUrl(jarUrls, directories, url, bailError);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		} else if (bailError) {
			throw new RuntimeException("the Class-Path attribute is no String: " + classPathObj.getClass().getName() + " tos='" + classPathObj + "'");
		}
	}
	
	private static void findClassPools(ClassLoader classLoader, Set <URL> jarUrls, Set <Path> directoryPaths, boolean bailError) {
		while (classLoader != null) {
			if (classLoader instanceof URLClassLoader) {
				for (URL url : ((URLClassLoader) classLoader).getURLs()) {
					addFromUrl(jarUrls, directoryPaths, url, bailError);
				}
			} else if (bailError) {
				throw new RuntimeException("unknown class loader: " + classLoader.getClass() + " : " + classLoader);
			}
			classLoader = classLoader.getParent();
		}
	}
	
	private static void addFromUrl(Set <URL> jarUrls, Set <Path> directoryPaths, URL url, boolean bailError) {
		if (url.getFile().endsWith(".jar") || url.getFile().endsWith(".zip")) {
			// may want better way to detect jar files
			jarUrls.add(url);
		} else {
			try {
				Path path = Paths.get(url.toURI());
				if (Files.exists(path) && Files.isDirectory(path)) {
					directoryPaths.add(path);
				} else if (bailError) {
					throw new RuntimeException("unknown url for class loading: " + url);
				}
			} catch (URISyntaxException e) {
				if (bailError) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
}

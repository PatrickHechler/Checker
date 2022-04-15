package de.hechler.patrick.zeugs.check.objects;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.jar.Attributes.Name;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;
import de.hechler.patrick.zeugs.check.anotations.End;
import de.hechler.patrick.zeugs.check.anotations.MethodParam;
import de.hechler.patrick.zeugs.check.anotations.ParamCreater;
import de.hechler.patrick.zeugs.check.anotations.ResultParam;
import de.hechler.patrick.zeugs.check.anotations.Start;

public class Checker implements Runnable {
	
	static {
		Checker.class.getClassLoader().setDefaultAssertionStatus(true);
	}
	
	/**
	 * the instance used to call methods.<br>
	 * {@link #Checker(Object)}
	 * 
	 * @see #Checker()
	 * @see #Checker(Object)
	 */
	private final Object instance;
	
	/**
	 * the {@link List} saving all methods which should be executed before any checks are executed
	 */
	private List <Method> init = null;
	/**
	 * the {@link List} saving all methods which should be executed every time before the execution of a
	 * check starts
	 */
	private List <Method> start = null;
	/**
	 * 
	 */
	private List <Method> end = null;
	private List <Method> finalize = null;
	private List <Method> check = null;
	
	/**
	 * saves the result of this checker or <code>null</code> if the checks has not been executed.<br>
	 * to execute the checks the {@link #run()} method is used.<br>
	 * if {@link #result()} is called before the checks has been executed, the {@link #run()} method
	 * will be automatically be executed.
	 * 
	 * @see #result()
	 * @see #run()
	 */
	private CheckResult result;
	
	
	
	/**
	 * creates a new {@link Checker}.<br>
	 * this {@link Checker} will use itself to call methods: {@link Method#invoke(Object, Object...)
	 * method.invoke(this, params);}
	 */
	public Checker() {
		instance = this;
	}
	
	/**
	 * creates a {@link Checker} with the specified <code>instance</code>.<br>
	 * This {@code instance} will be used to call methods.
	 * <p>
	 * only methods, which can be called with the given {@code instance} can be called.<br>
	 * if {@code instance} is <code>null</code> only static methods can be called.<br>
	 * (static methods can always be called)
	 * <p>
	 * if the {@code instance} is <code>null</code>, the {@link #load(Class)} method has to be called
	 * before the {@link #run()} (or {@link #result()}) method gets invoked.<br>
	 * if they are invoked before the {@link #load(Class)} method was called and the {@link #instance}
	 * is <code>null</code> they will throw an {@link IllegalStateException}.
	 * 
	 * @param instance
	 *            the {@code instance} used by this {@link Checker}
	 * @see {@link Method#invoke(Object, Object...) method.invoke(instance, params);}
	 */
	public Checker(Object instance) {
		this.instance = instance;
	}
	
	/**
	 * returns <code>true</code> if the checks has already been executed and <code>false</code> if the
	 * checker still needs to execute the checks to generate the {@link #result} of this checker.
	 * 
	 * @return
	 *             returns <code>true</code> if the checks has already been executed and
	 *             <code>false</code> if not
	 */
	public final boolean checkedAlready() {
		return result == null;
	}
	
	/**
	 * returns the {@link #result} of this checker.<br>
	 * if the {@link #result} has not yet been generated the {@link #run()} method is used to generate
	 * the {@link #result}.
	 * 
	 * @return the {@link #result} of this checker
	 */
	public final CheckResult result() {
		if (result == null) {
			run();
		}
		return result;
	}
	
	/**
	 * executes all startup methods (methods annotated with {@link Start}, where
	 * {@link Start#onlyOnce()} is <code>true</code>)<br>
	 * then all checks are executed.<br>
	 * before each check method all {@link Start} methods with {@link Start#onlyOnce()} set to
	 * <code>false</code> are executed.<br>
	 * then the check current is executed (some method annotated with {@link Check}).<br>
	 * after the check all {@link End} methods with {@link End#onlyOnce()} set to <code>false</code> are
	 * executed.<br>
	 * after the execution of all {@link Check} methods the {@link End} methods annotated with
	 * {@link End#onlyOnce()} set to <code>true</code> are executed.
	 * <p>
	 * if this method is run multiple times the checks are executed multiple times.<br>
	 * to check if the checks already has been executed use {@link #checkedAlready()}.<br>
	 * this method is automatically invoked, when {@link #result()} is invoked and the checks have not
	 * been executed.
	 * <p>
	 * this method will not create new {@link Thread} objects, so all checks/starts/ends are executed
	 * with the {@link Thread} executing this method.<br>
	 * this also means the checked class can use its member variables without caring about the other
	 * checks.
	 * <p>
	 * this method throws a IllegalStateException if this checker has not been loaded
	 * ({@link #load(Class)}) and was created with a null {@link #instance} (using the
	 * {@link #Checker(Object)}
	 * 
	 * @throws IllegalStateException
	 *             if this checker has not been loaded ({@link #load(Class)}) and was created with a
	 *             <code>null</code> {@link #instance} (using the {@link #Checker(Object)} constructor
	 *             with a <code>null</code> argument)
	 */
	@Override
	public final void run() throws IllegalStateException {
		if (this.init == null) {
			if (this.instance == null) {
				throw new IllegalStateException("this checker is not loaded and I have a null instance.");
			}
			load(this.instance.getClass());
		}
		this.result = new CheckResult();
		this.init.forEach(m -> run(m, this.instance, null, null));
		this.check.forEach(m -> {
			this.start.forEach(r -> run(r, this.instance, null, m));
			Result res = run(m, this.instance, null, null);
			this.result.set(m, res);
			this.end.forEach(r -> run(r, this.instance, res, m));
		});
		this.finalize.forEach(m -> run(m, this.instance, null, null));
		this.result.setEnd(System.currentTimeMillis());
	}
	
	
	private static Result run(final Method m, Object invoker, Result checked, Method notRun) {
		Result result;
		long start = System.currentTimeMillis();
		try {
			Parameter[] params = m.getParameters();
			Object[] ps = new Object[params.length];
			for (int i = 0; i < params.length; i ++ ) {
				Parameter param = params[i];
				ps[i] = getParam(invoker, checked, notRun, param, m.getDeclaringClass());
			}
			boolean flag = m.isAccessible();
			m.setAccessible(true);
			start = System.currentTimeMillis();
			Object res = m.invoke(invoker, ps);
			long end = System.currentTimeMillis();
			m.setAccessible(flag);
			result = new Result(res, start, end);
			// } catch (IllegalAccessException | IllegalArgumentException e) {
			// throw new AssertionError("can't run method: '" + m.getName() + "' params: " +
			// m.getParameterCount() + " : " + Arrays.deepToString(m.getParameterTypes())
			// + " ||| my params: {" + Arrays.deepToString(ps) + "} error message: " + e.getMessage(), e);
		} catch (InvocationTargetException | NullPointerException | IllegalAccessException | IllegalArgumentException | AssertionError e) {
			long end = System.currentTimeMillis();
			Throwable err;
			if (e instanceof InvocationTargetException) {
				err = e.getCause();
			} else {
				err = e;
			}
			if (err instanceof ThreadDeath) {
				throw (ThreadDeath) err;
			}
			result = new Result(err, start, end);
		}
		return result;
	}
	
	private static Object getParam(Object invoker, Result checked, Method notRun, Parameter param, Class <?> cls) throws AssertionError, ClassCastException {
		ParamCreater pc = param.getAnnotation(ParamCreater.class);
		MethodParam mp = param.getAnnotation(MethodParam.class);
		ResultParam rp = param.getAnnotation(ResultParam.class);
		Class <?> type = param.getType();
		if (pc != null && !pc.disabled()) {
			String method = pc.method();
			String[] paramClassNames = pc.methodParams();
			Class <?>[] paramClasses = new Class[paramClassNames.length];
			for (int i = 0; i < paramClasses.length; i ++ ) {
				try {
					paramClasses[i] = Class.forName(paramClassNames[i], false, cls.getClassLoader());
				} catch (ClassNotFoundException e) {
					throw new AssertionError("could not find class of Parameter ('" + paramClassNames[i] + "'): " + e.getMessage(), e);
				}
			}
			Method met;
			try {
				met = cls.getDeclaredMethod(method, paramClasses);
			} catch (NoSuchMethodException | SecurityException e) {
				throw new AssertionError("could not get Method (" + method + " params: " + Arrays.deepToString(paramClassNames) + "): " + e.getMessage(), e);
			}
			boolean flag = met.isAccessible();
			met.setAccessible(true);
			Result r = run(met, invoker, null, null);
			if (r.badResult()) {
				throw new AssertionError("could not get Parameter: " + r.getErr().getMessage(), r.getErr());
			}
			met.setAccessible(flag);
			return type.cast(r.getResult());
		} else if (rp != null && !rp.disabled()) {
			return type.cast(checked);
		} else if (mp != null && !mp.disabled()) {
			return type.cast(notRun);
		} else if (type.isPrimitive()) {
			if (type == Boolean.TYPE) return Boolean.FALSE;
			else if (type == Integer.TYPE) return 0;
			else if (type == Double.TYPE) return 0.0d;
			else if (type == Character.TYPE) return '\0';
			else if (type == Byte.TYPE) return (Byte) (byte) 0;
			else if (type == Long.TYPE) return 0l;
			else if (type == Float.TYPE) return 0.0f;
			else if (type == Short.TYPE) return (Short) (short) 0;
			else throw new InternalError("unknown primitiv param: '" + type + '\'');
		} else if (param.isVarArgs()) return Array.newInstance(type.getComponentType(), 0);
		else return null;
	}
	
	/**
	 * prepares this checker to execute the checks from this class.
	 * <p>
	 * if this checker has been instantiated with the {@link #Checker(Object)} and a <code>null</code>
	 * argument this method has to be executed explicit.<br>
	 * if this {@link Checker} has been instantiated with the {@link #generateChecker(Class)} this
	 * method is already invoked if the checker has a <code>null</code>
	 * {@link #instance}.<br>
	 * if this checker has a non <code>null</code> {@link #instance} and this method has not been
	 * called, this method will automatically been called, when the {@link #run()} method is invoked.
	 * <p>
	 * this method will also enable the assertions for the given class.<br>
	 * if the given class is a nested class the assertions will be enabled for the outermost class.
	 * 
	 * @param clas
	 *            the class for which the checker should be prepared.
	 */
	public final void load(Class <?> clas) {
		for (Class <?> cls = clas, c; cls != null; cls = c) {
			c = cls.getEnclosingClass();
			if (c != null) {
				continue;
			}
			Method m = cls.getEnclosingMethod();
			if (m != null) {
				c = m.getDeclaringClass();
				continue;
			}
			Constructor <?> con;
			con = cls.getEnclosingConstructor();
			if (con != null) {
				c = con.getDeclaringClass();
				continue;
			}
			cls.getClassLoader().setClassAssertionStatus(cls.getName(), true);
		}
		this.init = new ArrayList <>();
		this.start = new ArrayList <>();
		this.end = new ArrayList <>();
		this.finalize = new ArrayList <>();
		this.check = new ArrayList <>();
		addMethods(clas.getDeclaredMethods());
		CheckClass cc = clas.getAnnotation(CheckClass.class);
		if (cc == null || !cc.disableSuper()) {
			clas = clas.getSuperclass();
			while (clas != null) {
				addMethods(clas.getDeclaredMethods());
				clas = clas.getSuperclass();
			}
		}
	}
	
	private void addMethods(Method[] methods) {
		for (Method m : methods) {
			Start s = m.getAnnotation(Start.class);
			if (s != null && !s.disabled()) {
				if (s.onlyOnce()) {
					this.init.add(m);
				} else {
					this.start.add(m);
				}
			}
			End e = m.getAnnotation(End.class);
			if (e != null && !e.disabled()) {
				if (e.onlyOnce()) {
					this.finalize.add(m);
				} else {
					this.end.add(m);
				}
			}
			Check c = m.getAnnotation(Check.class);
			if (c != null && !c.disabled()) {
				this.check.add(m);
			}
		}
	}
	
	/**
	 * this will generate a {@link Checker} of {@code clas}, even if {@code clas} does not
	 * {@code extend} {@link Checker}<br>
	 * the generated {@link Checker} will be used to return the {@link CheckResult}.
	 * 
	 * @param clas
	 *            the {@link Class} to be checked
	 * @return the result of the created {@link Checker}
	 * @implNote it behaves like <code>{@link #generateChecker(Class)}.{@link #result()}</code>
	 */
	public static CheckResult check(final Class <?> clas) {
		try {
			Object instance = createInstance(clas);
			if (instance instanceof Checker) {
				return ((Checker) instance).result();
			} else {
				Checker checker = new Checker(instance);
				return checker.result();
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ignore) {
			// make with 'static' checker (error on instance methods)
		}
		Checker c = new Checker(null);
		c.load(clas);
		return c.result();
	}
	
	/**
	 * this will generate a {@link Checker} of {@code clas}, even if {@code clas} does not
	 * {@code extend} {@link Checker}<br>
	 * 
	 * @param clas
	 *            the {@link Class} to be checked
	 * @return the result of the created {@link Checker}
	 */
	public static Checker generateChecker(final Class <?> clas) {
		try {
			Object instance = createInstance(clas);
			if (instance instanceof Checker) {
				return (Checker) instance;
			} else {
				Checker checker = new Checker(instance);
				checker.load(clas);
				return checker;
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ignore) {
			// make with 'static' checker (error on instance methods)
		}
		Checker c = new Checker(null);
		c.load(clas);
		return c;
	}
	
	private static <T> T createInstance(final Class <T> clas)
			throws InternalError, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Object firstParam = null;
		if (clas.isMemberClass()) {
			if ( !Modifier.isStatic(clas.getModifiers())) {
				Class <?> outerClass = clas.getDeclaringClass();
				firstParam = createInstance(outerClass);
			}
		}
		Constructor <?>[] cs = clas.getDeclaredConstructors();
		for (Constructor <?> c : cs) {
			Start s = c.getAnnotation(Start.class);
			if (s == null) continue;
			if (s.disabled()) continue;
			return create(clas, c, firstParam);
		}
		for (Constructor <?> c : cs) {
			Start s = c.getAnnotation(Start.class);
			if (s != null && s.disabled()) continue;
			return create(clas, c, firstParam);
		}
		Constructor <?> c = cs[0];
		return create(clas, c, firstParam);
	}
	
	private static <T> T create(final Class <T> clas, Constructor <?> c, Object firstParam)
			throws InternalError, InstantiationException, IllegalAccessException, InvocationTargetException {
		Parameter[] params = c.getParameters();
		Object[] ps = new Object[params.length];
		int i = 0;
		if (firstParam != null) {
			ps[0] = firstParam;
			i ++ ;
		}
		for (; i < params.length; i ++ ) {
			ps[i] = getParam(null, null, null, params[i], clas);
		}
		boolean flag = c.isAccessible();
		c.setAccessible(true);
		Object instance = c.newInstance(ps);
		c.setAccessible(flag);
		return clas.cast(instance);
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
		BigCheckResult bcr = new BigCheckResult();
		for (String fcn : fullClassNames) {
			try {
				Class <?> cls;
				cls = Class.forName(fcn, false, classLoader);
				if (needEnabedCheckClass) {
					CheckClass cc = cls.getAnnotation(CheckClass.class);
					if (cc == null) continue;
					if (cc.disabled()) continue;
				}
				bcr.put(cls, check(cls));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		bcr.setEnd(System.currentTimeMillis());
		return bcr;
	}
	
	/**
	 * works like {@link #checkAll(boolean, ClassLoader, String...)}, but uses to load classes the class
	 * loader of the {@link Checker} class.
	 * 
	 * @param needEnabedCheckClass
	 *            if only classes annotated with {@link CheckClass} should be checked
	 * @param classes
	 *            the array containing all classes which should be checked
	 * @return the result of all checks
	 * @see #checkAll(boolean, Iterator)
	 */
	public static BigCheckResult checkAll(boolean needEnabedCheckClass, String... fullClassNames) {
		BigCheckResult bcr = new BigCheckResult();
		ClassLoader loader = Checker.class.getClassLoader();
		for (String fcn : fullClassNames) {
			try {
				Class <?> cls;
				cls = Class.forName(fcn, false, loader);
				if (needEnabedCheckClass) {
					CheckClass cc = cls.getAnnotation(CheckClass.class);
					if (cc == null) continue;
					if (cc.disabled()) continue;
				}
				bcr.put(cls, check(cls));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		bcr.setEnd(System.currentTimeMillis());
		return bcr;
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
		BigCheckResult bcr = new BigCheckResult();
		for (Class <?> cls : classes) {
			if (needEnabedCheckClass) {
				CheckClass cc = cls.getAnnotation(CheckClass.class);
				if (cc == null) {
					continue;
				}
				if (cc.disabled()) {
					continue;
				}
			}
			bcr.put(cls, check(cls));
		}
		bcr.setEnd(System.currentTimeMillis());
		return bcr;
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
		BigCheckResult bcr = new BigCheckResult();
		while (iter.hasNext()) {
			Class <?> cls = iter.next();
			if (needEnabedCheckClass) {
				CheckClass cc = cls.getAnnotation(CheckClass.class);
				if (cc == null) {
					continue;
				}
				if (cc.disabled()) {
					continue;
				}
			}
			CheckResult cr = check(cls);
			bcr.put(cls, cr);
		}
		bcr.setEnd(System.currentTimeMillis());
		return bcr;
	}
	
	/**
	 * like {@link #tryCheckAll(boolean, String, ClassLoader)} with
	 * {@code tryCheckAll(subPackages, package.getName(), Checker.class.getClassLoader())}.
	 * 
	 * @param subPackages
	 *            <code>true</code> if also classes in subPackages should be loaded
	 * @param pakage
	 *            the package
	 * @param loader
	 *            the loader used to find/load classes
	 * @see #tryCheckAll(boolean, String, ClassLoader, boolean)
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
	 *            the name of the package (like {@link Package#getName()})
	 * @param loader
	 *            the loader used to find/load classes
	 * @see #tryCheckAll(boolean, String, ClassLoader, boolean)
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
	 *            the package
	 * @param loader
	 *            the loader used to find/load classes
	 * @see #tryCheckAll(boolean, String, ClassLoader, boolean)
	 */
	public static BigCheckResult tryCheckAll(boolean subPackages, Package pakage, ClassLoader loader) {
		return tryCheckAll(subPackages, pakage.getName(), loader);
	}
	
	/**
	 * like {@link #tryCheckAll(boolean, String, ClassLoader, boolean)} with
	 * {@code tryCheckAll(subPackages, package, loader, true)}.
	 * 
	 * @param subPackages
	 *            <code>true</code> if also classes in subPackages should be loaded
	 * @param pakage
	 *            the name of the package (like {@link Package#getName()})
	 * @param loader
	 *            the loader used to find/load classes
	 * @see #tryCheckAll(boolean, String, ClassLoader, boolean)
	 */
	public static BigCheckResult tryCheckAll(boolean subPackages, String pakage, ClassLoader loader) {
		return tryCheckAll(subPackages, pakage, loader, false);
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
	public static BigCheckResult tryCheckAll(boolean subPackages, String pakage, ClassLoader loader, boolean bailError) {
		Set <Class <?>> classes = tryGetClassesForPackage(pakage, subPackages, loader, bailError);
		return checkAll(true, classes.iterator());
	}
	
	/**
	 * Scans all class-loaders for the current thread for loaded jars, and then scans
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
		String packagePath = packageName.replace(".", "/");
		Set <URL> jarUrls = new HashSet <URL>();
		Set <Path> directorys = new HashSet <Path>();
		
		findClassPools(loader, jarUrls, directorys, bailError);
		Set <Class <?>> jarClasses = findJarClasses(allowSubPackages, packagePath, jarUrls, directorys, loader, bailError);
		Set <Class <?>> dirClasses = findDirClasses(allowSubPackages, packagePath, directorys, loader, bailError);
		jarClasses.addAll(dirClasses);
		return jarClasses;
	}
	
	private static Set <Class <?>> findDirClasses(boolean subPackages, String packagePath, Set <Path> directorys, ClassLoader loader, boolean bailError) {
		Filter <Path> filter;
		Set <Class <?>> result = new HashSet <>();
		for (Path up : directorys) {
			final Path path = up.toAbsolutePath();
			if (subPackages) {
				filter = p -> {
					p = p.toAbsolutePath();
					Path other;
					if (p.getNameCount() >= path.getNameCount()) {
						other = path;
					} else {
						other = path.subpath(0, p.getNameCount());
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
					if (p.getNameCount() > path.getNameCount() + 1) {
						return false;//TODO
					} else if (p.toAbsolutePath().startsWith(path)) {
						return true;
					} else {
						return false;
					}
				};
			}
			findDirClassFilesRecursive(filter, path, path, result, loader, bailError);
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
	
	private static Set <Class <?>> findJarClasses(boolean subPackages, String packagePath, Set <URL> nextJarUrls, Set <Path> directories, ClassLoader loader, boolean bailError) {
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
			classPathObj = stream.getManifest().getMainAttributes().get("Class-Path");
		}
		// class path is space separated. (paths with space become URL like '%20')
		if (classPathObj instanceof String) {
			String[] entries = ((String) classPathObj).split("\\s+");
			for (String entry : entries) {
				try {
					URL url = new URL(entry);
					addUrl(jarUrls, directories, url, bailError);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		}
		// System.out.println("[findJarClassses]: jar-stream.main-attrs.Name(Class-Path): " + classPath);
	}
	
	private static void findClassPools(ClassLoader classLoader, Set <URL> jarUrls, Set <Path> directoryPaths, boolean bailError) {
		while (classLoader != null) {
			if (classLoader instanceof URLClassLoader) {
				for (URL url : ((URLClassLoader) classLoader).getURLs()) {
					addUrl(jarUrls, directoryPaths, url, bailError);
				}
			} else {
				// System.err.println("unknown class loader: " + classLoader.getClass() + " : " + classLoader);
			}
			classLoader = classLoader.getParent();
		}
	}
	
	private static void addUrl(Set <URL> jarUrls, Set <Path> directoryPaths, URL url, boolean bailError) {
		// System.out.println("[findClassPools]: url: " + url);
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

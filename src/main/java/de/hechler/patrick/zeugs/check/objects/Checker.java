//This file is part of the Checker Project
//DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
//Copyright (C) 2023  Patrick Hechler
//
//This program is free software: you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with this program.  If not, see <https://www.gnu.org/licenses/>.
package de.hechler.patrick.zeugs.check.objects;

import static de.hechler.patrick.zeugs.check.objects.LogHandler.LOG;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;
import de.hechler.patrick.zeugs.check.anotations.End;
import de.hechler.patrick.zeugs.check.anotations.MethodParam;
import de.hechler.patrick.zeugs.check.anotations.MethodParamsParam;
import de.hechler.patrick.zeugs.check.anotations.ParamCreater;
import de.hechler.patrick.zeugs.check.anotations.ParamParam;
import de.hechler.patrick.zeugs.check.anotations.ResultParam;
import de.hechler.patrick.zeugs.check.anotations.Start;
import de.hechler.patrick.zeugs.check.interfaces.TriConsumer;
import de.hechler.patrick.zeugs.check.interfaces.TwoVals;

/**
 * this class will be used to execute all checks.<br>
 * the preferred was to instantiate a {@link Checker} is by using the
 * {@link #generateChecker(Class)} method.<br>
 * if the {@link Checker} is only needed for the {@link CheckResult} the
 * {@link #check(Class)} method should be used.
 * <p>
 * to run multiple checks the {@link BigChecker} class is recommended.
 * <p>
 * even if the checker is not a final class, all methods used for checking are
 * marked as final.<br>
 * so it can not affect the check process if a checker is exactly of the
 * {@link Check} class or of a sub-class.
 * 
 * @author Patrick
 */
public class Checker implements Runnable, Supplier<CheckResult> {
	
	private static final Object[] EMPTY_ARR              = new Object[0];
	private static final String   EXPORTER_NULL_PNTR_MSG = "exporter is null";
	
	private static final Map<Module, Consumer<String>> moduleExporters = new HashMap<>();
	
	public static final String LOG_LEVEL_PROP   = "de.hechler.patrick.check.log.level";
	public static final String LOG_HANDLER_PROP = "de.hechler.patrick.check.log.handler";
	
	static {
		Checker.class.getClassLoader().setDefaultAssertionStatus(true);
		LOG.config(() -> "Checker-module: " + Checker.class.getModule());
	}
	
	/**
	 * registers a module exporter, which can be used to export and open packages
	 * for
	 * the checker
	 * 
	 * @param exporter the exporter which accepts its own module as first parameter,
	 *                 the packages which it exports/opens for the checker module as
	 *                 second parameter and the checker module as third parameter
	 */
	public static void registerExporter(TriConsumer<Module, String, Module> exporter) {
		if (exporter == null) { throw new NullPointerException(EXPORTER_NULL_PNTR_MSG); }
		registerExporter(exporter.getClass().getModule(), pkg -> exporter.accept(exporter.getClass().getModule(), pkg, Checker.class.getModule()));
	}
	
	/**
	 * registers a module exporter, which can be used to export and open packages
	 * for
	 * the checker
	 * 
	 * @param exporter the exporter which accepts packages and exports/opens the
	 *                 package for the checker module which is the second parameter
	 */
	public static void registerExporter(BiConsumer<String, Module> exporter) {
		if (exporter == null) { throw new NullPointerException(EXPORTER_NULL_PNTR_MSG); }
		registerExporter(exporter.getClass().getModule(), pkg -> exporter.accept(pkg, Checker.class.getModule()));
	}
	
	/**
	 * registers a module exporter, which can be used to export and open packages
	 * for
	 * the checker
	 * 
	 * @param exporter the exporter which accepts packages and exports/opens the
	 *                 package for the checker module
	 */
	public static void registerExporter(Consumer<String> exporter) {
		if (exporter == null) { throw new NullPointerException(EXPORTER_NULL_PNTR_MSG); }
		synchronized (moduleExporters) {
			moduleExporters.merge(exporter.getClass().getModule(), exporter, (a, b) -> {
				LOG.config("register multiple exporters for the same module (" + a.getClass().getModule() + ")");
				return pkg -> {
					a.accept(pkg);
					b.accept(pkg);
				};
			});
		}
	}
	
	/**
	 * registers a module exporter, which can be used to export and open packages
	 * for
	 * the checker
	 * 
	 * @param module   the module associated with this exporter
	 * @param exporter the exporter which accepts packages and exports/opens the
	 *                 package for the checker module
	 */
	public static void registerExporter(Module module, Consumer<String> exporter) {
		if (exporter == null) { throw new NullPointerException(EXPORTER_NULL_PNTR_MSG); }
		synchronized (moduleExporters) {
			moduleExporters.merge(module, exporter, (a, b) -> {
				LOG.config(() -> "register multiple exporters for the same module (" + a.getClass().getModule() + ')');
				return pkg -> {
					a.accept(pkg);
					b.accept(pkg);
				};
			});
		}
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
	 * the {@link List} saving all methods which should be executed before any
	 * checks are executed
	 * 
	 * @see Start
	 */
	private List<Runnable>                                         init     = null;
	/**
	 * the {@link List} saving all methods which should be executed every time
	 * before the execution of a check starts
	 * 
	 * @see Start
	 */
	private List<Method>                                           start    = null;
	/**
	 * the {@link List} saving all methods which should be executed every time
	 * after the execution of a check ends
	 * 
	 * @see End
	 */
	private List<Method>                                           end      = null;
	/**
	 * the {@link List} saving all methods which should be executed after all
	 * checks are executed
	 * 
	 * @see End
	 */
	private List<Consumer<Supplier<CheckResult>>>                  finalize = null;
	/**
	 * the {@link List} saving all methods which checks to be executed
	 * 
	 * @see Check
	 */
	private List<Consumer<Map<TwoVals<Method, Object[]>, Result>>> check    = null;
	
	/**
	 * saves the result of this checker or <code>null</code> if the checks has not
	 * been executed.<br>
	 * to execute the checks the {@link #run()} method is used.<br>
	 * if {@link #get()} is called before the checks has been executed, the
	 * {@link #run()} method will be automatically be executed.
	 * 
	 * @see #get()
	 * @see #run()
	 */
	private CheckResult result;
	
	
	
	/**
	 * creates a new {@link Checker}.<br>
	 * this {@link Checker} will use itself to call methods:
	 * {@link Method#invoke(Object, Object...) method.invoke(this, params);}
	 */
	public Checker() { instance = this; }
	
	/**
	 * creates a {@link Checker} with the specified <code>instance</code>.<br>
	 * This {@code instance} will be used to call methods.
	 * <p>
	 * only methods, which can be called with the given {@code instance} can be
	 * called.<br>
	 * if {@code instance} is <code>null</code> only static methods can be
	 * called.<br>
	 * (static methods can always be called)
	 * <p>
	 * if the {@code instance} is <code>null</code>, the {@link #load(Class)} method
	 * has to be called before the {@link #run()} (or {@link #get()}) method gets
	 * invoked.<br>
	 * if they are invoked before the {@link #load(Class)} method was called and the
	 * {@link #instance} is <code>null</code> they will throw an
	 * {@link IllegalStateException}.
	 * 
	 * @param instance the {@code instance} used by this {@link Checker}
	 * 
	 * @see Method#invoke(Object, Object...) method.invoke(instance, params);
	 */
	public Checker(Object instance) { this.instance = instance == null ? Checker.class : instance; }
	
	/**
	 * returns <code>true</code> if the checks has already been executed and
	 * <code>false</code> if the checker still needs to execute the checks to
	 * generate the {@link #result} of this checker.
	 * 
	 * @return returns <code>true</code> if the checks has already been executed and
	 *         <code>false</code> if not
	 */
	public final boolean checkedAlready() { return result == null; }
	
	/**
	 * returns the {@link #result} of this checker.<br>
	 * if the {@link #result} has not yet been generated the {@link #run()} method
	 * will be executed.
	 * 
	 * @return the {@link #result} of this checker
	 * 
	 * @see #run()
	 */
	@Override
	public final CheckResult get() {
		if (result == null) { run(); }
		return result;
	}
	
	/**
	 * executes all startup methods (methods annotated with {@link Start}, where
	 * {@link Start#onlyOnce()} is <code>true</code>)<br>
	 * then all checks are executed.<br>
	 * before each check method all {@link Start} methods with
	 * {@link Start#onlyOnce()} set to <code>false</code> are executed.<br>
	 * then the check current is executed (some method annotated with
	 * {@link Check}).<br>
	 * after the check all {@link End} methods with {@link End#onlyOnce()} set to
	 * <code>false</code> are executed.<br>
	 * after the execution of all {@link Check} methods the {@link End} methods
	 * annotated with {@link End#onlyOnce()} set to <code>true</code> are executed.
	 * <p>
	 * if this method is run multiple times the checks are executed multiple
	 * times.<br>
	 * to check if the checks already has been executed use
	 * {@link #checkedAlready()}.<br>
	 * this method is automatically invoked, when {@link #get()} is invoked and the
	 * checks have not been executed.
	 * <p>
	 * this method will not create new {@link Thread} objects, so all
	 * checks/starts/ends are executed with the {@link Thread} executing this
	 * method.<br>
	 * this also means the checked class can use its member variables without caring
	 * about the other checks.
	 * <p>
	 * this method throws a IllegalStateException if this checker has not been
	 * loaded ({@link #load(Class)}) and was created with a null {@link #instance}
	 * (using the {@link #Checker(Object)}
	 * 
	 * @throws IllegalStateException
	 *                               if this checker has not been loaded
	 *                               ({@link #load(Class)}) and was created with a
	 *                               <code>null</code> {@link #instance} (using the
	 *                               {@link #Checker(Object)} constructor with a
	 *                               <code>null</code> argument)
	 */
	@Override
	public final void run() throws IllegalStateException {
		if (this.init == null) {
			if (this.instance == null) { throw new IllegalStateException("this checker is not loaded and I have a null instance."); }
			load(this.instance.getClass());
		}
		long                                   startTime = System.currentTimeMillis();
		Map<TwoVals<Method, Object[]>, Result> results   = new HashMap<>();
		this.init.forEach(Runnable::run);
		this.check.forEach(c -> c.accept(results));
		this.finalize.forEach(c -> c.accept(() -> new CheckResult(results, startTime, System.currentTimeMillis())));
		long endTime = System.currentTimeMillis();
		this.result = new CheckResult(results, startTime, endTime);
	}
	
	
	private static Result run(Method met, Object invoker, Method notRun, Object[] params) {
		Result result;
		long   start = System.currentTimeMillis();
		try {
			setAccessible(met);
			start = System.currentTimeMillis();
			Object res = met.invoke(invoker, params);
			long   end = System.currentTimeMillis();
			result = new Result(notRun, res, start, end);
		} catch (InvocationTargetException | NullPointerException | IllegalAccessException | IllegalArgumentException | AssertionError e) {
			long      end = System.currentTimeMillis();
			Throwable err;
			if (e instanceof InvocationTargetException) {
				err = e.getCause();
			} else {
				err = e;
			}
			if (err instanceof ThreadDeath td) { throw td; }
			result = new Result(notRun, err, start, end);
		}
		return result;
	}
	
	private static Iterable<? extends Object> getParam(Object invoker, Supplier<?> checked, Method notRun, Object[] notParams, Method willRun,
			Parameter notParam, Parameter param, Class<?> cls) throws AssertionError, ClassCastException {
		ParamCreater      pc        = param.getAnnotation(ParamCreater.class);
		MethodParam       mp        = param.getAnnotation(MethodParam.class);
		MethodParamsParam mpp       = param.getAnnotation(MethodParamsParam.class);
		ResultParam       rp        = param.getAnnotation(ResultParam.class);
		ParamParam        pp        = param.getAnnotation(ParamParam.class);
		Class<?>          paramType = param.getType();
		if (pc != null) {
			String method = pc.method();
			Method met;
			try {
				if (pc.clas() == ParamCreater.class) {
					met = cls.getDeclaredMethod(method, pc.params());
				} else {
					met = pc.clas().getDeclaredMethod(method, pc.params());
				}
			} catch (NoSuchMethodException | SecurityException e) {
				throw new AssertionError("could not get Method (" + method + "): " + e.getMessage(), e);
			}
			setAccessible(met);
			Object[] ps = EMPTY_ARR;
			if (met.getParameterCount() > 0) {
				Parameter[] params = met.getParameters();
				ps = new Object[params.length];
				for (int i = 0; i < ps.length; i++) {
					ps[i] = getSingleParam(invoker, null, willRun, null, met, param, params[i], cls);
				}
			}
			Result r = run(met, invoker, met, ps);
			if (r.badResult()) { throw new AssertionError("could not get Parameter: " + r.getErr().getMessage(), r.getErr()); }
			Object   result            = r.getResult();
			Class<?> resultType        = result == null ? paramType : result.getClass();
			Class<?> potPrimResultType = resultType;
			if (resultType.isPrimitive()) {
				if (result == null) throw new NullPointerException("null pointer result for a primitive type");
				else resultType = wrappingType(resultType);
			}
			if (result == null) {
				Iterator<Object> iter = new ElementIterator<>(null);
				return () -> iter;
			} else if (resultType.arrayType().isInstance(result) || potPrimResultType.arrayType().isInstance(result)
					|| paramType.arrayType().isInstance(result)) {
						return () -> new ArrayIterator<>(result);
					} else
				if (result instanceof Iterable<?> iter) {
					return iter;
				} else {
					Iterator<Object> iter = new ElementIterator<>(result);
					return () -> iter;
				}
		} else if (rp != null) {
			Iterator<Object> iter = new ElementIterator<>(paramType.cast(checked.get()));
			return () -> iter;
		} else if (mp != null) {
			Iterator<Object> iter = new ElementIterator<>(paramType.cast(notRun));
			return () -> iter;
		} else if (mpp != null) {
			Iterator<Object> iter = new ElementIterator<>(paramType.cast(notParams));
			return () -> iter;
		} else if (pp != null) {
			Iterator<Object> iter = new ElementIterator<>(paramType.cast(notParam));
			return () -> iter;
		} else throw new AssertionError("could not find anything for this parameter: " + param + " of method: " + willRun);
	}
	
	/**
	 * returns the wrapping type of the given primitive type
	 * <p>
	 * the primitive type {@link Void#TYPE} is not supported and will cause this
	 * method to throw an error
	 */
	private static Class<?> wrappingType(Class<?> type) throws InternalError {
		if (type == int.class) return Integer.class;
		else if (type == long.class) return Long.class;
		else if (type == byte.class) return Byte.class;
		else if (type == short.class) return Short.class;
		else if (type == boolean.class) return Boolean.class;
		else if (type == char.class) return Character.class;
		else if (type == double.class) return Double.class;
		else if (type == float.class) return Float.class;
		else throw new InternalError("unknown primitiv class: '" + type + '\'');
	}
	
	/**
	 * prepares this checker to execute the checks from this class.
	 * <p>
	 * if this checker has been instantiated with the {@link #Checker(Object)} and a
	 * <code>null</code> argument this method has to be executed explicit.<br>
	 * if this {@link Checker} has been instantiated with the
	 * {@link #generateChecker(Class)} this method is already invoked if the checker
	 * has a <code>null</code> {@link #instance}.<br>
	 * if this checker has a non <code>null</code> {@link #instance} and this method
	 * has not been called, this method will automatically been called, when the
	 * {@link #run()} method is invoked.
	 * <p>
	 * this method will also enable the assertions for the given class.<br>
	 * if the given class is a nested class the assertions will be enabled for the
	 * outermost class.
	 * 
	 * @param clas
	 *             the class for which the checker should be prepared.
	 */
	public final void load(Class<?> clas) {
		if (clas == null) { throw new NullPointerException(); }
		for (Class<?> cls = clas, c; cls != null; cls = c) {
			c = cls.getEnclosingClass();
			if (c != null) { continue; }
			Method m = cls.getEnclosingMethod();
			if (m != null) {
				c = m.getDeclaringClass();
				continue;
			}
			Constructor<?> con;
			con = cls.getEnclosingConstructor();
			if (con != null) {
				c = con.getDeclaringClass();
				continue;
			}
			cls.getClassLoader().setClassAssertionStatus(cls.getName(), true);
		}
		this.init     = new ArrayList<>();
		this.start    = new ArrayList<>();
		this.end      = new ArrayList<>();
		this.finalize = new ArrayList<>();
		this.check    = new ArrayList<>();
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
					this.init.add(() -> executeMethod(null, null, null, m));
				} else {
					this.start.add(m);
				}
			}
			End e = m.getAnnotation(End.class);
			if (e != null && !e.disabled()) {
				if (e.onlyOnce()) {
					this.finalize.add(sup -> executeMethod(null, null, sup, m));
				} else {
					this.end.add(m);
				}
			}
			Check c = m.getAnnotation(Check.class);
			if (c != null && !c.disabled()) {
				this.check.add(resultsMap -> {
					if (m.getParameterCount() == 0) {
						executeStart(m, EMPTY_ARR);
						Result r = run(m, instance, m, EMPTY_ARR);
						executeEnd(m, EMPTY_ARR, r);
						resultsMap.put(new TwoValues<>(m, EMPTY_ARR), r);
					} else {
						for (Iterator<Object[]> iter = generateBigIter(m, null, null, null, null); iter.hasNext();) {
							Object[] objs = iter.next();
							if (objs == null) { throw new AssertionError(); }
							executeStart(m, objs);
							Result r = run(m, instance, null, objs);
							executeEnd(m, objs, r);
							resultsMap.put(new TwoValues<>(m, objs.clone()), r);
						}
					}
				});
			}
		}
	}
	
	private void executeEnd(Method m, Object[] notParams, Result r) throws AssertionError {
		for (Method em : this.end) {
			executeMethod(m, notParams, () -> r, em);
		}
	}
	
	private void executeStart(Method m, Object[] notParams) throws AssertionError {
		for (Method sm : this.start) {
			if (sm.getParameterCount() == 0) {
				Result sr = run(sm, instance, null, EMPTY_ARR);
				if (sr.badResult()) { throw new AssertionError(sr); }
			} else {
				for (Iterator<Object[]> startIter = generateBigIter(sm, null, m, notParams, null); startIter.hasNext();) {
					Object[] startObjs = startIter.next();
					Result   sr        = run(sm, instance, null, startObjs);
					if (sr.badResult()) { throw new AssertionError(sr); }
				}
			}
		}
	}
	
	private void executeMethod(Method m, Object[] notParams, Supplier<?> r, Method exeMet) throws AssertionError {
		if (exeMet.getParameterCount() == 0) {
			Result er = run(exeMet, instance, m == null ? exeMet : m, EMPTY_ARR);
			if (er.badResult()) { throw new AssertionError(er); }
		} else {
			for (Iterator<Object[]> endIter = generateBigIter(exeMet, r, m, notParams, null); endIter.hasNext();) {
				Object[] endObjs = endIter.next();
				Result   er      = run(exeMet, instance, m == null ? exeMet : m, endObjs);
				if (er.badResult()) { throw new AssertionError(er); }
			}
		}
	}
	
	private Iterator<Object[]> generateBigIter(Method m, Supplier<?> sup, Method notRun, Object[] notParams, Parameter notParam)
			throws AssertionError {
		Parameter[]   params = m.getParameters();
		Iterable<?>[] ps     = new Iterable<?>[params.length];
		for (int i = 0; i < params.length; i++) {
			ps[i] = getParam(instance, sup, notRun, notParams, m, notParam, params[i], m.getDeclaringClass());
		}
		return new BigIterator<>(Object.class, ps);
	}
	
	/**
	 * this will generate a {@link Checker} of {@code clas}, even if {@code clas}
	 * does not {@code extend} {@link Checker}<br>
	 * the generated {@link Checker} will be used to return the {@link CheckResult}.
	 * <p>
	 * it behaves like <code>{@link #generateChecker(Class)}.{@link #get()}</code>
	 * 
	 * @param clas
	 *             the {@link Class} to be checked
	 * 			
	 * @return the result of the created {@link Checker}
	 */
	public static CheckResult check(final Class<?> clas) {
		try {
			Object instance = createInstance(clas);
			if (instance instanceof Checker c) {
				return c.get();
			} else {
				Checker checker = new Checker(instance);
				return checker.get();
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException ignore) {
			// make with 'static' checker (error on instance methods)
		}
		Checker c = new Checker(null);
		c.load(clas);
		return c.get();
	}
	
	/**
	 * this will generate a {@link Checker} of {@code clas}, even if {@code clas}
	 * does not {@code extend} {@link Checker}<br>
	 * 
	 * @param clas
	 *             the {@link Class} to be checked
	 * 			
	 * @return the result of the created {@link Checker}
	 */
	public static Checker generateChecker(final Class<?> clas) {
		try {
			exp(clas);
			Object instance = createInstance(clas);
			if (instance instanceof Checker c) {
				return c;
			} else {
				Checker checker = new Checker(instance);
				checker.load(clas);
				return checker;
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException ignore) {
			LOG.config(() -> "[WARN]: create static only checker for class "
					+ (clas.getCanonicalName() == null ? clas.getName() : clas.getCanonicalName()));
		}
		Checker c = new Checker(null);
		c.load(clas);
		return c;
	}
	
	private static <T> T createInstance(final Class<T> clas)
			throws InternalError, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Object firstParam = null;
		if (clas.isMemberClass()) {
			if (!Modifier.isStatic(clas.getModifiers())) {
				Class<?> outerClass = clas.getDeclaringClass();
				firstParam = createInstance(outerClass);
			}
		}
		Constructor<?>[] cs = clas.getDeclaredConstructors();
		for (Constructor<?> c : cs) {
			Start s = c.getAnnotation(Start.class);
			if (s == null) continue;
			if (s.disabled()) continue;
			return create(clas, c, firstParam);
		}
		for (Constructor<?> c : cs) {
			Start s = c.getAnnotation(Start.class);
			if (s != null /* && s.disabled() */) continue;
			return create(clas, c, firstParam);
		}
		Constructor<?> c = cs[0];
		return create(clas, c, firstParam);
	}
	
	private static <T> T create(final Class<T> clas, Constructor<?> c, Object firstParam)
			throws InternalError, InstantiationException, IllegalAccessException, InvocationTargetException {
		exp(clas);
		Parameter[] params = c.getParameters();
		Object[]    ps     = new Object[params.length];
		int         i      = 0;
		if (firstParam != null) {
			ps[0] = firstParam;
			i++;
		}
		for (; i < params.length; i++) {
			ps[i] = getSingleParam(null, null, null, null, null, null, params[i], clas);
		}
		setAccessible(c);
		Object instance = c.newInstance(ps);
		return clas.cast(instance);
	}
	
	private static Object getSingleParam(Object invoker, Supplier<?> checked, Method notRun, Object[] notParams, Method willRun, Parameter notParam,
			Parameter param, Class<?> cls) {
		Iterator<?> iter = getParam(invoker, checked, notRun, notParams, willRun, notParam, param, cls).iterator();
		if (!iter.hasNext()) { throw new AssertionError("iterator has no element (tried to get single value of param: " + param + ")"); }
		Object result = iter.next();
		if (iter.hasNext()) { throw new AssertionError("iterator has too many elements (tried to get single value of param: " + param + ")"); }
		return result;
	}
	
	private static void exp(Class<?> clas) {
		synchronized (moduleExporters) {
			Consumer<String> register = moduleExporters.get(clas.getModule());
			if (register != null) { register.accept(clas.getPackageName()); }
		}
	}
	
	/**
	 * sets the given accessible object accessible.
	 * 
	 * @param ao
	 *           the accessible object to set accessible
	 */
	@SuppressWarnings("removal")
	public static void setAccessible(AccessibleObject ao) {
		if (ao instanceof Field) {
			Field f    = (Field) ao;
			int   mods = f.getModifiers();
			if (Modifier.isPublic(mods) && !Modifier.isFinal(mods)) { return; }
		} else if (ao instanceof Executable) {
			Executable e    = (Executable) ao;
			int        mods = e.getModifiers();
			if (Modifier.isPublic(mods) && !Modifier.isFinal(mods)) { return; }
		}
		try {
			ao.setAccessible(true);
		} catch (Exception e) {
			try {
				Field   field = ao.getClass().getField("override");
				Boolean t     = Boolean.TRUE;
				if (t == null || !t.booleanValue()) { t = new Boolean(true); }
				unsafePut(field, ao, t);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | ClassCastException
					| NullPointerException e1) {
				e.addSuppressed(e1);
				throw e;
			}
		}
	}
	
	/**
	 * returns the value of a field
	 * 
	 * @param f        the field object, from which the value should be retrieved
	 * @param instance the instance from which the field should be retrieved
	 *                 (ignored for static fields)
	 * 				
	 * @return the value of the given field
	 * 				
	 * @throws IllegalArgumentException if the instance does not has such a field
	 * @throws IllegalAccessException   if the field could not be accessed
	 */
	public static Object getValue(Field f, Object instance) throws IllegalArgumentException, IllegalAccessException {
		try {
			setAccessible(f);
			return f.get(instance);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			try {
				return unsafeGet(f, instance);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
				e.addSuppressed(e1);
				throw e;
			}
		}
	}
	
	/**
	 * this method uses the sun.misc.Unsafe class to get the value of the given
	 * field.<br>
	 * if the field contains a primitive type, the fields value will be wrapped to
	 * the given non primitive type
	 * 
	 * @param field
	 *                 the field which holds the value to receive
	 * @param instance
	 *                 the instance on wich should be worked if the field is static
	 *                 this value will be ignored
	 * 				
	 * @return the value of the field on the given instance
	 * 				
	 * @throws NoSuchFieldException
	 *                                  if the unsafe could not be received
	 * @throws SecurityException
	 *                                  if the unsafe could not be received
	 * @throws IllegalArgumentException
	 *                                  if the unsafe could not be received
	 * @throws IllegalAccessException
	 *                                  if the unsafe could not be received
	 */
	public static Object unsafeGet(Field field, Object instance)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		sun.misc.Unsafe unsafe = getUnsafe();
		long            off;
		if (Modifier.isStatic(field.getModifiers())) {
			off      = unsafe.staticFieldOffset(field);
			instance = unsafe.staticFieldBase(field);
		} else {
			off = unsafe.objectFieldOffset(field);
		}
		Class<?> type = field.getType();
		if (type.isPrimitive()) {
			if (type == long.class) return (Long) unsafe.getLong(instance, off);
			else if (type == int.class) return (Integer) unsafe.getInt(instance, off);
			else if (type == short.class) return (Short) unsafe.getShort(instance, off);
			else if (type == byte.class) return (Byte) unsafe.getByte(instance, off);
			else if (type == boolean.class) return (Boolean) unsafe.getBoolean(instance, off);
			else if (type == double.class) return (Double) unsafe.getDouble(instance, off);
			else if (type == float.class) return (Float) unsafe.getFloat(instance, off);
			else throw new InternalError("unknown primitive type: " + type);
		} else {
			return unsafe.getObject(instance, off);
		}
	}
	
	/**
	 * this method uses the sun.misc.Unsafe class to put the value of the given
	 * field.<br>
	 * if the field contains a primitive type, the fields value will be wrapped to
	 * the given non primitive type
	 * 
	 * @param field
	 *                 the given field
	 * @param instance
	 *                 the given instance if the field is static this value will be
	 *                 ignored
	 * @param value
	 *                 the new value of the field on the given instance
	 * 				
	 * @throws NoSuchFieldException
	 *                                  if the unsafe could not be received
	 * @throws SecurityException
	 *                                  if the unsafe could not be received
	 * @throws IllegalArgumentException
	 *                                  if the unsafe could not be received
	 * @throws IllegalAccessException
	 *                                  if the unsafe could not be received
	 * @throws ClassCastException
	 *                                  if the is not of the correct type for the
	 *                                  given field
	 * @throws NullPointerException
	 *                                  if the field is from a primitive type, but
	 *                                  the value is <code>null</code>
	 */
	public static void unsafePut(Field field, Object instance, Object value) throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, ClassCastException, NullPointerException {
		sun.misc.Unsafe unsafe = getUnsafe();
		long            off;
		if (Modifier.isStatic(field.getModifiers())) {
			off      = unsafe.staticFieldOffset(field);
			instance = unsafe.staticFieldBase(field);
		} else {
			off = unsafe.objectFieldOffset(field);
		}
		Class<?> type = field.getType();
		if (type.isPrimitive()) {
			if (type == long.class) unsafe.putLong(instance, off, (Long) value);
			else if (type == int.class) unsafe.putInt(instance, off, (Integer) value);
			else if (type == short.class) unsafe.putShort(instance, off, (Short) value);
			else if (type == byte.class) unsafe.putByte(instance, off, (Byte) value);
			else if (type == boolean.class) unsafe.putBoolean(instance, off, (Boolean) value);
			else if (type == double.class) unsafe.putDouble(instance, off, (Double) value);
			else if (type == float.class) unsafe.putFloat(instance, off, (Float) value);
			else throw new InternalError("unknown primitive type: " + type);
		} else {
			unsafe.putObject(instance, off, type.cast(value));
		}
	}
	
	private static sun.misc.Unsafe getUnsafe() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		LogHandler.LOG.info("try to get the unsafe");
		Field theUnsafe = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
		try {
			theUnsafe.setAccessible(true);
			return (sun.misc.Unsafe) theUnsafe.get(null);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			try {
				Field override = theUnsafe.getClass().getField("override");
				override.setAccessible(true);
				override.setBoolean(theUnsafe, true);
				return (sun.misc.Unsafe) theUnsafe.get(null);
			} catch (RuntimeException | IllegalAccessException | NoSuchFieldException e1) {
				e.addSuppressed(e1);
				throw e;
			}
		}
	}
	
}

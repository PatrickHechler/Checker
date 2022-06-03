package de.hechler.patrick.zeugs.check.objects;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;
import de.hechler.patrick.zeugs.check.anotations.End;
import de.hechler.patrick.zeugs.check.anotations.MethodParam;
import de.hechler.patrick.zeugs.check.anotations.ParamCreater;
import de.hechler.patrick.zeugs.check.anotations.ResultParam;
import de.hechler.patrick.zeugs.check.anotations.Start;

/**
 * this class will be used to execute all checks.<br>
 * the preferred was to instantiate a {@link Checker} is by using the
 * {@link #generateChecker(Class)} method.<br>
 * if the {@link Checker} is only needed for the {@link CheckResult} the {@link #check(Class)}
 * method should be used.
 * <p>
 * to run multiple checks the {@link BigChecker} class is recommended.
 * <p>
 * even if the checker is not a final class, all methods used for checking are marked as final.<br>
 * so it can does not affect the check process if a checker is exactly of the {@link Check} class or
 * of a sub-class.
 * 
 * @author Patrick
 */
public class Checker implements Runnable, Supplier <CheckResult> {
	
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
	 * if {@link #get()} is called before the checks has been executed, the {@link #run()} method
	 * will be automatically be executed.
	 * 
	 * @see #get()
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
	 * before the {@link #run()} (or {@link #get()}) method gets invoked.<br>
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
	 * if the {@link #result} has not yet been generated the {@link #run()} method will be executed.
	 * 
	 * @return the {@link #result} of this checker
	 * @see #result()
	 */
	@Override
	public final CheckResult get() {
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
	 * this method is automatically invoked, when {@link #get()} is invoked and the checks have not
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
		long start = System.currentTimeMillis();
		Map <String, Method> methods = new HashMap <>();
		Map <Method, Result> results = new HashMap <>();
		this.init.forEach(m -> run(m, this.instance, null, null));
		this.check.forEach(m -> {
			Result result = null;
			for (Method r : this.start) {
				Result res = run(r, this.instance, null, m);
				if (res.badResult()) {
					result = res;
				}
			}
			if (result == null) {
				result = run(m, this.instance, null, null);
				final Result finalresult = result;
				for (Method r : this.end) {
					Result res = run(r, this.instance, () -> finalresult, m);
					if (res.badResult() && result.goodResult()) {
						result = new Result(res.met, res.getErr(), result.start, result.end);
					}
				}
			}
			CheckResult.put(methods, results, m, result);
		});
		this.finalize.forEach(m -> run(m, this.instance, () -> new CheckResult(methods, results, start, System.currentTimeMillis()), null));
		long end = System.currentTimeMillis();
		this.result = new CheckResult(methods, results, start, end);
	}
	
	
	private static Result run(Method met, Object invoker, Supplier <Object> checked, Method notRun) {
		Result result;
		long start = System.currentTimeMillis();
		try {
			Parameter[] params = met.getParameters();
			Object[] ps = new Object[params.length];
			for (int i = 0; i < params.length; i ++ ) {
				Parameter param = params[i];
				ps[i] = getParam(invoker, checked, notRun, param, met.getDeclaringClass());
			}
			setAccessible(met);
			start = System.currentTimeMillis();
			Object res = met.invoke(invoker, ps);
			long end = System.currentTimeMillis();
			result = new Result(notRun, res, start, end);
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
			result = new Result(notRun, err, start, end);
		}
		return result;
	}
	
	private static Object getParam(Object invoker, Supplier <Object> checked, Method notRun, Parameter param, Class <?> cls) throws AssertionError, ClassCastException {
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
			setAccessible(met);
			Result r = run(met, invoker, null, notRun);
			if (r.badResult()) {
				throw new AssertionError("could not get Parameter: " + r.getErr().getMessage(), r.getErr());
			}
			Object result = r.getResult();
			if (type.isPrimitive()) {
				if (result == null) throw new NullPointerException("null pointer result for a primitive type");
				else if (type == int.class) type = Integer.class;
				else if (type == long.class) type = Long.class;
				else if (type == byte.class) type = Byte.class;
				else if (type == short.class) type = Short.class;
				else if (type == boolean.class) type = Boolean.class;
				else if (type == char.class) type = Character.class;
				else if (type == double.class) type = Double.class;
				else if (type == float.class) type = Float.class;
				else throw new InternalError("unknown primitiv param: '" + type + '\'');
			}
			return type.cast(result);
		} else if (rp != null && !rp.disabled()) {
			return type.cast(checked.get());
		} else if (mp != null && !mp.disabled()) {
			return type.cast(notRun);
		} else if (type.isPrimitive()) {
			if (type == Boolean.TYPE) return Boolean.FALSE;
			else if (type == int.class) return 0;
			else if (type == double.class) return 0.0d;
			else if (type == char.class) return '\0';
			else if (type == byte.class) return (Byte) (byte) 0;
			else if (type == long.class) return 0l;
			else if (type == float.class) return 0.0f;
			else if (type == short.class) return (Short) (short) 0;
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
	 * @implNote it behaves like <code>{@link #generateChecker(Class)}.{@link #get()}</code>
	 */
	public static CheckResult check(final Class <?> clas) {
		try {
			Object instance = createInstance(clas);
			if (instance instanceof Checker) {
				return ((Checker) instance).get();
			} else {
				Checker checker = new Checker(instance);
				return checker.get();
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ignore) {
			// make with 'static' checker (error on instance methods)
		}
		Checker c = new Checker(null);
		c.load(clas);
		return c.get();
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
		setAccessible(c);
		Object instance = c.newInstance(ps);
		return clas.cast(instance);
	}
	
	/**
	 * sets the given accessible object accessible.
	 * 
	 * @param ao
	 *            the accessible object to set accessible
	 */
	public static void setAccessible(AccessibleObject ao) {
		try {
			ao.setAccessible(true);
		} catch (Exception e) {
			try {
				Field field = ao.getClass().getField("override");
				Boolean t = Boolean.TRUE;
				if (t == null || !t.booleanValue()) {
					t = new Boolean(true);
				}
				unsafePut(field, ao, t);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | ClassCastException | NullPointerException e1) {
				e.addSuppressed(e1);
				throw e;
			}
		}
	}
	
	public static Object getValue(Field f, Object instance) throws IllegalAccessException, IllegalArgumentException, ExceptionInInitializerError {
		try {
			setAccessible(f);
			return f.get(instance);
		} catch (Exception e) {
			try {
				return unsafeGet(f, instance);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
				e.addSuppressed(e1);
				throw e;
			}
		}
	}
	
	/**
	 * this method uses the sun.misc.Unsafe class to get the value of the given field.<br>
	 * if the field contains a primitive type, the fields value will be wrapped to the given non
	 * primitive type
	 * 
	 * @param field
	 *            the field which holds the value to receive
	 * @param instance
	 *            the instance on wich should be worked if the field is static this value will be
	 *            ignored
	 * @return the value of the field on the given instance
	 * @throws NoSuchFieldException
	 *             if the unsafe could not be received
	 * @throws SecurityException
	 *             if the unsafe could not be received
	 * @throws IllegalArgumentException
	 *             if the unsafe could not be received
	 * @throws IllegalAccessException
	 *             if the unsafe could not be received
	 */
	@SuppressWarnings("restriction")
	public static Object unsafeGet(Field field, Object instance) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		sun.misc.Unsafe unsafe = getUnsafe();
		long off;
		if (Modifier.isStatic(field.getModifiers())) {
			off = unsafe.staticFieldOffset(field);
			instance = unsafe.staticFieldBase(field);
		} else {
			off = unsafe.objectFieldOffset(field);
		}
		Class <?> type = field.getType();
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
	 * this method uses the sun.misc.Unsafe class to put the value of the given field.<br>
	 * if the field contains a primitive type, the fields value will be wrapped to the given non
	 * primitive type
	 * 
	 * @param field
	 *            the given field
	 * @param instance
	 *            the given instance if the field is static this value will be ignored
	 * @param value
	 *            the new value of the field on the given instance
	 * @throws NoSuchFieldException
	 *             if the unsafe could not be received
	 * @throws SecurityException
	 *             if the unsafe could not be received
	 * @throws IllegalArgumentException
	 *             if the unsafe could not be received
	 * @throws IllegalAccessException
	 *             if the unsafe could not be received
	 * @throws ClassCastException
	 *             if the is not of the correct type for the given field
	 * @throws NullPointerException
	 *             if the field is from a primitive type, but the value is <code>null</code>
	 */
	@SuppressWarnings("restriction")
	public static void unsafePut(Field field, Object instance, Object value)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassCastException, NullPointerException {
		sun.misc.Unsafe unsafe = getUnsafe();
		long off;
		if (Modifier.isStatic(field.getModifiers())) {
			off = unsafe.staticFieldOffset(field);
			instance = unsafe.staticFieldBase(field);
		} else {
			off = unsafe.objectFieldOffset(field);
		}
		Class <?> type = field.getType();
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
	
	@SuppressWarnings("restriction")
	private static sun.misc.Unsafe getUnsafe() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		System.out.println("get unsafe");
		Field theUnsafe = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
		try {
			theUnsafe.setAccessible(true);
			return (sun.misc.Unsafe) theUnsafe.get(null);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			try {
				Field field = theUnsafe.getClass().getField("override");
				field.setAccessible(true);
				field.setBoolean(theUnsafe, true);
				return (sun.misc.Unsafe) theUnsafe.get(null);
			} catch (RuntimeException | IllegalAccessException | NoSuchFieldException e1) {
				e.addSuppressed(e1);
				throw e;
			}
		}
	}
	
}

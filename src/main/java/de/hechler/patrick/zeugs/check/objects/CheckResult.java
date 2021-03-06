package de.hechler.patrick.zeugs.check.objects;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;

/**
 * this class saves the {@link Result}s of a {@link Checker}.<br>
 * The {@link Result} of each checked {@link Method} is accessible with the {@link #getResult(Method)} method
 * <p>
 * in addition to all checked methods, the executed time for all checks is saved.<br>
 * to get the time needed for the execution the class check {@link #getTime()} can be used.<br>
 * to get the start time of the class check the field {@link #start} can be accessed.<br>
 * to get the end time of the class check {@link #end} can be accessed.
 * 
 * @author Patrick
 */
public final class CheckResult {
	
	/**
	 * this map saves all {@link Method}s with their names as keys (and their {@link Parameter params})
	 */
	private final Map <String, Method> methods;
	/**
	 * this map contains all {@link Result} and the {@link Method}, from which the results are
	 */
	private final Map <Method, Result> results;
	/**
	 * the time when the checks were started
	 */
	public final long                  start;
	/**
	 * the time when all checks were finished
	 */
	public final long                  end;
	
	/**
	 * creates a new {@link CheckResult} with the given values.
	 * <p>
	 * to build the {@link #methods} and {@link #results} maps the {@link #put(Map, Map, Method, Result)} method is recommended.
	 * 
	 * @param methods
	 *            this map contains the methods which has been checked.
	 * @param results
	 *            this map contains the {@link Result} of the checks.
	 * @param start
	 *            the time when the checker started to check
	 * @param end
	 *            the time when the checks were finished
	 */
	public CheckResult(Map <String, Method> methods, Map <Method, Result> results, long start, long end) {
		this.methods = methods;
		this.results = results;
		this.start = start;
		this.end = end;
	}
	
	/**
	 * returns the total time needed for this {@link CheckResult}
	 * 
	 * @return the total time needed for this {@link CheckResult}
	 */
	public long getTime() {
		return end - start;
	}
	
	/**
	 * this method saves the {@link Result} with the {@link Method} in the {@code results} and the method with its {@link Method#getName() name} (and {@link Method#getParameters() params}) in the
	 * {@code methods}.<br>
	 * When the {@link Method} has no params (<code>{@link Method#getParameterCount()} == 0</code>) there will be two links created to the {@link Method} in {@code methods}: one wit only the
	 * {@link Method#getName() name} and one with the {@link Method#getName() name} and the braces '()'.<br>
	 * When the {@link Method} has params (<code>{@link Method#getParameterCount()} > 0</code>) there will be one link in the {@code methods}: it will start with the {@link Method#getName() name} and
	 * then the braces. between the braces will be the {@link Method#getParameters() params} with only their {@link Parameter#getType() type} as fully qualifying name. if a param is a
	 * {@link Parameter#isVarArgs() varArg}, it will have '...' at the end and a second with '[]' at the end all other arrays will have only '[]' at the end. The {@link Method#getParameters() params}
	 * will be separated by <code>', '</code>.
	 * 
	 * @param methods
	 *            the map containing the methods refereed by their names
	 * @param results
	 *            the map containing the results refereed by the Method
	 * @param met
	 *            the checked {@link Method}
	 * @param value
	 *            the {@link Result} value of the checked {@link Method}
	 */
	public static void put(Map <String, Method> methods, Map <Method, Result> results, Method met, Result value) {
		if (met.getParameterCount() > 0) {
			StringBuilder name = new StringBuilder(met.getName()).append('(');
			Parameter[] clss = met.getParameters();
			if (clss.length == 0) {
				addToMethods(methods, results, met, met.getName());
			}
			Class <?> zw = clss[0].getType();
			int deep = 0;
			while (zw.isArray()) {
				deep ++ ;
				zw = zw.getComponentType();
			}
			name.append(zw.getName());
			for (int ii = 0; ii < deep; ii ++ ) {
				name.append("[]");
			}
			for (int i = 1; i < clss.length; i ++ ) {
				zw = clss[i].getType();
				deep = 0;
				while (zw.isArray()) {
					deep ++ ;
					zw = zw.getComponentType();
				}
				name.append(", ").append(zw.getName());
				for (int ii = 0; ii < deep; ii ++ ) {
					name.append("[]");
				}
			}
			String methodName = name.append(')').toString();
			addToMethods(methods, results, met, methodName);
			results.put(met, value);
			if (clss[clss.length - 1].isVarArgs()) {
				name = new StringBuilder(met.getName()).append('(');
				zw = clss[0].getType();
				deep = 0;
				while (zw.isArray()) {
					deep ++ ;
					zw = zw.getComponentType();
				}
				name.append(zw.getName());
				for (int ii = 0; ii < deep; ii ++ ) {
					name.append("[]");
				}
				for (int i = 1; i < clss.length - 1; i ++ ) {
					zw = clss[i].getType();
					deep = 0;
					while (zw.isArray()) {
						deep ++ ;
						zw = zw.getComponentType();
					}
					name.append(", ").append(zw.getName());
					for (int ii = 0; ii < deep; ii ++ ) {
						name.append("[]");
					}
				}
				zw = clss[clss.length - 1].getType();
				deep = 0;
				while (zw.isArray()) {
					deep ++ ;
					zw = zw.getComponentType();
				}
				name.append(", ").append(zw.getName());
				deep -- ;
				for (int ii = 0; ii < deep; ii ++ ) {
					name.append("[]");
				}
				name.append("...");
				addToMethods(methods, results, met, methodName);
			}
		} else {
			methods.put(met.getName() + "()", met);
			methods.put(met.getName(), met);
			results.put(met, value);
		}
	}
	
	private static void addToMethods(Map <String, Method> methods, Map <Method, Result> results, Method m, String methodName) {
		Method met = methods.get(methodName);
		if (met != null) {
			Result res = results.get(met);
			if (res.badResult()) {
				return;
			}
		}
		methods.put(methodName, m);
	}
	
	/**
	 * returns the {@link Result} from the {@link #results} by the {@link Method}
	 * 
	 * @param m
	 *            the {@link Method}
	 * @return the {@link Result} from the {@link #results} by the {@link Method}
	 * @throws NoSuchElementException
	 *             if there is no {@link Result} in the {@link #results} for the {@link Method}
	 * @see #getResult(String)
	 */
	public Result getResult(Method m) throws NoSuchElementException {
		Result res = results.get(m);
		if (res == null) throw new NoSuchElementException("missing method '" + m.getName() + "' in my methods: " + this.results.keySet());
		return res;
	}
	
	/**
	 * returns the {@link Method} of the {@link #methods} by it's name
	 * <p>
	 * the method name is the name of the method plus it's parameter classes wrapped in parentheses and separated with a comma and a single space character.<br>
	 * if the method has no parameters, the parentheses are optional.
	 * <p>
	 * if due to super checkers multiple methods with the name has been checked it is not defined, which method will be returned.<br>
	 * but if at least one of the results is a {@link Result#badResult()} it is ensured that a {@link Result#badResult()} will be returned
	 * 
	 * @param mname
	 *            the name of the {@link Method}
	 * @return the {@link Method} of the {@link #methods} by it's name
	 * @throws NoSuchElementException
	 *             if the {@link #methods} does not contain a {@link Method} with the given name
	 */
	public Method getMethod(String mname) throws NoSuchElementException {
		Method met = methods.get(mname);
		if (met == null) throw new NoSuchElementException("missing method '" + mname + "' in my methods: " + methods);
		return met;
	}
	
	/**
	 * returns <code>true</code> if this {@link CheckResult} contains a {@link Result} for a {@link Method} with this name (and with the params if the {@link Method} have some), <code>false</code>
	 * otherwise
	 * 
	 * @param mname
	 *            the name of the {@link Method} and their potentially params
	 * @return <code>true</code> if this {@link CheckResult} contains a {@link Result} for a {@link Method} with this name (and with the params if the {@link Method} have some), <code>false</code>
	 *         otherwise
	 */
	public boolean checked(String mname) {
		return this.methods.containsKey(mname);
	}
	
	/**
	 * returns <code>true</code> if this {@link CheckResult} contains a {@link Result} for this {@link Method}, <code>false</code> otherwise
	 * 
	 * @param m
	 *            the {@link Method} which is potentially saved in this {@link CheckResult}
	 * @return <code>true</code> if this {@link CheckResult} contains a {@link Result} for this {@link Method}, <code>false</code> otherwise
	 */
	public boolean checked(Method m) {
		return this.results.containsKey(m);
	}
	
	/**
	 * returns <code>true</code> if the execution of the method went as expected (if no {@link Throwable} was thrown)
	 * 
	 * @param mname
	 *            the name of the {@link Method}
	 * @return <code>true</code> if the execution of the method went as expected (if no {@link Throwable} was thrown)
	 * @throws NoSuchElementException
	 *             if no {@link Result} is saved for the method in this {@link CheckResult}
	 */
	public boolean wentExpected(String mname) throws NoSuchElementException {
		Method m = this.methods.get(mname);
		if (m == null) throw new NoSuchElementException("missing method '" + mname + "' in my methods: " + this.methods.keySet());
		return this.results.get(m).goodResult();
	}
	
	/**
	 * returns <code>true</code> if the execution of the {@link Method} went as expected (if no {@link Throwable} was thrown)
	 * 
	 * @param m
	 *            the {@link Method} identifier
	 * @return <code>true</code> if the execution of the {@link Method} went as expected (if no {@link Throwable} was thrown)
	 * @throws NoSuchElementException
	 *             if no {@link Result} is saved for the {@link Method} in this {@link CheckResult}
	 */
	public boolean wentExpected(Method m) throws NoSuchElementException {
		Result res = this.results.get(m);
		if (res == null) {
			throw new NoSuchElementException("missing method '" + m.getName() + "' in my methods: " + this.results.keySet());
		}
		return res.goodResult();
	}
	
	/**
	 * this method will return <code>true</code> if and only if all Checks went as {@link Result#goodResult() expected} (without any {@link Throwable} thrown). if at least one Check is
	 * {@link Result#badResult() bad} this method will return <code>false</code>
	 * 
	 * @return <code>true</code> if none of the {@link #results} is a {@link Result#badResult() bad Result}
	 * @see #wentUnexpected()
	 */
	public boolean wentExpected() {
		for (Result r : this.results.values()) {
			if (r.badResult()) return false;
		}
		return true;
	}
	
	/**
	 * returns <code>true</code> if the {@link Result} of the method is a {@link Result#badResult() bad Result} and <code>false</code> if not. The {@link Method} will be searched in the
	 * {@link #methods} map. The {@link Result} will be searched in the {@link #results} map
	 * 
	 * @param mname
	 *            the name of the method to search in the {@link #methods}
	 * @return <code>true</code> if the {@link Result} of the method is a {@link Result#badResult() bad Result} and <code>false</code> if not
	 * @throws NoSuchElementException
	 *             if there is no mapping in the {@link #methods} for the given name
	 * @see #wentUnexpected(Method)
	 * @see #wentExpected(String)
	 * @see #wentExpected(Method)
	 */
	public boolean wentUnexpected(String mname) throws NoSuchElementException {
		Method m = this.methods.get(mname);
		if (m == null) {
			throw new NoSuchElementException("missing method '" + mname + "' in my methods: " + this.methods.keySet());
		}
		return this.results.get(m).badResult();
	}
	
	/**
	 * returns <code>true</code> if the {@link Result} of the method is a {@link Result#badResult() bad Result} and <code>false</code> if not. The {@link Result} will be searched in the
	 * {@link #results} map
	 * 
	 * @param m
	 *            the {@link Method} of which the {@link Result} is checked
	 * @return <code>true</code> if the {@link Result} of the method is a {@link Result#badResult() bad Result} and <code>false</code> if not
	 * @throws NoSuchElementException
	 *             if there is no mapping in the {@link #results} for the given {@link Method}
	 * @see #wentUnexpected(String)
	 * @see #wentExpected(String)
	 * @see #wentExpected(Method)
	 */
	public boolean wentUnexpected(Method m) throws NoSuchElementException {
		Result res = this.results.get(m);
		if (res == null) {
			throw new NoSuchElementException("missing method '" + m.getName() + "' in my methods: " + this.results.keySet());
		}
		return res.badResult();
	}
	
	/**
	 * returns <code>true</code> when at least one {@link Result} is {@link Result#badResult() bad}.
	 * 
	 * @return <code>true</code> when at least one {@link Result} is {@link Result#badResult() bad}.
	 * @see #wentExpected()
	 */
	public boolean wentUnexpected() {
		for (Result r : this.results.values()) {
			if (r.badResult()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * returns the number of {@link Result}s saved in this {@link CheckResult}.
	 * 
	 * @return
	 * @implSpec <code>{@link #results}.{@link Map#size() size()}</code>
	 */
	public int cehckedCount() {
		return this.results.size();
	}
	
	/**
	 * returns the {@link Result} from the {@link #results} by the {@link Method} from the {@link #methods} by it's name
	 * 
	 * @param mname
	 *            the name of the {@link Method}
	 * @return the {@link Result} saved in the {@link #results} by the {@link Method} saved in the {@link #methods}
	 * @throws NoSuchElementException
	 *             if there is no {@link Method} in the {@link #methods} for the name
	 * @see #getResult(Method)
	 * @see #getMethod(String)
	 */
	public Result getResult(String mname) throws NoSuchElementException {
		Method m = methods.get(mname);
		if (m == null) throw new NoSuchElementException("missing method '" + mname + "' in my methods: " + this.methods.keySet());
		return results.get(m);
	}
	
	/**
	 * returns all {@link Method}s, which {@link #wentUnexpected(Method) went unexpected}, with their {@link Throwable}s.
	 * 
	 * @return all {@link Method}s, which {@link #wentUnexpected(Method) went unexpected}, with their {@link Throwable}s.
	 */
	public Map <Method, Throwable> allUnexpected() {
		Map <Method, Throwable> ret = new HashMap <>();
		results.forEach((m, r) -> {
			if (r.badResult()) {
				ret.put(m, r.getErr());
			}
		});
		return ret;
	}
	
	/**
	 * returns all {@link Method}s, which {@link #wentExpected(Method) went expected}, with their returned {@link Object}s.
	 * 
	 * @return all {@link Method}s, which {@link #wentExpected(Method) went expected}, with their returned {@link Object}s.
	 */
	public Map <Method, Object> allExpected() {
		Map <Method, Object> ret = new HashMap <>();
		results.forEach((m, r) -> {
			if (r.goodResult()) {
				ret.put(m, r.getResult());
			}
		});
		return ret;
	}
	
	/**
	 * returns all {@link Method}s, with their {@link Result}s.
	 * 
	 * @return all {@link Method}s, with their {@link Result}s.
	 */
	public Map <Method, Result> allCecked() {
		Map <Method, Result> ret = new HashMap <>();
		ret.putAll(results);
		return ret;
	}
	
	/**
	 * iterates though {@link #results} and invokes on every {@link Result} the consumer
	 * 
	 * @param c
	 *            what should be done for all
	 * @see Map#forEach(BiConsumer)
	 */
	public void forAll(BiConsumer <Method, Result> c) {
		results.forEach(c);
	}
	
	/**
	 * iterates though {@link #results} and invokes on every {@link Result#badResult() unexpected} {@link Result} the consumer
	 * 
	 * @param c
	 *            what should be done for all {@link Result#badResult() unexpected} {@link Result}s
	 * @see #forAllExpected(BiConsumer)
	 */
	public void forAllUnexpected(BiConsumer <Method, Throwable> c) {
		results.forEach((m, t) -> {
			if (t.badResult()) c.accept(m, t.getErr());
		});
	}
	
	/**
	 * iterates though {@link #results} and invokes on every {@link Result#goodResult() expected} {@link Result} the consumer
	 * 
	 * @param c
	 *            what should be done for all {@link Result#goodResult() expected} {@link Result}s
	 * @see #forAllUnexpected(BiConsumer)
	 */
	public void forAllExpected(BiConsumer <Method, Object> c) {
		results.forEach((m, r) -> {
			if (r.badResult()) c.accept(m, r.getResult());
		});
	}
	
	/**
	 * prints this {@link CheckResult} on the {@link System#out default out} with an indention of {@code 4}
	 */
	public void print() {
		print(System.out, 4);
	}
	
	/**
	 * prints this {@link CheckResult} on the {@link System#out default out} with the given indention
	 * 
	 * @param indention
	 *            the indention of spaces (' ') of the checks
	 */
	public void print(int indention) {
		print(System.out, indention);
	}
	
	/**
	 * prints this {@link CheckResult} on the given {@link PrintStream} with an indention of {@code 4}
	 * 
	 * @param out
	 *            the {@link PrintStream} to use to print this {@link CheckResult}
	 */
	public void print(final PrintStream out) {
		print(out, 4);
	}
	
	/**
	 * prints this {@link CheckResult} on the given {@link PrintStream} with the given indention
	 * 
	 * @param out
	 *            the {@link PrintStream} to use to print this {@link CheckResult}
	 * @param indention
	 *            the indention of spaces (' ') of the checks
	 */
	public void print(final PrintStream out, int indention) {
		final String start;
		{
			char[] zw = new char[indention];
			Arrays.fill(zw, ' ');
			start = new String(zw);
		}
		StringBuilder str = new StringBuilder(System.lineSeparator());
		TwoInts cnt = new TwoInts();
		this.results.forEach((m, r) -> {
			str.append(start).append(m.getName()).append('(');
			Class <?>[] params = m.getParameterTypes();
			if (params.length > 0) {
				str.append(params[0].getSimpleName());
			}
			for (int i = 1; i < params.length; i ++ ) {
				str.append(',').append(params[i].getSimpleName());
			}
			str.append(')').append(" -> ");
			cnt.a ++ ;
			if (r.goodResult()) {
				cnt.b ++ ;
				str.append("good: ");
				if (m.getReturnType() == void.class) {
					str.append("good");
				} else {
					str.append("good: ");
					if (r.getResult() == null) {
						str.append("null");
					} else {
						str.append(r.toSimpleString());
					}
				}
			} else {
				str.append("bad: ");
				str.append(r.toSimpleString());
			}
			str.append(System.lineSeparator());
		});
		str.append(System.lineSeparator());
		str.insert(0, cnt.a == cnt.b ? "good" : "bad");
		str.insert(0, " -> ");
		str.insert(0, cnt.a);
		str.insert(0, '/');
		str.insert(0, cnt.b);
		str.insert(0, "RESULT: ");
		out.print(str);
	}
	
	/**
	 * creates a detailed string which contains all {@link Result}s of this {@link CheckResult} indented with spaces by the doubled given indention.<br>
	 * The given name will be set to the beginning and be indented with spaces by the given indention.<br>
	 * The given {@code counter} will be modified:<br>
	 * {@link TwoInts#a} will be incremented by the number of {@link Result}s in this {@link CheckResult}<br>
	 * {@link TwoInts#b} will be incremented by the number of {@link Result#getResult() good Result}s in this {@link CheckResult}
	 * 
	 * @param builder
	 *            the {@link StringBuilder} to be filled with a detailed string representation of this {@link CheckResult}
	 * @param name
	 *            the name of this {@link CheckResult}
	 * @param counter
	 *            the {@link TwoInts} will be changed as above specified: the {@link Result}number will be added to {@link TwoInts#a a} and the {@link Result#getResult() good Result} number will be
	 *            added to {@link TwoInts#b b}
	 * @param classIndention
	 *            the indention in spaces of this {@link CheckResult}
	 * @param methodIndention
	 *            the indention in spaces for the methods of this {@link CheckResult}
	 */
	public void toString(StringBuilder builder, String name, TwoInts counter, int classIndention, int methodIndention) {
		final char[] start;
		final char[] doubleStart;
		start = new char[classIndention];
		Arrays.fill(start, ' ');
		doubleStart = new char[methodIndention];
		Arrays.fill(doubleStart, ' ');
		int startIndex = builder.length();
		TwoInts cnt = new TwoInts();
		this.results.forEach((m, r) -> {
			builder.append(doubleStart).append(m.getName()).append('(');
			Class <?>[] params = m.getParameterTypes();
			if (params.length > 0) {
				builder.append(params[0].getSimpleName());
			}
			for (int i = 1; i < params.length; i ++ ) {
				builder.append(',').append(params[i].getSimpleName());
			}
			builder.append(')').append(" -> ");
			cnt.a ++ ;
			if (r.goodResult()) {
				cnt.b ++ ;
				if (m.getReturnType() == void.class) {
					builder.append("good");
				} else {
					builder.append("good: ");
					if (r.getResult() == null) {
						builder.append("null");
					} else {
						builder.append(r.toSimpleString());
					}
				}
			} else {
				builder.append("bad: ");
				builder.append(r.toSimpleString());
			}
			builder.append(System.lineSeparator());
		});
		builder.insert(startIndex, System.lineSeparator());
		builder.insert(startIndex, cnt.a == cnt.b ? "good" : "bad");
		builder.insert(startIndex, " -> ");
		builder.insert(startIndex, cnt.a);
		builder.insert(startIndex, '/');
		builder.insert(startIndex, cnt.b);
		builder.insert(startIndex, ": ");
		builder.insert(startIndex, name);
		builder.insert(startIndex, start);
		counter.a += cnt.a;
		counter.b += cnt.b;
	}
	
	/**
	 * prints a all results in a detailed format this just uses {@link #detailedPrint(PrintStream, int, int)} with a {@code doubleIndention} which is <code>(indention * 2)}</code><br>
	 * the time will be in the first line, which will NOT be intended!
	 * 
	 * @param out
	 *            the {@link PrintStream} on which the {@link Result}s should be printed
	 * @param indention
	 *            the normal indention
	 * @see #detailedPrintUnexpected(PrintStream, int, int)
	 */
	public void detailedPrint(PrintStream out, int indention) {
		detailedPrint(out, indention, indention << 1);
	}
	
	/**
	 * prints a all results in a detailed format<br>
	 * the time will be in the first line, which will NOT be intended!
	 * 
	 * @param out
	 *            the {@link PrintStream} on which the {@link Result}s should be printed
	 * @param indention
	 *            the normal indention
	 * @param doubleIndented
	 *            the complete indention for the doubled indented lines
	 */
	public void detailedPrint(PrintStream out, int indention, int doubleIndented) {
		String indent = null;
		String dindent = null;
		{
			StringBuilder zw = new StringBuilder(Math.max(indention, doubleIndented));
			for (int i = 0; i < Math.min(indention, doubleIndented); i ++ ) {
				zw.append(' ');
			}
			if (indention < doubleIndented) {
				indent = zw.toString();
			} else {
				dindent = zw.toString();
			}
			for (int i = 0; i < Math.max(doubleIndented, indention); i ++ ) {
				zw.append(' ');
			}
			if (doubleIndented < indention) {
				indent = zw.toString();
			} else {
				dindent = zw.toString();
			}
		}
		int cnt = 0;
		for (Result r : this.results.values()) {
			if (r.goodResult()) {
				cnt ++ ;
			}
		}
		out.println("time=" + (end - start) + "ms " + cnt + "/" + this.results.size());
		final String i = indent, di = dindent;
		this.forAll((m, r) -> {
			r.detailedPrint(out, m, i, di);
		});
	}
	
	/**
	 * prints a all unexpected results with their stack traces this just uses {@link #detailedPrintUnexpected(PrintStream, int, int)} with a {@code doubleIndention} which is
	 * <code>(indention * 2)}</code><br>
	 * the time will be in the first line, which will NOT be intended!
	 * 
	 * @param out
	 *            the {@link PrintStream} on which the {@link Result#badResult() bad} {@link Result}s should be printed
	 * @param indention
	 *            the normal indention
	 * @see #detailedPrintUnexpected(PrintStream, int, int)
	 */
	public void detailedPrintUnexpected(PrintStream out, int indention) {
		detailedPrintUnexpected(out, indention, indention << 1);
	}
	
	/**
	 * prints a all unexpected results with their stack traces<br>
	 * the time will be in the first line, which will NOT be intended!
	 * 
	 * @param out
	 *            the {@link PrintStream} on which the {@link Result#badResult() bad} {@link Result}s should be printed
	 * @param indention
	 *            the normal indention
	 * @param doubleIndented
	 *            the complete indention for the doubled indented lines
	 */
	public void detailedPrintUnexpected(PrintStream out, int indention, int doubleIndented) {
		String indent;
		String dindent;
		{
			StringBuilder zw = new StringBuilder(Math.max(indention, doubleIndented));
			for (int i = 0; i < indention; i ++ ) {
				zw.append(' ');
			}
			indent = zw.toString();
			for (int i = 0; i < doubleIndented; i ++ ) {
				zw.append(' ');
			}
			if (doubleIndented < indention) {
				dindent = indent.substring(indention - doubleIndented);
			} else {
				dindent = zw.toString();
			}
		}
		out.println("time=" + (end - start) + "ms");
		this.forAll((m, r) -> {
			if (r.badResult()) {
				r.detailedPrint(out, m, indent, dindent);
			}
		});
	}
	
	/**
	 * creates a detailed string which contains all {@link Result}s of this {@link CheckResult} indented with spaces by the doubled given indention.<br>
	 * The given name will be set to the beginning and be indented with spaces by the given indention.<br>
	 * The given {@code counter} will be modified:<br>
	 * {@link TwoInts#a} will be incremented by the number of {@link Result}s in this {@link CheckResult}<br>
	 * {@link TwoInts#b} will be incremented by the number of {@link Result#getResult() good Result}s in this {@link CheckResult}
	 * 
	 * @param builder
	 *            the {@link StringBuilder} to be filled with a detailed string representation of this {@link CheckResult}
	 * @param name
	 *            the name of this {@link CheckResult}
	 * @param counter
	 *            the {@link TwoInts} will be changed as above specified: the {@link Result}number will be added to {@link TwoInts#a a} and the {@link Result#getResult() good Result} number will be
	 *            added to {@link TwoInts#b b}
	 * @param indention
	 *            the indention in spaces of this {@link CheckResult} and the half indention in spaces for the methods of this {@link CheckResult}
	 */
	public void toString(StringBuilder builder, String name, TwoInts counter, int indention) {
		toString(builder, name, counter, indention, indention << 1);
	}
	
	/**
	 * creates a detailed string which contains all {@link Result}s of this {@link CheckResult} indented with spaces by the doubled given indention.<br>
	 * The given name will be set to the beginning and be indented with spaces by the given indention.<br>
	 * The given {@code counter} will be modified:<br>
	 * {@link TwoInts#a} will be incremented by the number of {@link Result}s in this {@link CheckResult}<br>
	 * {@link TwoInts#b} will be incremented by the number of {@link Result#getResult() good Result}s in this {@link CheckResult}
	 * 
	 * @param name
	 *            the name of this {@link CheckResult}
	 * @param counter
	 *            the {@link TwoInts} will be changed as above specified: the {@link Result}number will be added to {@link TwoInts#a a} and the {@link Result#getResult() good Result} number will be
	 *            added to {@link TwoInts#b b}
	 * @param indention
	 *            the indention in spaces of this {@link CheckResult} and the half indention in spaces for the methods of this {@link CheckResult}
	 * @return creates a detailed {@link String} representing this {@link CheckResult}
	 * @implNote it works like <code>{{@link StringBuilder} zw = new {@link StringBuilder#StringBuilder() StringBuilder()}; {@link #toString(StringBuilder, String, TwoInts, int)
	 *           cr.toString(zw,name,counter,indention)}; return {@link StringBuilder#toString() zw.toString()};} when {@code cr} is the {@link CheckResult} and the other params are the same as by
	 *           calling this method
	 * @see #toString(StringBuilder, String, TwoInts, int)
	 */
	public String toString(String name, TwoInts counter, int indention) {
		StringBuilder str = new StringBuilder();
		toString(str, name, counter, indention);
		return str.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		toString(str, "<classname>", new TwoInts(), 0, 4);
		return str.toString();
	}
	
}

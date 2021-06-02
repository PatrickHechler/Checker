package de.hechler.patrick.zeugs.check;

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
 * The {@link Result}s are accessible with the {@link Method}s or
 * 
 * @author Patrick
 *
 */
public final class CheckResult {
	
	/**
	 * this map saves all {@link Method}s with their names as keys (and their {@link Parameter params})
	 */
	private Map <String, Method> methods = new HashMap <>();
	/**
	 * this map contains all {@link Result} and the {@link Method}, from which the results are
	 */
	private Map <Method, Result> results = new HashMap <>();
	
	
	
	/**
	 * this method saves the {@link Result} with the {@link Method} in the {@link #results} and the method with its {@link Method#getName() name} (and {@link Method#getParameters() params}) in the
	 * {@link #methods}.<br>
	 * 
	 * When the {@link Method} has no params (<code>{@link Method#getParameterCount()} == 0</code>) there will be two links created to the {@link Method} in {@link #methods}: one wit only the
	 * {@link Method#getName() name} and one with the {@link Method#getName() name} and the braces '()'.<br>
	 * 
	 * When the {@link Method} has params (<code>{@link Method#getParameterCount()} > 0</code>) there will be one link in the {@link #methods}: it will start with the {@link Method#getName() name} and
	 * then the braces. between the braces will be the {@link Method#getParameters() params} with only their {@link Parameter#getType() type} as fully qualifying name. if a param is a
	 * {@link Parameter#isVarArgs() varArg}, it will have '...' at the end and a second with '[]' at the end all other arrays will have only '[]' at the end. The {@link Method#getParameters() params}
	 * will be separated by <code>', '</code>.
	 * 
	 * @param m
	 * @param value
	 */
	void set(Method m, Result value) {
		if (m.getParameterCount() > 0) {
			StringBuilder name = new StringBuilder(m.getName()).append('(');
			Parameter[] clss = m.getParameters();
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
			this.methods.put(name.append(')').toString(), m);
			this.results.put(m, value);
			if (clss[clss.length - 1].isVarArgs()) {
				name = new StringBuilder(m.getName()).append('(');
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
				this.methods.put(name.append(')').toString(), m);
			}
		} else {
			this.methods.put(m.getName() + "()", m);
			this.methods.put(m.getName(), m);
			this.results.put(m, value);
		}
	}
	
	/**
	 * returns the {@link Result} from the {@link #results} by the {@link Method}
	 * 
	 * @param m
	 *            the {@link Method}
	 * @return the {@link Result} from the {@link #results} by the {@link Method}
	 * @throws NoSuchElementException
	 *             if there is no {@link Result} in the {@link #results} for the {@link Method}
	 * @see {@link #getResult(String)}
	 */
	public Result getResult(Method m) throws NoSuchElementException {
		Result res = results.get(m);
		if (res == null) throw new NoSuchElementException("missing method '" + m.getName() + "' in my methods: " + this.results.keySet());
		return res;
	}
	
	/**
	 * returns the {@link Method} of the {@link #methods} by it's name
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
		IntInt cnt = new IntIntImpl();
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
			cnt.incA();
			if (r.goodResult()) {
				cnt.incB();
				str.append("good: ");
				if (m.getReturnType() == Void.TYPE) {
					str.append("good");
				} else {
					str.append("good: ");
					str.append(r.getResult());
				}
			} else {
				str.append("bad: ");
				str.append(r.toSimpleString());
			}
			str.append(System.lineSeparator());
		});
		str.append(System.lineSeparator());
		str.insert(0, cnt.bothSame() ? "good" : "bad");
		str.insert(0, " -> ");
		str.insert(0, cnt.getA());
		str.insert(0, '/');
		str.insert(0, cnt.getB());
		str.insert(0, "RESULT: ");
		out.print(str);
	}
	
	/**
	 * creates a detailed {@link String} which contains all {@link Result}s of this {@link CheckResult} indented by the doubled given indention.<br>
	 * The given name will be set to the beginning and will be indented by the given indention.<br>
	 * 
	 * The given {@code counter} will be modified:<br>
	 * {@link IntIntImpl#a} will be incremented by the number of {@link Result}s in this {@link CheckResult}<br>
	 * {@link IntIntImpl#b} will be incremented by the number of {@link Result#getResult() good Result}s in this {@link CheckResult}
	 * 
	 * @param name
	 *            the name of this {@link CheckResult}
	 * @param counter
	 *            the {@link IntIntImpl} will be changed as above specified: the {@link Result}number will be added to {@link IntIntImpl#a a} and the {@link Result#getResult() good Result} number will be
	 *            added to {@link IntIntImpl#b b}
	 * @param indention
	 *            the indention of this {@link CheckResult} and the half indention for the methods of this {@link CheckResult}
	 * @return creates a detailed {@link String} representing this {@link CheckResult}
	 */
	public String toString(String name, IntInt counter, int indention) {
		final char[] start;
		final char[] doubleStart;
		start = new char[indention];
		Arrays.fill(start, ' ');
		doubleStart = new char[indention << 1];
		Arrays.fill(doubleStart, ' ');
		StringBuilder str = new StringBuilder();
		IntIntImpl cnt = new IntIntImpl();
		this.results.forEach((m, r) -> {
			boolean b = r.goodResult();
			str.append(doubleStart).append(m.getName()).append('(');
			Class <?>[] params = m.getParameterTypes();
			if (params.length > 0) {
				str.append(params[0].getSimpleName());
			}
			for (int i = 1; i < params.length; i ++ ) {
				str.append(',').append(params[i].getSimpleName());
			}
			str.append(')').append(" -> ");
			cnt.incA();
			if (b) {
				cnt.incB();
				;
				if (m.getReturnType() == Void.TYPE) {
					str.append("good");
				} else {
					str.append("good: ");
					str.append(r.getResult());
				}
			} else {
				str.append("bad: ");
				str.append(r.toSimpleString());
			}
			str.append(System.lineSeparator());
		});
		str.insert(0, System.lineSeparator());
		str.insert(0, cnt.bothSame() ? "good" : "bad");
		str.insert(0, " -> ");
		str.insert(0, cnt.getA());
		str.insert(0, '/');
		str.insert(0, cnt.getB());
		str.insert(0, ": ");
		str.insert(0, name);
		str.insert(0, start);
		counter.addBoth(cnt);
		return str.toString();
	}
	
	public String toString(StringBuilder builder, String name, IntInt counter, int indention) {
		final char[] start;
		final char[] doubleStart;
		start = new char[indention];
		Arrays.fill(start, ' ');
		doubleStart = new char[indention << 1];
		Arrays.fill(doubleStart, ' ');
		int startIndex = builder.length();
		IntIntImpl cnt = new IntIntImpl();
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
			cnt.incA();
			if (r.goodResult()) {
				cnt.incB();
				if (m.getReturnType() == Void.TYPE) {
					builder.append("good");
				} else {
					builder.append("good: ");
					builder.append(r.getResult());
				}
			} else {
				builder.append("bad: ");
				builder.append(r.toSimpleString());
			}
			builder.append(System.lineSeparator());
		});
		builder.insert(startIndex, System.lineSeparator());
		builder.insert(startIndex, cnt.bothSame() ? "good" : "bad");
		builder.insert(startIndex, " -> ");
		builder.insert(startIndex, cnt.getA());
		builder.insert(startIndex, '/');
		builder.insert(startIndex, cnt.getB());
		builder.insert(startIndex, ": ");
		builder.insert(startIndex, name);
		builder.insert(startIndex, start);
		counter.addBoth(cnt);
		return builder.toString();
	}
	
}

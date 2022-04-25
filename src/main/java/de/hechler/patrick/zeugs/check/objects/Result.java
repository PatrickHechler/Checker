package de.hechler.patrick.zeugs.check.objects;

import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * saves the Result of a {@link Method#invoke(Object, Object...) Method call}.
 * <p>
 * a {@link Result} is a {@link #goodResult()} when the method returned normally.<br>
 * the normal return value will be saved in the field {@link #result} and can be accessed via
 * {@link #getResult()}. <br>
 * a {@link Result} is a {@link #badResult()} when the method ended by throwing a
 * {@link Throwable}.<br>
 * the {@link Throwable} will be saved in the field {@link #err} and can be accessed via
 * {@link #getErr()}.
 * <p>
 * additional to the return value or the thrown value the {@link Result} also saves the execution
 * time, needed for the invoked method.<br>
 * the start and end time can be accessed via {@link #start} and {@link #end}.<br>
 * to get the time, which had been needed to execute the method the {@link #getTime()} method can be
 * used.
 * 
 * @author Patrick
 */
public final class Result {
	
	/**
	 * the method which should be checked for this result<br>
	 */
	public final Method met;
	/**
	 * this saves the return value (or <code>null</code> if it was a <code>void</code> method)<br>
	 * if the method did not end normally (it throwed an {@link Throwable}) this value will be
	 * <code>null</code>
	 */
	public final Object result;
	/**
	 * here will be the {@link Throwable} which was thrown by the called {@link Method}<br>
	 * if the method ended normally this value will be <code>null</code>
	 */
	private final Throwable err;
	/**
	 * the time when this Check startet
	 */
	public final long start;
	/**
	 * the time when the {@link Checker} finished checking for this {@link Result}.
	 */
	public final long end;
	
	
	
	/**
	 * creates a {@link Result} with the {@link Object} as return value of a {@link Method}
	 * 
	 * @param e
	 *            the return value of the executed {@link Method}
	 */
	public Result(Method met, Object e, long start, long end) {
		this.met = met;
		this.result = e;
		this.err = null;
		this.start = start;
		this.end = end;
	}
	
	/**
	 * creates a {@link Result} with the {@link Throwable} saved as error of a called {@link Method}
	 * 
	 * @param t
	 *            the {@link Throwable} thrown while the {@link Method} was called
	 */
	public Result(Method met, Throwable t, long start, long end) {
		this.met = met;
		this.result = null;
		this.err = t;
		this.start = start;
		this.end = end;
	}
	
	
	
	/**
	 * returns the total time needed for this {@link Result}
	 * 
	 * @return the total time needed for this {@link Result}
	 */
	public long getTime() { return this.end - this.start; }
	
	/**
	 * if this is a 'good' {@link Result} this {@link Method} will return <code>true</code>.<br>
	 * This is a 'good' {@link Result} if no {@link Throwable} was thrown during the method call or in
	 * other words if <code>{@link #err} == null</code> is <code>true</code>
	 * 
	 * @return <code>true</code> if this {@link Result} is a 'good' {@link Result}, <code>false</code>
	 *             if not
	 */
	public boolean goodResult() {
		return this.err == null;
	}
	
	/**
	 * if this is a 'bad' {@link Result} this {@link Method} will return <code>true</code>.<br>
	 * This is a 'bad' {@link Result} if a {@link Throwable} was thrown during the method call or in
	 * other words if <code>{@link #err} != null</code> is <code>true</code>
	 * 
	 * @return <code>true</code> if this {@link Result} is a 'bad' {@link Result}, <code>false</code> if
	 *             not
	 */
	public boolean badResult() {
		return this.err != null;
	}
	
	/**
	 * returns the saved return value of the {@link Method}.<br>
	 * if this is no {@link #goodResult() good Result} it will throw a {@link NoSuchElementException}
	 * 
	 * @return the saved return value of the {@link Method}
	 * @throws NoSuchElementException
	 *             if this is a {@link #badResult() bad Result}
	 */
	public Object getResult() throws NoSuchElementException {
		if (this.err != null) {
			throw new NoSuchElementException("this is no good result!");
		} else return this.result;
	}
	
	/**
	 * returns the saved {@link #err} of the {@link Method}.<br>
	 * if this is no {@link #badResult() bad Result} it will throw a {@link NoSuchElementException}
	 * 
	 * @return the saved {@link Throwable} of the {@link Method}
	 * @throws NoSuchElementException
	 *             if this is a {@link #goodResult() good Result}
	 */
	public Throwable getErr() throws NoSuchElementException {
		if (this.err == null) {
			throw new NoSuchElementException("this is no bad result!");
		} else return err;
	}
	
	@Override
	public String toString() {
		if (this.err != null) {
			return "error[" + this.err.getClass().getName() + ':' + this.err.getMessage() + ']';
		} else if (this.result != null) {
			return "return[" + this.result.getClass() + ':' + this.result + ']';
		} else {
			return "returned null or void";
		}
	}
	
	/**
	 * prints a detailed represention of this {@link Result}, which contains even some informations
	 * which are not saved by this {@link Result} (then the information comes form the
	 * {@link Method} {@code me}), when {@code me} is <code>null</code> these informations are skipped
	 * 
	 * @param out
	 *            the {@link PrintStream} on which this {@link Result} should be printed
	 * @param me
	 *            the {@link Method} of this {@link Result} with additional information. ignored if
	 *            <code>null</code>
	 * @param indent
	 *            the normal indention
	 * @param dindent
	 *            the complete indention of double indented lines
	 */
	public void detailedPrint(PrintStream out, Method me, String indent, String dindent) {
		if (me != null) {
			out.print(indent + "checked method: " + me.getName() + "(");
			Parameter[] params = me.getParameters();
			if (params.length > 0) {
				Annotation[] a = params[0].getAnnotations();
				for (int i = 0; i < a.length; i ++ ) {
					Class <? extends Annotation> acls = a[i].annotationType();
					out.print("@" + acls.getSimpleName() + ' ');
				}
				out.print(params[0].getType().getName());
				if (params[0].isNamePresent()) {
					out.print(" " + params[0].getName());
				}
				for (int i = 1; i < params.length; i ++ ) {
					out.print(", ");
					a = params[i].getAnnotations();
					for (int ii = 0; ii < a.length; ii ++ ) {
						Class <? extends Annotation> acls = a[ii].annotationType();
						out.print("@" + acls.getSimpleName() + ' ');
					}
					Class <?> type = params[i].getType();
					String cn = type.getCanonicalName();
					out.print(cn == null ? type.getName() : cn);
					if (params[i].isNamePresent()) {
						out.print(" " + params[i].getName());
					}
				}
			}
			Class <?> zwcls = me.getReturnType();
			String cn = zwcls.getCanonicalName();
			out.print(") -> ");
			if (err == null) {
				if (me.getReturnType() == void.class) {
					out.println("void");
				} else if (this.result == null) {
					out.println("returned " + cn + ": null");
				} else {
					out.println("returned " + cn + ": " + resultToString());
				}
				out.println(indent + "time=" + (this.end - this.start) + "ms");
				return;
			} else {
				out.println("should return " + cn);
			}
		} else if (this.err == null) {
			if (this.result != null) {
				out.println("returned void or null");
			} else {
				out.println("returned: " + resultToString());
			}
			out.println(dindent + "time=" + (this.end - this.start) + "ms");
			return;
		}
		out.println(indent + "time=" + (this.end - this.start) + "ms");
		// err != null
		printErr(out, indent, dindent, err);
	}
	
	private static void printErr(PrintStream out, String indent, String dindent, Throwable t) {
		Class <?> cls = t.getClass();
		String cn = cls.getCanonicalName();
		out.println(indent + "exception: " + (cn == null ? cls.getName() : cn));
		out.println(indent + "message: " + t.getMessage());
		out.println(indent + "localized message: " + t.getLocalizedMessage());
		out.println(indent + "exeption to string: " + t.toString());
		out.println(indent + "stack trace:");
		StackTraceElement[] st = t.getStackTrace();
		for (int i = 0; i < st.length; i ++ ) {
			StackTraceElement ste = st[i];
			out.println(dindent + "at " + ste.toString());
		}
		Throwable cause = t.getCause();
		if (cause != null) {
			out.println(indent + "caused by:");
			printErr(out, indent + "    ", dindent + "    ", cause);
		}
		Throwable[] sup = t.getSuppressed();
		if (sup != null && sup.length > 0) {
			out.println(indent + "suppressed: " + sup.length);
			for (int i = 0; i < sup.length; i ++ ) {
				out.println(indent + "suppressed [" + i + "]:");
				printErr(out, indent + "    ", dindent + "    ", sup[i]);
			}
		}
	}
	
	/**
	 * this method returns a {@link String} with the {@link #err}or or {@link #result} of this
	 * {@link Result}. There will be no Prefix or Postfix
	 * 
	 * @return a {@link String} with the {@link #err} or {@link #result} of this {@link Result} without
	 *             any Prefix or Postfix
	 */
	public String toSimpleString() {
		if (this.err != null) {
			return this.err.toString();
		} else if (this.result != null) {
			return resultToString();
		} else {
			return "null or void";
		}
	}
	
	private String resultToString() {
		Class <? extends Object> cls = this.result.getClass();
		if ( !cls.isArray()) return this.result.toString();
		Class <?> ct = cls.getComponentType();
		if ( !ct.isPrimitive()) return Arrays.deepToString((Object[]) this.result);
		else if (ct == long.class) return Arrays.toString((long[]) this.result);
		else if (ct == int.class) return Arrays.toString((int[]) this.result);
		else if (ct == short.class) return Arrays.toString((short[]) this.result);
		else if (ct == byte.class) return Arrays.toString((byte[]) this.result);
		else if (ct == boolean.class) return Arrays.toString((boolean[]) this.result);
		else if (ct == char.class) return Arrays.toString((char[]) this.result);
		else if (ct == double.class) return Arrays.toString((double[]) this.result);
		else if (ct == float.class) return Arrays.toString((float[]) this.result);
		else throw new InternalError("unknown primitive type: " + ct);
	}
	
}

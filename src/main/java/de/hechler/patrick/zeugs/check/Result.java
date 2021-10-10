package de.hechler.patrick.zeugs.check;

import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.NoSuchElementException;

/**
 * saves the Result of a {@link Method#invoke(Object, Object...) Method call}. This is either an {@link Object} <code>return</code> result or an {@link Throwable} thrown result.
 * 
 * @author Patrick
 *
 */
public final class Result {
	
	/**
	 * this saves the return value (or <code>null</code> if it was a <code>void</code> method)<br>
	 * if the method did not end normally (it throwed an {@link Throwable}) this value will be <code>null</code>
	 */
	private final Object result;
	/**
	 * here will be the {@link Throwable} which was thrown by the called {@link Method}<br>
	 * if the method ended normally this value will be <code>null</code>
	 */
	private final Throwable err;
	/**
	 * the time when this {@link Result} was created
	 */
	private final long start = System.currentTimeMillis();
	/**
	 * the time when the {@link Checker} finished checking for this {@link Result}.
	 */
	private long end = -1L;
	
	
	
	/**
	 * sets the end time of this {@link Result}
	 * 
	 * @param end
	 *            the end time
	 */
	void setEnd(long end) {
		this.end = end;
	}
	
	/**
	 * returns the end time of this {@link Result}
	 * 
	 * @return the end time of this {@link Result}
	 */
	public long getEnd() {
		return end;
	}
	
	/**
	 * returns the total time needed for this {@link Result}
	 * 
	 * @return the total time needed for this {@link Result}
	 */
	public long getTime() {
		return end - start;
	}
	
	/**
	 * creates a {@link Result} with the {@link Object} as return value of a {@link Method}
	 * 
	 * @param e
	 *            the return value of the executed {@link Method}
	 */
	public Result(Object e) {
		result = e;
		err = null;
	}
	
	/**
	 * creates a {@link Result} with the {@link Throwable} saved as error of a called {@link Method}
	 * 
	 * @param t
	 *            the {@link Throwable} thrown while the {@link Method} was called
	 */
	public Result(Throwable t) {
		result = null;
		err = t;
	}
	
	
	
	/**
	 * if this is a 'good' {@link Result} this {@link Method} will return <code>true</code>.<br>
	 * This is a 'good' {@link Result} if no {@link Throwable} was thrown during the method call or in other words if <code>{@link #err} == null</code> is <code>true</code>
	 * 
	 * @return <code>true</code> if this {@link Result} is a 'good' {@link Result}, <code>false</code> if not
	 */
	public boolean goodResult() {
		return err == null;
	}
	
	/**
	 * if this is a 'bad' {@link Result} this {@link Method} will return <code>true</code>.<br>
	 * This is a 'bad' {@link Result} if a {@link Throwable} was thrown during the method call or in other words if <code>{@link #err} != null</code> is <code>true</code>
	 * 
	 * @return <code>true</code> if this {@link Result} is a 'bad' {@link Result}, <code>false</code> if not
	 */
	public boolean badResult() {
		return err != null;
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
		if (err != null) throw new NoSuchElementException("this is no good result!");
		else return result;
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
		if (err == null) throw new NoSuchElementException("this is no bad result!");
		else return err;
	}
	
	@Override
	public String toString() {
		if (err != null) {
			return "error[" + err.getClass().getName() + ':' + err.getMessage() + ']';
		} else if (result != null) {
			return "return[" + result + ']';
		} else {
			return "returned null or void";
		}
	}
	
	/**
	 * prints a detailed represention of this {@link Result}, which contains even some informations which are not saved by this {@link Result} (then the information comes form the
	 * {@link Method} {@code me}), when {@code me} is <code>null</code> these informations are skipped
	 * 
	 * @param out
	 *            the {@link PrintStream} on which this {@link Result} should be printed
	 * @param me
	 *            the {@link Method} of this {@link Result} with additional information. ignored if <code>null</code>
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
				if (me.getReturnType() == Void.TYPE) {
					out.println("void");
				} else {
					out.println("returned " + cn + ": " + result);
				}
				return;
			} else {
				out.println("should return " + cn);
			}
		} else {
			if (err == null) {
				if (result != null) {
					out.println("returned void or null");
				} else {
					out.println("returned: " + result);
				}
				return;
			}
		}
		out.println(indent + "time=" + (end - start) + "ms");
		// err != null
		Class <?> zwcls = err.getClass();
		String cn = zwcls.getCanonicalName();
		out.println(indent + "exception: " + (cn == null ? zwcls.getName() : cn));
		out.println(indent + "message: " + err.getMessage());
		out.println(indent + "localized message: " + err.getLocalizedMessage());
		out.println(indent + "exeption to string: " + err.toString());
		out.println(indent + "stack trace:");
		StackTraceElement[] st = err.getStackTrace();
		for (int i = 0; i < st.length; i ++ ) {
			StackTraceElement ste = st[i];
			out.println(dindent + "at " + ste.getClassName() + '.' + ste.getMethodName() + '(' + ste.getFileName() + ':' + ste.getLineNumber() + ')');
		}
	}
	
	/**
	 * this method returns a {@link String} with the {@link #err}or or {@link #result} of this {@link Result}. There will be no Prefix or Postfix
	 * 
	 * @return a {@link String} with the {@link #err} or {@link #result} of this {@link Result} without any Prefix or Postfix
	 */
	public String toSimpleString() {
		if (err != null) {
			return err.toString();
		} else if (result != null) {
			return result.toString();
		} else {
			return "null or void";
		}
	}
	
}

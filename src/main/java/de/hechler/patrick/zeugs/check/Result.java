package de.hechler.patrick.zeugs.check;

import java.lang.reflect.Method;
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
	private final Object    result;
	/**
	 * here will be the {@link Throwable} which was thrown by the called {@link Method}<br>
	 * if the method ended normally this value will be <code>null</code>
	 */
	private final Throwable err;
	
	
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
	
	public String toSimpleString() {
		if (err != null) {
			return err.getClass().getName() + ':' + err.getMessage();
		} else if (result != null) {
			return result.toString();
		} else {
			return "null or void";
		}
	}
	
}

package de.hechler.patrick.zeugs.check.exceptions;

import de.hechler.patrick.zeugs.check.objects.Checker;

/**
 * this class is the root exception class for all exception thrown by the
 * {@link Checker} or any other class from this project.<br>
 * the {@link CheckerException} subclasses {@link Error}, so it does not get cough when catching
 * {@link Exception}.
 * 
 * @author Patrick
 */
public class CheckerException extends Error {
	
	/** UID */
	private static final long serialVersionUID = 6260278481954374074L;
	
	/**
	 * creates a new {@link CheckerException} with the given message
	 * 
	 * @param msg the detailed message
	 */
	public CheckerException(String msg) {
		super(msg);
	}
	
	/**
	 * creates a new {@link CheckerException} with the given message and {@code cause}.
	 * 
	 * @param msg the detailed message
	 * @param cause the cause of this {@link CheckerException}
	 */
	public CheckerException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	/**
	 * creates a new {@link CheckerException} with the given {@code cause} and its message.
	 * 
	 * @param cause the cause of this {@link CheckerException}
	 */
	public CheckerException(Throwable cause) {
		super(cause);
	}
	
}

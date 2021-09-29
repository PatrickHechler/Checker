package de.hechler.patrick.zeugs.check.exceptions;


public class CheckerException extends Error {
	
	/** UID */
	private static final long serialVersionUID = 6260278481954374074L;
	
	
	public CheckerException(String msg) {
		super(msg);
	}
	
	public CheckerException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public CheckerException(Throwable cause) {
		super(cause);
	}
	
}

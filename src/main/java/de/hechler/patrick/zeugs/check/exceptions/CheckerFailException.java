package de.hechler.patrick.zeugs.check.exceptions;


public class CheckerFailException extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = -4885385220886815191L;
	
	public CheckerFailException(String msg) {
		super(msg);
	}
	
	public CheckerFailException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public CheckerFailException(Throwable cause) {
		super(cause);
	}
	
}

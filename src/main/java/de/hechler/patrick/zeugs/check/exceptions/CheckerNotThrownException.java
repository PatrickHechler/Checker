package de.hechler.patrick.zeugs.check.exceptions;

public class CheckerNotThrownException extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = 7980255959142064263L;
	
	
	public CheckerNotThrownException(String assertetMsg, Class <? extends Throwable> assertet, String realMsg, Class <? extends Throwable> thrown, boolean exactClass) {
		super("did not throw an " + assertet.getName() + " with msg: '" + assertetMsg + "' exception, but an " + thrown.getName() + " exception with msg: '" + realMsg + "'! exactClass=" + exactClass);
	}
	
	public CheckerNotThrownException(String assertetMsg, Class <? extends Throwable> assertet, String realMsg, Class <? extends Throwable> thrown) {
		super("did not throw an " + assertet.getName() + " with msg: '" + assertetMsg + "' exception, but an " + thrown.getName() + " exception with msg: '" + realMsg + "'!");
	}
	
	public CheckerNotThrownException(Class <? extends Throwable> assertet, Class <? extends Throwable> thrown, boolean exactClass) {
		super("did not throw an " + assertet.getName() + " exception, but an " + thrown.getName() + " exception! exactClass=" + exactClass);
	}
	
	public CheckerNotThrownException(Class <? extends Throwable> assertet, Class <? extends Throwable> thrown) {
		super("did not throw an " + assertet.getName() + " exception, but an " + thrown.getName() + " exception!");
	}
	
	public CheckerNotThrownException(Class <? extends Throwable> assertet) {
		super("did not throw an " + assertet.getName() + " exception!");
	}
	
	public CheckerNotThrownException(String msg, String realMsg, Class <? extends Throwable> thrown) {
		super("did not throw an error with " + msg + ", but a " + thrown.getName() + " with '" + realMsg + "'!");
	}
	
	public CheckerNotThrownException() {
		super("did not throw an exception!");
	}
	
}

package de.hechler.patrick.zeugs.check.exceptions;


public class CheckerNotThrownException extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = 7980255959142064263L;
	
	
	public CheckerNotThrownException(Class <? extends Throwable> assertet, Class <? extends Throwable> thrown, boolean exactClass) {
		super("did not throw an " + assertet.getSimpleName() + " exception, but an " + thrown.getSimpleName() + " exception! exactClass=" + exactClass);
	}
	
	public CheckerNotThrownException(Class <? extends Throwable> assertet, Class <? extends Throwable> thrown) {
		super("did not throw an " + assertet.getSimpleName() + " exception, but an " + thrown.getSimpleName() + " exception!");
	}
	
	public CheckerNotThrownException(Class <? extends Throwable> assertet) {
		super("did not throw an " + assertet.getSimpleName() + " exception!");
	}
	
	public CheckerNotThrownException() {
		super("did not throw an exception!");
	}
	
}

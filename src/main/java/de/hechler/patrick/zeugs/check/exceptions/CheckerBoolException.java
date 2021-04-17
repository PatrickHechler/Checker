package de.hechler.patrick.zeugs.check.exceptions;


public class CheckerBoolException extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = 7604792815650622843L;
	
	
	
	public CheckerBoolException(boolean notAssert) {
		super(notAssert ? "asserted false, but got true!" : "asserted true, but got false");
	}
	
}

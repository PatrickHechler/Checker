package de.hechler.patrick.zeugs.check.exceptions;


public class CheckerNullExeption extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = 3920986078263969681L;
	
	
	
	public CheckerNullExeption() {
		super("it was not null");
	}
	
}

package de.hechler.patrick.zeugs.check.exceptions;


public class CheckerNullExeption extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = 3920986078263969681L;
	
	public String primType;
	
	public CheckerNullExeption() {
		super("it was null");
		primType = null;
	}
	
	public CheckerNullExeption(String primType) {
		super("it was 0");
		this.primType = primType;
	}
	
}

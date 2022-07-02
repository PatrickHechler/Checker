package de.hechler.patrick.zeugs.check.exceptions;


public class CheckerNotEqualsExeption extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = -310962163429806222L;
	
	public final Object a;
	public final Object b;
	
	public CheckerNotEqualsExeption(Object a, Object b, String msg) {
		super(msg);
		this.a = a;
		this.b = b;
	}
	
	public CheckerNotEqualsExeption(Object a, Object b) {
		super("'" + a + "' not equal '" + b + "'");
		this.a = a;
		this.b = b;
	}
	
}

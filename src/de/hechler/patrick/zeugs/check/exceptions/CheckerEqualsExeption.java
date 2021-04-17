package de.hechler.patrick.zeugs.check.exceptions;


public class CheckerEqualsExeption extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = -8988882556462832814L;
	
	
	public final Object a;
	public final Object b;
	
	public CheckerEqualsExeption(Object a, Object b) {
		super(a + " equal " + b);
		this.a = a;
		this.b = b;
	}
	
}

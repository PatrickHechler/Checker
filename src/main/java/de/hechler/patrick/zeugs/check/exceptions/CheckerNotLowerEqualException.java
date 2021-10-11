package de.hechler.patrick.zeugs.check.exceptions;


public class CheckerNotLowerEqualException extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = -1345820270412931652L;
	
	
	public final Object a;
	public final Object b;
	
	public CheckerNotLowerEqualException(byte a, byte b) {
		super(a + " is not lower then or equal to" + b);
		this.a = Byte.valueOf(a);
		this.b = Byte.valueOf(b);
	}
	
	public CheckerNotLowerEqualException(short a, short b) {
		super(a + " is not lower then or equal to" + b);
		this.a = Short.valueOf(a);
		this.b = Short.valueOf(b);
	}
	
	public CheckerNotLowerEqualException(int a, int b) {
		super(a + " is not lower then or equal to" + b);
		this.a = Integer.valueOf(a);
		this.b = Integer.valueOf(b);
	}
	
	public CheckerNotLowerEqualException(long a, long b) {
		super(a + " is not lower then or equal to" + b);
		this.a = Long.valueOf(a);
		this.b = Long.valueOf(b);
	}
	
	public CheckerNotLowerEqualException(float a, float b) {
		super(a + " is not lower then or equal to" + b);
		this.a = Float.valueOf(a);
		this.b = Float.valueOf(b);
	}
	
	public CheckerNotLowerEqualException(double a, double b) {
		super(a + " is not lower then or equal to" + b);
		this.a = Double.valueOf(a);
		this.b = Double.valueOf(b);
	}
	
}

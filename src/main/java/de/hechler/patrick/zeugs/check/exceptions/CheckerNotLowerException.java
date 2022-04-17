package de.hechler.patrick.zeugs.check.exceptions;


public class CheckerNotLowerException extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = -5021811327168300460L;
	
	
	public final Object a;
	public final Object b;
	
	public CheckerNotLowerException(byte a, byte b) {
		super(a + " is not lower than " + b);
		this.a = Byte.valueOf(a);
		this.b = Byte.valueOf(b);
	}
	
	public CheckerNotLowerException(short a, short b) {
		super(a + " is not lower than " + b);
		this.a = Short.valueOf(a);
		this.b = Short.valueOf(b);
	}
	
	public CheckerNotLowerException(int a, int b) {
		super(a + " is not lower than " + b);
		this.a = Integer.valueOf(a);
		this.b = Integer.valueOf(b);
	}
	
	public CheckerNotLowerException(long a, long b) {
		super(a + " is not lower than " + b);
		this.a = Long.valueOf(a);
		this.b = Long.valueOf(b);
	}
	
	public CheckerNotLowerException(float a, float b) {
		super(a + " is not lower than " + b);
		this.a = Float.valueOf(a);
		this.b = Float.valueOf(b);
	}
	
	public CheckerNotLowerException(double a, double b) {
		super(a + " is not lower than " + b);
		this.a = Double.valueOf(a);
		this.b = Double.valueOf(b);
	}

	public CheckerNotLowerException(Object a, Object b) {
		super(a + " is not lower than " + b);
		this.a = a;
		this.b = b;
	}
	
}

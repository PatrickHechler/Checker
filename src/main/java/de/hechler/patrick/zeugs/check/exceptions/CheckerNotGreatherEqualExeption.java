package de.hechler.patrick.zeugs.check.exceptions;


public class CheckerNotGreatherEqualExeption extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = -5697505197711483880L;
	
	
	public final Object a;
	public final Object b;
	
	public CheckerNotGreatherEqualExeption(byte a, byte b) {
		super(a + " is not greather or equal to " + b);
		this.a = Byte.valueOf(a);
		this.b = Byte.valueOf(b);
	}
	
	public CheckerNotGreatherEqualExeption(short a, short b) {
		super(a + " is not greather or equal to " + b);
		this.a = Short.valueOf(a);
		this.b = Short.valueOf(b);
	}
	
	public CheckerNotGreatherEqualExeption(int a, int b) {
		super(a + " is not greather or equal to " + b);
		this.a = Integer.valueOf(a);
		this.b = Integer.valueOf(b);
	}
	
	public CheckerNotGreatherEqualExeption(long a, long b) {
		super(a + " is not greather or equal to " + b);
		this.a = Long.valueOf(a);
		this.b = Long.valueOf(b);
	}
	
	public CheckerNotGreatherEqualExeption(float a, float b) {
		super(a + " is not greather or equal to " + b);
		this.a = Float.valueOf(a);
		this.b = Float.valueOf(b);
	}
	
	public CheckerNotGreatherEqualExeption(double a, double b) {
		super(a + " is not greather or equal to " + b);
		this.a = Double.valueOf(a);
		this.b = Double.valueOf(b);
	}
	
	public CheckerNotGreatherEqualExeption(Object a, Object b) {
		super(a + " is not greather or equal to " + b);
		this.a = a;
		this.b = b;
	}
	
}

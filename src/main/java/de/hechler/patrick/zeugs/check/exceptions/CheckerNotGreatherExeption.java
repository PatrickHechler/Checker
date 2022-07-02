package de.hechler.patrick.zeugs.check.exceptions;


public class CheckerNotGreatherExeption extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = 8978475692696731457L;
	
	
	public final Object a;
	public final Object b;
	
	public CheckerNotGreatherExeption(byte a, byte b) {
		super(a + " is not greather then " + b);
		this.a = Byte.valueOf(a);
		this.b = Byte.valueOf(b);
	}
	
	public CheckerNotGreatherExeption(short a, short b) {
		super(a + " is not greather then " + b);
		this.a = Short.valueOf(a);
		this.b = Short.valueOf(b);
	}
	
	public CheckerNotGreatherExeption(int a, int b) {
		super(a + " is not greather then " + b);
		this.a = Integer.valueOf(a);
		this.b = Integer.valueOf(b);
	}
	
	public CheckerNotGreatherExeption(long a, long b) {
		super(a + " is not greather then " + b);
		this.a = Long.valueOf(a);
		this.b = Long.valueOf(b);
	}
	
	public CheckerNotGreatherExeption(float a, float b) {
		super(a + " is not greather then " + b);
		this.a = Float.valueOf(a);
		this.b = Float.valueOf(b);
	}
	
	public CheckerNotGreatherExeption(double a, double b) {
		super(a + " is not greather then " + b);
		this.a = Double.valueOf(a);
		this.b = Double.valueOf(b);
	}
	
	public CheckerNotGreatherExeption(Object a, Object b) {
		super(a + " is not greather then " + b);
		this.a = a;
		this.b = b;
	}
	
}

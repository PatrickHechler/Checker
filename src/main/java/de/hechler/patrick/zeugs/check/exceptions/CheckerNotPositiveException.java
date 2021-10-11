package de.hechler.patrick.zeugs.check.exceptions;


public class CheckerNotPositiveException extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = -5393001837820530120L;
	
	
	public final Object num;
	
	public CheckerNotPositiveException(byte num) {
		super(num + " is not positive!");
		this.num = Byte.valueOf(num);
	}
	
	public CheckerNotPositiveException(short num) {
		super(num + " is not positive!");
		this.num = Short.valueOf(num);
	}
	
	public CheckerNotPositiveException(int num) {
		super(num + " is not positive!");
		this.num = Integer.valueOf(num);
	}
	
	public CheckerNotPositiveException(long num) {
		super(num + " is not positive!");
		this.num = Long.valueOf(num);
	}
	
	public CheckerNotPositiveException(float num) {
		super(num + " is not positive!");
		this.num = Float.valueOf(num);
	}
	
	public CheckerNotPositiveException(double num) {
		super(num + " is not positive!");
		this.num = Double.valueOf(num);
	}
	
}

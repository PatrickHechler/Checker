package de.hechler.patrick.zeugs.check.exceptions;


public class CheckerNotNegativeException extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = -6711053097945722625L;
	
	public final Object num;
	
	
	public CheckerNotNegativeException(byte num) {
		super(num + "is not negative");
		this.num = Byte.valueOf(num);
	}
	
	public CheckerNotNegativeException(short num) {
		super(num + "is not negative");
		this.num = Short.valueOf(num);
	}
	
	public CheckerNotNegativeException(int num) {
		super(num + "is not negative");
		this.num = Integer.valueOf(num);
	}
	
	public CheckerNotNegativeException(long num) {
		super(num + "is not negative");
		this.num = Long.valueOf(num);
	}
	
	public CheckerNotNegativeException(float num) {
		super(num + "is not negative");
		this.num = Float.valueOf(num);
	}
	
	public CheckerNotNegativeException(double num) {
		super(num + "is not negative");
		this.num = Double.valueOf(num);
	}
	
}

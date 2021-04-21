package de.hechler.patrick.zeugs.check.exceptions;

public class CheckerNotNullExeption extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = 1813168640271551544L;
	
	public final Object notNull;
	public final boolean primType;
	
	
	
	public CheckerNotNullExeption(Object obj) {
		super("'" + obj + "' is not null!");
		notNull = obj;
		primType = false;
	}

	public CheckerNotNullExeption(byte a, Void v) {
		super("'" + a + "' is not 0!");
		notNull = a;
		primType = true;
	}
	
	public CheckerNotNullExeption(short a, Void v) {
		super("'" + a + "' is not 0!");
		notNull = a;
		primType = true;
	}
	
	public CheckerNotNullExeption(int a, Void v) {
		super("'" + a + "' is not 0!");
		notNull = a;
		primType = true;
	}
	
	public CheckerNotNullExeption(long a, Void v) {
		super("'" + a + "' is not 0!");
		notNull = a;
		primType = true;
	}
	
	public CheckerNotNullExeption(double a, Void v) {
		super("'" + a + "' is not 0!");
		notNull = a;
		primType = true;
	}
	
	public CheckerNotNullExeption(float a, Void v) {
		super("'" + a + "' is not 0!");
		notNull = a;
		primType = true;
	}
	
}

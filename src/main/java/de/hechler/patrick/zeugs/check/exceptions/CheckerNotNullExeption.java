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

	public CheckerNotNullExeption(byte a) {
		super("'" + a + "' is not 0!");
		notNull = a;
		primType = true;
	}
	
	public CheckerNotNullExeption(short a) {
		super("'" + a + "' is not 0!");
		notNull = a;
		primType = true;
	}
	
	public CheckerNotNullExeption(int a) {
		super("'" + a + "' is not 0!");
		notNull = a;
		primType = true;
	}
	
	public CheckerNotNullExeption(long a) {
		super("'" + a + "' is not 0!");
		notNull = a;
		primType = true;
	}
	
	public CheckerNotNullExeption(double a) {
		super("'" + a + "' is not 0!");
		notNull = a;
		primType = true;
	}
	
	public CheckerNotNullExeption(float a) {
		super("'" + a + "' is not 0!");
		notNull = a;
		primType = true;
	}
	
}

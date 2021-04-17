package de.hechler.patrick.zeugs.check.exceptions;

public class CheckerNotNullExeption extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = 1813168640271551544L;
	
	public final Object notNull;
	
	
	
	public CheckerNotNullExeption(Object obj) {
		super("'" + obj + "' is not null!");
		notNull = obj;
	}
	
}

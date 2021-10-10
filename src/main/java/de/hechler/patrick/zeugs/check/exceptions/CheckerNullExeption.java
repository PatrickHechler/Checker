package de.hechler.patrick.zeugs.check.exceptions;


public class CheckerNullExeption extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = 3920986078263969681L;
	
	public Class <?> type;
	
	public CheckerNullExeption() {
		super("it was null");
		type = null;
	}
	
	public CheckerNullExeption(Class <?> type) {
		super("it was " + (type.isPrimitive() ? (type == Float.TYPE ? "0.0f" : (type == Double.TYPE ? "0.0d" : (type == Long.TYPE ? "0l" : "0"))) : "null") + ", type=" + type);
		this.type = type;
	}
	
}

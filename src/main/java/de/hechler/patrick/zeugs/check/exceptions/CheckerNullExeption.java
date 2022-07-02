package de.hechler.patrick.zeugs.check.exceptions;

public class CheckerNullExeption extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = 3920986078263969681L;
	
	public Class <?> type;
	
	public CheckerNullExeption() {
		this("it was null", null);
	}
	
	public CheckerNullExeption(String msg) {
		this(msg, null);
	}
	
	public CheckerNullExeption(Class <?> type) {
		this("it was a non null value of type: " + type.getCanonicalName() == null ? type.getName() : type.getCanonicalName(), type);
		this.type = type;
	}
	
	public CheckerNullExeption(String msg, Class <?> type) {
		super(msg);
		this.type = type;
	}
	
}

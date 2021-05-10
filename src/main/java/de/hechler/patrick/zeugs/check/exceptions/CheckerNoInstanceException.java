package de.hechler.patrick.zeugs.check.exceptions;


public class CheckerNoInstanceException extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = -5434253253135747940L;
	
	
	
	public CheckerNoInstanceException(Class <?> cls, Object o, Void exactClass) {
		super(cls.getName() + " is not the exact class of '" + o + "' (o.class=" + o.getClass().getName() + ")");
	}
	
	public CheckerNoInstanceException(Class <?> cls, Object o) {
		super("'" + o + "' is no instance of " + cls.getName());
	}
	
	public CheckerNoInstanceException(Class <?> cls, Class <?> noSubClas) {
		super(noSubClas.getName() + " is no subclass of " + cls.getName());
	}
	
}

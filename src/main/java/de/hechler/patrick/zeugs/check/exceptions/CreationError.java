package de.hechler.patrick.zeugs.check.exceptions;

import java.lang.reflect.Method;

import de.hechler.patrick.zeugs.check.Result;

public class CreationError extends Error {

	/** UID */
	private static final long serialVersionUID = 2962699677896355090L;

	public final Method m;
	public final Result r;

	public CreationError(Method m, Result r) {
		super("creation error in method '" + m + "': '" + r + "'");
		this.m = m;
		this.r = r;
	}

}

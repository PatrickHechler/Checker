package de.hechler.patrick.zeugs.check.exceptions;

import java.util.Arrays;

public class CheckerArraysNotEqualsExeption extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = -1426130518951896569L;
	public final Object       a;
	public final Object       b;
	
	public CheckerArraysNotEqualsExeption(Object a, Object b) {
		super("arrays not equals: " + tos(a) + " not equals " + tos(b));
		this.a = a;
		this.b = b;
	}
	
	private static String tos(Object arr) {
		Class <?> c = arr.getClass();
		if (c == long[].class) return Arrays.toString((long[]) arr);
		else if (c == int[].class) return Arrays.toString((int[]) arr);
		else if (c == short[].class) return Arrays.toString((short[]) arr);
		else if (c == byte[].class) return Arrays.toString((byte[]) arr);
		else if (c == float[].class) return Arrays.toString((float[]) arr);
		else if (c == double[].class) return Arrays.toString((double[]) arr);
		else if (arr instanceof Object[]) return Arrays.deepToString((Object[]) arr);
		else return arr.toString();
	}
	
}

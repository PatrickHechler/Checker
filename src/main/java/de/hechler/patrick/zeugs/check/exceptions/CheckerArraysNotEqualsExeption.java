//This file is part of the Checker Project
//DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
//Copyright (C) 2023  Patrick Hechler
//
//This program is free software: you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with this program.  If not, see <https://www.gnu.org/licenses/>.
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

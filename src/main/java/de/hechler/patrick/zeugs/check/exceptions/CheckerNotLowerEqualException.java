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

public class CheckerNotLowerEqualException extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = 8853729517550085214L;
	
	
	
	public final Object  b;
	public final Object  a;
	public final boolean primitive;
	
	
	
	public CheckerNotLowerEqualException(Object a, Object b) {
		super("'" + a + "' is not equal to '" + b + "'");
		this.a = a;
		this.b = b;
		this.primitive = false;
	}
	
	public CheckerNotLowerEqualException(long a, long b) {
		super(a + " is not equal to " + b);
		this.a = Long.valueOf(a);
		this.b = Long.valueOf(b);
		this.primitive = true;
	}
	
	public CheckerNotLowerEqualException(int a, int b) {
		super(a + " is not equal to " + b);
		this.a = Integer.valueOf(a);
		this.b = Integer.valueOf(b);
		this.primitive = true;
	}
	
	public CheckerNotLowerEqualException(short a, short b) {
		super(a + " is not equal to " + b);
		this.a = Short.valueOf(a);
		this.b = Short.valueOf(b);
		this.primitive = true;
	}
	
	public CheckerNotLowerEqualException(byte a, byte b) {
		super(a + " is not equal to " + b);
		this.a = Byte.valueOf(a);
		this.b = Byte.valueOf(b);
		this.primitive = true;
	}
	
	public CheckerNotLowerEqualException(float a, float b) {
		super(a + " is not equal to " + b);
		this.a = Float.valueOf(a);
		this.b = Float.valueOf(b);
		this.primitive = true;
	}
	
	public CheckerNotLowerEqualException(double a, double b) {
		super(a + " is not equal to " + b);
		this.a = Double.valueOf(a);
		this.b = Double.valueOf(b);
		this.primitive = true;
	}
	
	public CheckerNotLowerEqualException(char a, char b) {
		super(a + " is not equal to " + b);
		this.a = Character.valueOf(a);
		this.b = Character.valueOf(b);
		this.primitive = true;
	}
	
	public CheckerNotLowerEqualException(boolean a, boolean b) {
		super(a + " is not equal to " + b);
		this.a = Boolean.valueOf(a);
		this.b = Boolean.valueOf(b);
		this.primitive = true;
	}
	
}

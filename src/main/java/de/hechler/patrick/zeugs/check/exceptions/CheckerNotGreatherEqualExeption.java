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


public class CheckerNotGreatherEqualExeption extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = -5697505197711483880L;
	
	
	public final Object a;
	public final Object b;
	
	public CheckerNotGreatherEqualExeption(byte a, byte b) {
		super(a + " is not greather or equal to " + b);
		this.a = Byte.valueOf(a);
		this.b = Byte.valueOf(b);
	}
	
	public CheckerNotGreatherEqualExeption(short a, short b) {
		super(a + " is not greather or equal to " + b);
		this.a = Short.valueOf(a);
		this.b = Short.valueOf(b);
	}
	
	public CheckerNotGreatherEqualExeption(int a, int b) {
		super(a + " is not greather or equal to " + b);
		this.a = Integer.valueOf(a);
		this.b = Integer.valueOf(b);
	}
	
	public CheckerNotGreatherEqualExeption(long a, long b) {
		super(a + " is not greather or equal to " + b);
		this.a = Long.valueOf(a);
		this.b = Long.valueOf(b);
	}
	
	public CheckerNotGreatherEqualExeption(float a, float b) {
		super(a + " is not greather or equal to " + b);
		this.a = Float.valueOf(a);
		this.b = Float.valueOf(b);
	}
	
	public CheckerNotGreatherEqualExeption(double a, double b) {
		super(a + " is not greather or equal to " + b);
		this.a = Double.valueOf(a);
		this.b = Double.valueOf(b);
	}
	
	public CheckerNotGreatherEqualExeption(Object a, Object b) {
		super(a + " is not greather or equal to " + b);
		this.a = a;
		this.b = b;
	}
	
}

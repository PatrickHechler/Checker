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


public class CheckerNotGreatherExeption extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = 8978475692696731457L;
	
	
	public final Object a;
	public final Object b;
	
	public CheckerNotGreatherExeption(byte a, byte b) {
		super(a + " is not greather then " + b);
		this.a = Byte.valueOf(a);
		this.b = Byte.valueOf(b);
	}
	
	public CheckerNotGreatherExeption(short a, short b) {
		super(a + " is not greather then " + b);
		this.a = Short.valueOf(a);
		this.b = Short.valueOf(b);
	}
	
	public CheckerNotGreatherExeption(int a, int b) {
		super(a + " is not greather then " + b);
		this.a = Integer.valueOf(a);
		this.b = Integer.valueOf(b);
	}
	
	public CheckerNotGreatherExeption(long a, long b) {
		super(a + " is not greather then " + b);
		this.a = Long.valueOf(a);
		this.b = Long.valueOf(b);
	}
	
	public CheckerNotGreatherExeption(float a, float b) {
		super(a + " is not greather then " + b);
		this.a = Float.valueOf(a);
		this.b = Float.valueOf(b);
	}
	
	public CheckerNotGreatherExeption(double a, double b) {
		super(a + " is not greather then " + b);
		this.a = Double.valueOf(a);
		this.b = Double.valueOf(b);
	}
	
	public CheckerNotGreatherExeption(Object a, Object b) {
		super(a + " is not greather then " + b);
		this.a = a;
		this.b = b;
	}
	
}

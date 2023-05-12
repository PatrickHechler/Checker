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


public class CheckerNotPositiveException extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = -5393001837820530120L;
	
	
	public final Object num;
	
	public CheckerNotPositiveException(byte num) {
		super(num + " is not positive!");
		this.num = Byte.valueOf(num);
	}
	
	public CheckerNotPositiveException(short num) {
		super(num + " is not positive!");
		this.num = Short.valueOf(num);
	}
	
	public CheckerNotPositiveException(int num) {
		super(num + " is not positive!");
		this.num = Integer.valueOf(num);
	}
	
	public CheckerNotPositiveException(long num) {
		super(num + " is not positive!");
		this.num = Long.valueOf(num);
	}
	
	public CheckerNotPositiveException(float num) {
		super(num + " is not positive!");
		this.num = Float.valueOf(num);
	}
	
	public CheckerNotPositiveException(double num) {
		super(num + " is not positive!");
		this.num = Double.valueOf(num);
	}
	
}

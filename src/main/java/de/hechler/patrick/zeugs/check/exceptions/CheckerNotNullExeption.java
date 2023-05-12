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

public class CheckerNotNullExeption extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = 1813168640271551544L;
	
	public final Object notNull;
	public final boolean primType;
	
	
	
	public CheckerNotNullExeption(Object obj, String msg) {
		super(msg);
		notNull = obj;
		primType = false;
	}
	
	public CheckerNotNullExeption(Object obj) {
		super("'" + obj + "' is not null!");
		notNull = obj;
		primType = false;
	}

	public CheckerNotNullExeption(byte a) {
		super(a + " is not 0!");
		notNull = a;
		primType = true;
	}
	
	public CheckerNotNullExeption(short a) {
		super(a + " is not 0!");
		notNull = a;
		primType = true;
	}
	
	public CheckerNotNullExeption(int a) {
		super(a + " is not 0!");
		notNull = a;
		primType = true;
	}
	
	public CheckerNotNullExeption(long a) {
		super(a + " is not 0!");
		notNull = a;
		primType = true;
	}
	
	public CheckerNotNullExeption(double a) {
		super(a + " is not 0!");
		notNull = a;
		primType = true;
	}
	
	public CheckerNotNullExeption(float a) {
		super(a + " is not 0!");
		notNull = a;
		primType = true;
	}
	
}

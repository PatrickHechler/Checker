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


public class CheckerNoInstanceException extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = -5434253253135747940L;
	
	public final Class <?> cls;
	public final Object noInstance;
	public final boolean neededExactClass;
	public final boolean bothClasses;
	
	
	public CheckerNoInstanceException(String msg, Object o, Class <?> notExactClass) {
		super(msg);
		this.bothClasses = false;
		this.neededExactClass = true;
		this.noInstance = o;
		this.cls = notExactClass;
	}
	
	public CheckerNoInstanceException(String msg, Class <?> notClass, Object o) {
		super(msg);
		this.bothClasses = false;
		this.neededExactClass = false;
		this.noInstance = o;
		this.cls = notClass;
	}
	
	public CheckerNoInstanceException(String msg, Class <?> cls, Class <?> noSubClas) {
		super(msg);
		this.bothClasses = true;
		this.neededExactClass = false;
		this.noInstance = noSubClas;
		this.cls = cls;
	}
	
	public CheckerNoInstanceException(Object o, Class <?> notExactClass) {
		super(notExactClass.getName() + " is not the exact class of '" + o + "' (o.class=" + o.getClass().getName() + ')');
		this.bothClasses = false;
		this.neededExactClass = true;
		this.noInstance = o;
		this.cls = notExactClass;
	}
	
	public CheckerNoInstanceException(Class <?> notClass, Object o) {
		super("'" + o + "' is no instance of " + notClass.getName() + " (o.class=" + o.getClass().getName() + ')');
		this.bothClasses = false;
		this.neededExactClass = false;
		this.noInstance = o;
		this.cls = notClass;
	}
	
	public CheckerNoInstanceException(Class <?> cls, Class <?> noSubClas) {
		super(noSubClas.getName() + " is no subclass of " + cls.getName());
		this.bothClasses = true;
		this.neededExactClass = false;
		this.noInstance = noSubClas;
		this.cls = cls;
	}
	
}

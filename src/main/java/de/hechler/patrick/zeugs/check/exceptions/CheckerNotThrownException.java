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

public class CheckerNotThrownException extends CheckerException {
	
	/** UID */
	private static final long serialVersionUID = 7980255959142064263L;
	
	
	public CheckerNotThrownException(String assertetMsg, Class <? extends Throwable> assertet, String realMsg, Class <? extends Throwable> thrown, boolean exactClass) {
		super("did not throw an " + assertet.getName() + " with msg: '" + assertetMsg + "' exception, but an " + thrown.getName() + " exception with msg: '" + realMsg + "'! exactClass=" + exactClass);
	}
	
	public CheckerNotThrownException(String assertetMsg, Class <? extends Throwable> assertet, String realMsg, Class <? extends Throwable> thrown) {
		super("did not throw an " + assertet.getName() + " with msg: '" + assertetMsg + "' exception, but an " + thrown.getName() + " exception with msg: '" + realMsg + "'!");
	}
	
	public CheckerNotThrownException(Class <? extends Throwable> assertet, Class <? extends Throwable> thrown, boolean exactClass) {
		super("did not throw an " + assertet.getName() + " exception, but an " + thrown.getName() + " exception! exactClass=" + exactClass);
	}
	
	public CheckerNotThrownException(Class <? extends Throwable> assertet, Class <? extends Throwable> thrown) {
		super("did not throw an " + assertet.getName() + " exception, but an " + thrown.getName() + " exception!");
	}
	
	public CheckerNotThrownException(Class <? extends Throwable> assertet) {
		super("did not throw an " + assertet.getName() + " exception!");
	}
	
	public CheckerNotThrownException(String msg, String realMsg, Class <? extends Throwable> thrown) {
		super("did not throw an error with " + msg + ", but a " + thrown.getName() + " with '" + realMsg + "'!");
	}
	
	public CheckerNotThrownException() {
		super("did not throw an exception!");
	}
	
}

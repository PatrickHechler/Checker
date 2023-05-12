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

import de.hechler.patrick.zeugs.check.objects.Checker;

/**
 * this class is the root exception class for all exception thrown by the
 * {@link Checker} or any other class from this project.<br>
 * the {@link CheckerException} subclasses {@link Error}, so it does not get cough when catching
 * {@link Exception}.
 * 
 * @author Patrick
 */
public class CheckerException extends Error {
	
	/** UID */
	private static final long serialVersionUID = 6260278481954374074L;
	
	/**
	 * creates a new {@link CheckerException} with the given message
	 * 
	 * @param msg the detailed message
	 */
	public CheckerException(String msg) {
		super(msg);
	}
	
	/**
	 * creates a new {@link CheckerException} with the given message and {@code cause}.
	 * 
	 * @param msg the detailed message
	 * @param cause the cause of this {@link CheckerException}
	 */
	public CheckerException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	/**
	 * creates a new {@link CheckerException} with the given {@code cause} and its message.
	 * 
	 * @param cause the cause of this {@link CheckerException}
	 */
	public CheckerException(Throwable cause) {
		super(cause);
	}
	
}

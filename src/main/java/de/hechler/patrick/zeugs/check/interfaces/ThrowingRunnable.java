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
package de.hechler.patrick.zeugs.check.interfaces;

/**
 * this functional interface represents a runnable, which is allowed to throw an {@link Exception}, {@link Error} or any other
 * {@link Throwable}.
 * 
 * @author Patrick
 * @param <T>
 *            the type of the {@link Throwable} which may be thrown by this {@link ThrowingRunnable}
 */
@FunctionalInterface
public interface ThrowingRunnable <T extends Throwable> {
	
	/**
	 * executes this throwable runnable.<br>
	 * this operation is allowed to throw any {@link Throwable} of type {@code T} (or of course some
	 * other unchecked {@link Throwable}).<br>
	 * But this operation is also allowed to end normally without throwing anything.
	 * 
	 * @throws T
	 *             the type of the {@link Throwable} which is possible to be thrown in this operation.
	 */
	void run() throws T;
	
}

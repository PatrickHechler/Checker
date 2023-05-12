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
 * a simple functional interface which defines the method
 * {@link #accept(Object, Object, Object)}.<br>
 * the method gets three arguments and has no return value.<br>
 * the defined method is not allowed to throw any checked exception.
 * 
 * @author Patrick
 * @param <A>
 *            the first parameter type of the function
 * @param <B>
 *            the second parameter type of the function
 * @param <C>
 *            the third parameter type of the function
 */
@FunctionalInterface
public interface TriConsumer <A, B, C> {
	
	/**
	 * performs the operation with the three arguments.
	 * 
	 * @param a
	 *            the first argument
	 * @param b
	 *            the second argument
	 * @param c
	 *            the third argument
	 */
	void accept(A a, B b, C c);
	
	/**
	 * Returns a composed {@link TriConsumer} that performs, in sequence, this
	 * operation followed by the {@code then} operation. If performing either
	 * operation throws an exception, it is relayed to the caller of the
	 * composed operation. If performing this operation throws an exception,
	 * the {@code then} operation will not be performed.
	 *
	 * @param then
	 *            the operation to perform after this operation
	 * @return a composed {@link TriConsumer} that performs in sequence this
	 *             operation followed by the {@code then} operation
	 * @throws NullPointerException
	 *             if {@code then} is null
	 */
	default TriConsumer <A, B, C> andThen(TriConsumer <? super A, ? super B, ? super C> then) throws NullPointerException {
		if (then == null) {
			throw new NullPointerException("then is null");
		}
		return (a, b, c) -> {
			this.accept(a, b, c);
			then.accept(a, b, c);
		};
	}
	
}

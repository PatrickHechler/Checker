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

import java.util.Arrays;

/**
 * this interface provides two methods to retrieve two values ({@link #valA()}
 * and {@link #valB()})
 * <br>
 * additionally there are two optional methods to set those values
 * ({@link #valA(Object)} and {@link #valB(Object)})
 * 
 * @author pat
 *
 * @param <A> the type of value A
 * @param <B> the type of value B
 */
public interface TwoVals<A, B> {
	
	/**
	 * returns the A value of this {@link TwoVals} instance
	 * 
	 * @return the A value of this {@link TwoVals} instance
	 */
	A valA();
	
	/**
	 * returns the B value of this {@link TwoVals} instance
	 * 
	 * @return the B value of this {@link TwoVals} instance
	 */
	B valB();
	
	/**
	 * sets the A value of this {@link TwoVals} instance to {@code newA}
	 * <p>
	 * this method is optional, the default implementation just throws an
	 * {@link UnsupportedOperationException}
	 * 
	 * @param newA the new A value
	 */
	default void valA(A newA) { throw new UnsupportedOperationException("set value A"); }
	
	/**
	 * sets the B value of this {@link TwoVals} instance to {@code newB}
	 * <p>
	 * this method is optional, the default implementation just throws an
	 * {@link UnsupportedOperationException}
	 * 
	 * @param newB the new B value
	 */
	default void valB(B newB) { throw new UnsupportedOperationException("set value B"); }
	
	/**
	 * returns
	 * <code>({@link #hashCode(Object) TwoVals.hashCode(valA())} ^ {@link #hashCode(Object) TwoVals.hashCode(valB())})</code>
	 * 
	 * @see #valA()
	 * @see #valB()
	 * @see #hashCode(Object)
	 * 
	 * @return <code>({@link #hashCode(Object) TwoVals.hashCode(val()A)} ^ {@link #hashCode(Object) TwoVals.hashCode(valB())})</code>
	 */
	@Override
	int hashCode();
	
	/**
	 * returns
	 * 
	 * <pre><code>
	 * if (obj == null) return false;
	 * if (!(obj instanceof TwoVals&lt;?, ?&gt; tv)) return false;
	 * return Objects.deepEquals(valA(), tv.valA()) {@literal &&} Objects.deepEquals(valB(), tv.valB());
	 * </code></pre>
	 * 
	 * @param obj the other object which is potentially equal to this object
	 * 
	 * @return<code>
	 * if (obj == null) { return false; }
	 * if (!(obj instanceof TwoVals&lt;?, ?&gt; tv)) { return false; }
	 * return Objects.deepEquals(valA(), tv.valA()) {@literal &&} Objects.deepEquals(valB(), tv.valB());
	 * </code>
	 */
	@Override
	boolean equals(Object obj);
	
	static int hashCode(Object obj) throws InternalError {
		if (obj == null) return 0;
		if (obj.getClass().isArray()) {
			if (obj.getClass().componentType().isPrimitive()) {
				return primArrHashCode(obj);
			} else {
				return Arrays.deepHashCode((Object[]) obj);
			}
		} else return obj.hashCode();
	}
	
	static int primArrHashCode(Object obj) throws AssertionError {
		int result = 0;
		if (obj instanceof long[] arr) {
			for (int i = arr.length - 1; i >= 0; i--) {
				result ^= Long.hashCode(arr[i]);
			}
		} else if (obj instanceof double[] arr) {
			for (int i = arr.length - 1; i >= 0; i--) {
				result ^= Double.hashCode(arr[i]);
			}
		} else if (obj instanceof int[] arr) {
			for (int i = arr.length - 1; i >= 0; i--) {
				result ^= Integer.hashCode(arr[i]);
			}
		} else if (obj instanceof byte[] arr) {
			for (int i = arr.length - 1; i >= 0; i--) {
				result ^= Byte.hashCode(arr[i]);
			}
		} else if (obj instanceof char[] arr) {
			for (int i = arr.length - 1; i >= 0; i--) {
				result ^= Character.hashCode(arr[i]);
			}
		} else if (obj instanceof float[] arr) {
			for (int i = arr.length - 1; i >= 0; i--) {
				result ^= Float.hashCode(arr[i]);
			}
		} else if (obj instanceof short[] arr) {
			for (int i = arr.length - 1; i >= 0; i--) {
				result ^= Short.hashCode(arr[i]);
			}
		} else {
			throw new AssertionError("unknown primitive array type: " + obj.getClass());
		}
		return result;
	}
	
}

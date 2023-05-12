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
package de.hechler.patrick.zeugs.check.objects;

import java.util.Objects;

import de.hechler.patrick.zeugs.check.interfaces.TwoVals;

/**
 * a simple implementation of the {@link TwoVals} interface.<br>
 * this implementation allows public access to the two values.<br>
 * modifications with the {@link #valA(Object)} and {@link #valB(Object)}
 * methods are allowed.
 * 
 * @author Patrick
 * 
 * @param <A>
 *            the type of the first value
 * @param <B>
 *            the type of the second value
 */
public class TwoValuesImpl<A, B> implements TwoVals<A, B>, Cloneable {
	
	/**
	 * the A value
	 */
	public A valA;
	/**
	 * the B value
	 */
	public B valB;
	
	/**
	 * creates a new {@link TwoValuesImpl} with the given two initial values
	 * 
	 * @param valueA the initial A value
	 * @param valueB the initial B value
	 */
	public TwoValuesImpl(A valueA, B valueB) {
		this.valA = valueA;
		this.valB = valueB;
	}
	
	@Override
	public A valA() { return valA; }
	
	@Override
	public B valB() { return valB; }
	
	@Override
	public void valA(A valueA) { this.valA = valueA; }
	
	@Override
	public void valB(B valueB) { this.valB = valueB; }
	
	@Override
	public int hashCode() { return TwoVals.hashCode(valA) ^ TwoVals.hashCode(valB); }
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null) return false;
		if (!(obj instanceof TwoVals<?, ?> ce)) return false;
		return Objects.deepEquals(valA, ce.valA()) && Objects.deepEquals(valB, ce.valB());
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TwoValues [valueA=");
		builder.append(valA);
		builder.append(", valueB=");
		builder.append(valB);
		builder.append("]");
		return builder.toString();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected TwoValuesImpl<A, B> clone() {
		try {
			return (TwoValuesImpl<A, B>) super.clone();
		} catch (CloneNotSupportedException e) {
			return new TwoValuesImpl<>(valA, valB);
		}
	}
	
}

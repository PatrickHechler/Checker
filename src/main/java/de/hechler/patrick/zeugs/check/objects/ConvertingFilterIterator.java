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

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;


public abstract class ConvertingFilterIterator<T, O> implements Iterator<T>, Predicate<O>, Function<O, T> {
	
	private final Iterator<O> iter;
	private T                 next;
	
	protected ConvertingFilterIterator(Iterator<O> iter) { this.iter = iter; }
	
	@Override
	public boolean hasNext() {
		try {
			next = next();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	@Override
	public T next() {
		T n = next;
		if (n != null) {
			next = null;
			return n;
		}
		while (true) {
			O val = iter.next();
			if (test(val)) { return apply(val); }
		}
	}
	
}

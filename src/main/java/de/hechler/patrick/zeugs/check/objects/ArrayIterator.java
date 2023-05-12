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

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * a simple {@link Iterator} which iterates over
 * 
 * @author Patrick
 * 
 * @param <T> the type of the array
 */
public class ArrayIterator<T> implements Iterator<T> {
	
	/**
	 * the array over wich this iterator should iterate.
	 */
	private final Object arr;
	/**
	 * the current index
	 */
	private int          i;
	/**
	 * the end (by default the array length)
	 */
	private final int    end;
	
	/**
	 * creates a new array iterator which starts at the begin of the array
	 * 
	 * @param arr the array over which should be iterated
	 */
	public ArrayIterator(Object arr) {
		this.arr = arr;
		this.i   = 0;
		this.end = Array.getLength(arr);
	}
	
	/**
	 * creates a new array iterator wich should iterate from start to the end of the
	 * array
	 * 
	 * @param arr   the array over wich should be iterated
	 * @param start the start index
	 * @param end   the end index
	 */
	public ArrayIterator(T[] arr, int start, int end) {
		this.arr = arr;
		this.i   = start;
		this.end = end;
	}
	
	@Override
	public boolean hasNext() { return this.i < this.end; }
	
	@Override
	@SuppressWarnings("unchecked")
	public T next() {
		if (this.i < this.end) {
			return (T) Array.get(this.arr, i++);
		} else {
			throw new NoSuchElementException("I only have " + this.end + " elements!");
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void forEachRemaining(Consumer<? super T> action) {
		for (; this.i < this.end; this.i++) {
			action.accept((T) Array.get(this.arr, this.i));
		}
	}
	
}

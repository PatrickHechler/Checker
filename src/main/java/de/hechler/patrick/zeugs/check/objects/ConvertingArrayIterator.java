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
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * this iterator uses an array from the type S and an convert {@link Function} to iterate over the
 * converted array.<br>
 * this iterator iterates over the array {@link #arr} and returns the return value of the
 * {@link Function} {@link #func}.
 * 
 * @author Patrick
 * @param <S>
 *            the element type of the source array
 * @param <T>
 *            the converted target type of the {@link Function}
 */
public class ConvertingArrayIterator <S, T> implements Iterator <T> {
	
	/**
	 * the array over wich should be iterated
	 */
	private final S[] arr;
	/**
	 * the converting function to get from each array element of type {@code S} an target object of type
	 * {@code T}
	 */
	private final Function <S, T> func;
	/**
	 * the current index
	 */
	private int i;
	
	/**
	 * creates a new {@link ConvertingArrayIterator} which starts at the begin of the given array
	 * {@code arr}.<br>
	 * 
	 * @param arr
	 *            the array over wich should be iterated with its converted values
	 * @param func
	 *            the {@link Function} used to convert the array element values
	 */
	public ConvertingArrayIterator(S[] arr, Function <S, T> func) {
		this.arr = arr;
		this.func = func;
		this.i = 0;
	}
	
	/**
	 * creates a new {@link ConvertingArrayIterator} which starts at the given index
	 * 
	 * @param arr
	 *            the array over wich should be iterated with its converted values
	 * @param func
	 *            the {@link Function} used to convert the array element values
	 * @param i
	 *            the start index
	 */
	public ConvertingArrayIterator(S[] arr, Function <S, T> func, int i) {
		this.arr = arr;
		this.func = func;
		this.i = i;
	}
	
	@Override
	public boolean hasNext() {
		return this.i < this.arr.length;
	}
	
	@Override
	public T next() {
		if (this.i < this.arr.length) {
			return this.func.apply(this.arr[i ++ ]);
		} else {
			throw new NoSuchElementException("I only have " + this.arr.length + " elements!");
		}
	}
	
	@Override
	public void forEachRemaining(Consumer <? super T> action) {
		for (; this.i < this.arr.length; this.i ++ ) {
			T converted = this.func.apply(this.arr[i ++ ]);
			action.accept(converted);
		}
	}
	
}

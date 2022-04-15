package de.hechler.patrick.zeugs.check.objects;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayIterator <T> implements Iterator <T> {
	
	private final T[] arr;
	private int i;
	
	public ArrayIterator(T[] arr) {
		this.arr = arr;
		this.i = 0;
	}
	
	@Override
	public boolean hasNext() {
		return this.i < this.arr.length;
	}
	
	@Override
	public T next() {
		if (this.i < this.arr.length) {
			return this.arr[i ++ ];
		} else {
			throw new NoSuchElementException("I only have " + this.arr.length + " elements!");
		}
	}
	
}

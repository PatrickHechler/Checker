package de.hechler.patrick.zeugs.check.objects;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class ArrayFunctionIterator <S, T> implements Iterator <T> {
	
	private final S[] arr;
	private final Function <S, T> func;
	private int i;
	
	public ArrayFunctionIterator(S[] arr, Function <S, T> func) {
		this.arr = arr;
		this.func = func;
		this.i = 0;
	}
	
	@Override
	public boolean hasNext() {
		return this.i < this.arr.length;
	}
	
	@Override
	public T next() {
		if (this.i < this.arr.length) {
			return this.func.apply(this.arr[i++]);
		} else {
			throw new NoSuchElementException("I only have " + this.arr.length + " elements!");
		}
	}
	
}

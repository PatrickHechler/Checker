package de.hechler.patrick.zeugs.check.objects;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * a simple {@link Iterator} which iterates over
 * 
 * @author Patrick
 * @param <T>
 */
public class ArrayIterator <T> implements Iterator <T> {
	
	/**
	 * the array over wich this iterator should iterate.
	 */
	private final T[] arr;
	/**
	 * the current index
	 */
	private int i;
	
	/**
	 * creates a new array iterator which starts at the begin of the array
	 * 
	 * @param arr
	 *            the array over wich should be iterated
	 */
	public ArrayIterator(T[] arr) {
		this.arr = arr;
		this.i = 0;
	}
	
	/**
	 * creates a new array iterator wich should iterate from start to the end of the array
	 * 
	 * @param arr
	 *            the array over wich should be iterated
	 * @param start
	 *            the start index
	 */
	public ArrayIterator(T[] arr, int start) {
		this.arr = arr;
		this.i = start;
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
	
	@Override
	public void forEachRemaining(Consumer <? super T> action) {
		for (; this.i < this.arr.length; this.i ++ ) {
			action.accept(this.arr[this.i]);
		}
	}
	
}

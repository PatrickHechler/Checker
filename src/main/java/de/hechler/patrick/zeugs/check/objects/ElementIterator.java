package de.hechler.patrick.zeugs.check.objects;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ElementIterator<T> implements Iterator<T> {
	
	private final T element;
	private boolean iterated = false;
	
	public ElementIterator(T element) { this.element = element; }
	
	@Override
	public boolean hasNext() { return !iterated; }
	
	@Override
	public T next() {
		if (iterated) { throw new NoSuchElementException(); }
		iterated = true;
		return element;
	}
	
}

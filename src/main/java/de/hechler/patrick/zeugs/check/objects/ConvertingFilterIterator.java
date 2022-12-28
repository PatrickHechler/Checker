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

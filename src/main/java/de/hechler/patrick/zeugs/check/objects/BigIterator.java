package de.hechler.patrick.zeugs.check.objects;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * this class can be used to iterate over all possibilities of a matrix/cluster
 * <p>
 * it uses an array of {@link Iterable} objects and iterates over all possible
 * combinations
 * 
 * @author pat
 *
 * @param <T> the type of the {@link Iterable} objects
 */
public class BigIterator<T> implements Iterator<T[]> {
	
	private final Iterator<? extends T>[] iters;
	private final Iterable<? extends T>[] iterabls;
	private final T[]                     result;
	
	private T[] next;
	
	/**
	 * creates a new {@link BigIterator}, which can be used to iterate over the
	 * given {@link Iterable} objects which are of the given {@link Class}
	 * 
	 * @param cls the class of the {@link Iterable} objects (and this {@link BigIterator})
	 * @param iterabls the {@link Iterable} objects, used by this {@link BigIterator}
	 */
	@SuppressWarnings("unchecked")
	public BigIterator(Class<T> cls, Iterable<? extends T>[] iterabls) {
		this.iterabls = iterabls;
		this.iters    = new Iterator[iterabls.length];
		this.result   = (T[]) Array.newInstance(cls, iterabls.length);
		for (int i = 0; i < iterabls.length; i++) {
			this.iters[i] = iterabls[i].iterator();
		}
	}
	
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
	public T[] next() throws NoSuchElementException {
		T[] n = next;
		if (n != null) {
			next = null;
			return n;
		}
		for (int i = 0; i < result.length; i++) {
			if (iters[i].hasNext()) {
				result[i] = iters[i].next();
			} else if (i + 1 == result.length) {
				throw new NoSuchElementException();
			} else {
				iters[i]  = iterabls[i].iterator();
				result[i] = iters[i].next();
			}
		}
		return result;
	}
	
}

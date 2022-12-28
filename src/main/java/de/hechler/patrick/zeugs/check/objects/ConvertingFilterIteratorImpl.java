package de.hechler.patrick.zeugs.check.objects;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConvertingFilterIteratorImpl<T, O> extends ConvertingFilterIterator<T, O> {
	
	private final Predicate<O>   filter;
	private final Function<O, T> convert;
	
	public ConvertingFilterIteratorImpl(Iterator<O> iter, Predicate<O> filter, Function<O, T> convert) {
		super(iter);
		this.filter  = filter;
		this.convert = convert;
	}
	
	@Override
	public boolean test(O t) { return filter.test(t); }
	
	@Override
	public T apply(O t) { return convert.apply(t); }
	
}

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

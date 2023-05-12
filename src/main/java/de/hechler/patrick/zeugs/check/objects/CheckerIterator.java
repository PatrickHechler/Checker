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

import de.hechler.patrick.zeugs.check.anotations.CheckClass;
import de.hechler.patrick.zeugs.check.interfaces.TwoVals;

/**
 * the {@link CheckerIterator} uses an {@link Iterator} which iterates over
 * {@link Class} objects,
 * and generates Checkers from the classes.<br>
 * if a {@link Class} has no {@link CheckClass} annotation or the annotation is
 * {@link CheckClass#disabled()} the class will skipped.<br>
 * 
 * @author Patrick
 */
public class CheckerIterator extends ConvertingFilterIterator<TwoVals<Class<?>, Checker>, Class<?>> {
	
	/**
	 * the filter {@link Predicate}, which checks if the given {@link Class} is
	 * annotated with {@link CheckClass} and not {@link CheckClass#disabled()}
	 */
	public static final Predicate<? super Class<?>> FILTER = (Class<?> n) -> {
		CheckClass checkClass = n.getAnnotation(CheckClass.class);
		if (checkClass == null) return false;
		if (checkClass.disabled()) return false;
		return true;
	};
	
	/**
	 * the convert {@link Function}, which generates of the given class a
	 * {@link TwoVals} object, which holds the class and a newly generated checker
	 * for the given class
	 */
	public static final Function<Class<?>, TwoVals<Class<?>, Checker>> CONVERT = (Class<?> t) -> {
		Checker checker = Checker.generateChecker(t);
		return new TwoValues<>(t, checker);
	};
	
	/**
	 * creates a new {@link CheckerIterator}, which will iterate over all classes
	 * which are annotated with {@link CheckClass}
	 * and convert them
	 * 
	 * @param classes the raw classes iterator
	 * 
	 * @see #FILTER
	 * @see #CONVERT
	 */
	public CheckerIterator(Iterator<Class<?>> classes) { super(classes); }
	
	@Override
	public boolean test(Class<?> n) {
		CheckClass checkClass = n.getAnnotation(CheckClass.class);
		LogHandler.LOG.fine(() -> "I found " + n + "\nCheckClass: " + checkClass);
		if (checkClass == null) return false;
		if (checkClass.disabled()) return false;
		return true;
	}
	
	@Override
	public TwoVals<Class<?>, Checker> apply(Class<?> t) {
		LogHandler.LOG.fine(() -> "I will check " + t);
		Checker checker = Checker.generateChecker(t);
		return new TwoValues<>(t, checker);
	}
	
}

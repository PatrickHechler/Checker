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
package de.hechler.patrick.zeugs.check.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.Parameter;
import java.util.Iterator;

/**
 * used to create the value of a parameter.<br>
 * the primitive values, and their wrapping values are treated equally here<br>
 * the return value can have different values:
 * <ul>
 * <li>an instance of {@link Iterable} will be interpreted as an
 * {@link Iterable} over the possible parameter values (the returned Iterable
 * should not be modified during the checks!).</li>
 * <li>an array will be interpreted as an array which contains all possible
 * parameter values.</li>
 * <li>all other values are interpreted as the only possible value the parameter
 * should have</li>
 * <li><code>null</code> and <code>void</code> will be interpreted like the
 * other values and lead to a single <code>null</code> value</li>
 * </ul>
 * <p>
 * note that if the returned value is an array or {@link Iterable} and the value
 * is
 * used to generate the values for an {@link Parameter} which belongs to a
 * method which is called to generate a {@link Parameter} (because it is
 * annotated with {@link ParamCreater}), the array must have the length
 * <code>1</code> and the {@link Iterable} must return an {@link Iterator},
 * which iterates over exactly one element.
 * <p>
 * {@link #method()} defines the name of the method which creates the
 * parameter.<br>
 * {@link #params()} is an array of the methods parameters types<br>
 * {@link #clas()} is the type of the class which declares the method or the
 * {@link ParamCreater} class
 * <p>
 * the described method must be declared in the same class.
 * <p>
 * {@link #method()} has no default value.<br>
 * {@link #params()} is by default an empty array.<br>
 * {@link #clas()} is by default set to the class {@link ParamCreater}.
 * <p>
 * if this annotation is mixed with {@link ResultParam}, {@link ParamParam},
 * {@link MethodParam} on one {@link Parameter} the one of them is chosen to be
 * used.
 * 
 * @author Patrick
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ParamCreater {
	
	/**
	 * this element defines the name of the method,
	 * which should be used to instantiate the parameter
	 * <ul>
	 * <li>
	 * if the method returns an {@link Iterable}, it is assumed to be am
	 * {@link Iterable} over the* parameters which should be used
	 * </li>
	 * <li>
	 * if the method returns an {@link Array}, it is assumed to be an array which
	 * contains the parameters which should be used
	 * </li>
	 * <li>
	 * in any other case the returned object is assumed to be a valid parameter and
	 * will be used (like an array which contains a single object (the return
	 * value))
	 * </li>
	 * </ul>
	 * 
	 * @return
	 *         the name of the method
	 */
	String method();
	
	/**
	 * returns the classes of the parameters of the method.
	 * <p>
	 * the default value is an empty array which means, that the method has no
	 * parameters
	 * 
	 * @return the classes of the parameters of the method.
	 */
	Class<?>[] params() default {};
	
	/**
	 * returns the class in which the method is declared in.
	 * <p>
	 * the default value is the {@link ParamCreater} class which is the special case
	 * for the class of the checker
	 * <br>
	 * when the {@link ParamCreater} class is returned, it will instead use the
	 * class of the checker
	 * 
	 * @return the class in which the method is declared in.
	 */
	Class<?> clas() default ParamCreater.class;
	
}

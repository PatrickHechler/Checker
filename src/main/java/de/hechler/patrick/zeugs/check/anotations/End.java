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

/**
 * this annotation is used to mark methods which should be executed after a check or all checks of a
 * checker has been executed.
 * <p>
 * if {@link #onlyOnce()} is <code>true</code> the annotated method will be executed after all
 * checks of the checker has been executed.<br>
 * if {@link #onlyOnce()} is <code>false</code> the annotated method will every be executed after a
 * check has been executed.
 * <p>
 * if {@link #disabled()} is <code>true</code> this annotation will be ignored.
 * <p>
 * {@link #onlyOnce()} and {@link #disabled()} are by default <code>false</code>.
 * 
 * @author Patrick
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface End {
	
	/**
	 * <code>true</code> if the annotated method should be called after all checks has been executed and
	 * not after every check.
	 * 
	 * @return
	 *             <code>true</code> if the annotated method should be called after all checks has been
	 *             executed and not after every check.
	 */
	boolean onlyOnce() default false;
	
	/**
	 * <code>true</code> if this annotation should be ignored
	 * 
	 * @return
	 *             <code>true</code> if this annotation should be ignored
	 */
	boolean disabled() default false;
	
}

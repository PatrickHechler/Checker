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
import java.lang.reflect.Method;

/**
 * the {@link Start} annotation is used when a {@link Method} should be called
 * before the check is called.
 * <p>
 * if {@link #onlyOnce()} returns <code>true</code> the {@link Start}
 * {@link Method} will be called before all checks.<br>
 * if annotated on a constructor {@link #onlyOnce()} will be ignored.
 * <p>
 * if {@link #disabled()} returns <code>true</code> the {@link Start}
 * {@link Method} will not be called.
 * <p>
 * if a constructor is annotated with this annotation<br>
 * the annotated constructor will be used to instantiate objects of the given class.<br>
 * if {@link #disabled()} is <code>true</code> the annotation will be ignored.<br>
 * a disabled constructor may still be used to instantiate objects.
 * <p>
 * {@link #onlyOnce()} and {@link #disabled()} are by default <code>false</code>
 * 
 * @author Patrick
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD })
public @interface Start {
	
	/**
	 * if {@link #onlyOnce()} returns <code>true</code> the {@link Start}
	 * {@link Method} will be called before all checks. <br>
	 * 
	 * @return <code>true</code> when it should be called only once before all
	 *             checks and <code>false</code> if it should be called before every
	 *             check.
	 */
	boolean onlyOnce() default false;
	
	/**
	 * if {@link #disabled()} returns <code>true</code> the {@link Start}
	 * {@link Method} will not be called.
	 * 
	 * @return <code>true</code> if this annotation should be ignored (like there is
	 *             no annotation) and <code>false</code> if not
	 */
	boolean disabled() default false;
	
}

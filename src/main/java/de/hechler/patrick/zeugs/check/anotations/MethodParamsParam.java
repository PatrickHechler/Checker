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

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

import de.hechler.patrick.zeugs.check.objects.LogHandler;

/**
 * used when a {@link Start} or {@link End} method needs to know the
 * arguments/parameters of the check
 * {@link Start#onlyOnce()}/{@link End#onlyOnce()} is <code>true</code> the
 * {@link Parameter} will be set to <code>null</code>.
 * if the annotated {@link Parameter} does not accept
 * <code>{@link Method}</code> a {@link ClassCastException}
 * will be thrown (it will not be thrown inside of the check-method, so it will
 * go the stack up).<br>
 * example:
 * 
 * <pre><code>
 * {@literal @}{@link de.hechler.patrick.zeugs.check.anotations.Start}
 * void logChecksStart({@literal @}{@link MethodParam} {@link Method} check, {@literal @}{@link MethodParamsParam} Object[] args) {
 *     {@link LogHandler#LOG}.info(() -> "check now: " + {@link Method#getName() check.getName()} + " with args: "+ {@link Arrays#toString(Object[]) Arrays.toString(args)});
 * }
 * </code></pre>
 * <p>
 * if this annotation is mixed with {@link ResultParam}, {@link ParamCreater},
 * {@link ParamParam} on one {@link Parameter} the one of them is chosen to be
 * used.
 * 
 * @author Patrick
 */
@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface MethodParamsParam {
	
}

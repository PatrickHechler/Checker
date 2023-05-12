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
import java.lang.reflect.Parameter;

import de.hechler.patrick.zeugs.check.objects.CheckResult;
import de.hechler.patrick.zeugs.check.objects.Result;

/**
 * used when a {@link End} method needs to know the result of the previously ran
 * check.<br>
 * if {@link End#onlyOnce()} returns <code>false</code> the param will be the {@link Result} from
 * the executed check.<br>
 * if {@link End#onlyOnce()} returns <code>true</code> the param will be the {@link CheckResult}
 * containing all executed checks of this checker.
 * <p>
 * if this method is not invoked as an {@link End} method the {@link Parameter}
 * will be
 * <code>null</code>
 * <p>
 * if the method is executed as a {@link End} with {@link End#onlyOnce()} set to
 * <code>false</code>,
 * the {@link Parameter} must be of type {@link Result} or a super class of
 * {@link Result}.<br>
 * if the method is executed as a {@link End} with {@link End#onlyOnce()} set to
 * <code>true</code>,
 * the {@link Parameter} must be of type {@link CheckResult} or a super class of
 * {@link CheckResult}.<br>
 * the {@link CheckResult} given to the method will have a different end time
 * than the final
 * {@link CheckResult} saved by the checker.
 * The final end time of the {@link CheckResult} will be the time after all
 * {@link End} methods with
 * {@link End#onlyOnce()} set to <code>false</code> has been executed.
 * <p>
 * the method is interpreted as a not {@link End} method, when it is not run
 * because of the
 * {@link End} annotation (regardless if it is annotated with {@link End})<br>
 * <p>
 * if this annotation is mixed with {@link ParamParam}, {@link ParamCreater},
 * {@link MethodParam} on one {@link Parameter} the one of them is chosen to be
 * used.
 * 
 * @author Patrick
 */
@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface ResultParam {
	
}

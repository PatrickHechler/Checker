package de.hechler.patrick.zeugs.check.anotations;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Parameter;

/**
 * used when a {@link End} method needs to know the result of the previously ran
 * check. if {@link End#onlyOnce()} returns <code>true</code> or it is used on a not
 * {@link End} method the {@link Parameter} will be <code>null</code><br>
 * 
 * TODO
 * 
 * @author Patrick
 *
 */
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface ResultParam {

}

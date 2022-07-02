package de.hechler.patrick.zeugs.check.anotations;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * used when a {@link Start} method needs to know which check will be called or
 * when a {@link End} method needs to know which method has been called.<br>
 * when used in a {@link Check} method or
 * {@link Start#onlyOnce()}/{@link End#onlyOnce()} is <code>true</code> the
 * {@link Parameter} will be set to <code>null</code>.
 * if the annotated {@link Parameter} does not accept
 * <code>{@link java.lang.reflect.Method}</code> a {@link ClassCastException}
 * will be thrown (it will not be thrown inside of the check-method, so it will
 * go the stack up).<br>
 * example: <code><pre>
 * {@literal @}{@link de.hechler.patrick.zeugs.check.anotations.Start}
 * void logChecksStart({@literal @}{@link de.de.hechler.patrick.zeugs.check.anotations.MethodParam} {@link java.lang.reflect.Method} check) {
 *     System.err.println("[LOG]: check now: " + check.{@link Method#getName() getName()});
 * }
 * 
 * {@literal @}{@link de.hechler.patrick.zeugs.check.anotations.End}
 * void logChecksEnd({@literal @}{@link de.de.hechler.patrick.zeugs.check.anotations.MethodParam} {@link java.lang.reflect.Method} check) {
 *     System.err.println("[LOG]: finished check: " + check.{@link Method#getName() getName()});
 * }
 * </pre></code>
 * if this annotation is mixed with {@link ResultParam} and/or
 * {@link ParamCreater} on one {@link Parameter} the one of them is chosen to be used.
 * <p>
 * if {@link #disabled()} is <code>true</code> this annotation will be ignored <br>
 * {@link #disabled()} is by default <code>false</code>
 * 
 * @author Patrick
 */
@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface MethodParam {
	
	/**
	 * <code>true</code> if this annotation should be ignored
	 * 
	 * @return
	 *             <code>true</code> if this annotation should be ignored
	 */
	boolean disabled() default false;
	
}

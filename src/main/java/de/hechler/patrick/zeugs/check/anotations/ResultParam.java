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
 * if this method is not invoked as an {@link End} method the {@link Parameter} will be
 * <code>null</code>
 * <p>
 * if the method is executed as a {@link End} with {@link End#onlyOnce()} set to <code>false</code>,
 * the {@link Parameter} must be of type {@link Result} or a super class of
 * {@link Result}.<br>
 * if the method is executed as a {@link End} with {@link End#onlyOnce()} set to <code>true</code>,
 * the {@link Parameter} must be of type {@link CheckResult} or a super class of
 * {@link CheckResult}.<br>
 * the {@link CheckResult} given to the method will have a start time set, but no end time.
 * The end time of the check will be set after all {@link End} methods with {@link End#onlyOnce()}
 * set to <code>false</code> has been executed.
 * <p>
 * if {@link #disabled()} is <code>true</code> the annotation will be ignored
 * <p>
 * the method is interpreted as a not {@link End} method, when it is not run because of the
 * {@link End} annotation (regardless if it is annotated with {@link End})<br>
 * <p>
 * if this annotation is mixed with {@link ResultParam} and/or
 * {@link ParamCreater} on one {@link Parameter} the one of them is chosen to be used.
 * <p>
 * {@link #disabled()} is by default <code>false</code>
 * 
 * @author Patrick
 */
@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface ResultParam {
	
	/**
	 * <code>true</code> when the annotation should be ignored
	 * 
	 * @return
	 *             <code>true</code> when the annotation should be ignored
	 */
	boolean disabled() default false;
	
}

package de.hechler.patrick.zeugs.check.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import de.hechler.patrick.zeugs.check.objects.BigCheckResult;
import de.hechler.patrick.zeugs.check.objects.CheckResult;
import de.hechler.patrick.zeugs.check.objects.Result;

/**
 * this annotation is used to tell the checker which methods should be executed as checks.<br>
 * a method marked with {@link Check} will be executed as a check, when the class of the method is
 * checked.<br>
 * the executed check will be saved in a {@link Result}.<br>
 * the time needed for the check can be accessed via {@link Result#getTime()}, {@link Result#start}
 * and {@link Result#end}.<br>
 * if the check normally the return value can be accessed via {@link Result#getResult()}.<br>
 * if the check ended by throwing a {@link Throwable} the given {@link Throwable} can be accessed
 * via {@link Result#getErr()}.<br>
 * if the check ended by throwing a {@link ThreadDeath} the thread-death will be re-thrown.
 * <p>
 * normally the {@link Result} can be accessed via the {@link CheckResult}, which contains the
 * {@link Result} of each {@link Method} checked in a class.<br>
 * When more classes are checked, the {@link CheckResult} can normally be accessed via the
 * {@link BigCheckResult}, which contains the {@link CheckResult} of each checked class.
 * <p>
 * if {@link #disabled()} is <code>true</code> this annotation will be ignored.
 * <p>
 * {@link #disabled()} is be default <code>false</code>
 * 
 * @author Patrick
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Check {
	
	/**
	 * <code>true</code> if this annotation should be ignored
	 * 
	 * @return
	 *             <code>true</code> if this annotation should be ignored
	 */
	boolean disabled() default false;
	
}

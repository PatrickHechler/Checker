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

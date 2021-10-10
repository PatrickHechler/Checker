package de.hechler.patrick.zeugs.check.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * 
 * the {@link Start} annotation is used when a {@link Method} should be called
 * before the check is called. <br>
 * 
 * if {@link #onlyOnce()} returns <code>true</code> the {@link Start}
 * {@link Method} will be called before all checks. <br>
 * 
 * if {@link #disabled()} returns <code>true</code> the {@link Start}
 * {@link Method} will not be called.
 * 
 * @author Patrick
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.CONSTRUCTOR, ElementType.METHOD })
public @interface Start {

	/**
	 * if {@link #onlyOnce()} returns <code>true</code> the {@link Start}
	 * {@link Method} will be called before all checks. <br>
	 * 
	 * @return <code>true</code> when it should be called only once before all
	 *         checks and <code>false</code> if it should be called before every
	 *         check.
	 */
	boolean onlyOnce() default false;

	/**
	 * if {@link #disabled()} returns <code>true</code> the {@link Start}
	 * {@link Method} will not be called.
	 * 
	 * @return <code>true</code> if this annotation should be ignored (like there is
	 *         no annotation) and <code>false</code> if not
	 */
	boolean disabled() default false;

}

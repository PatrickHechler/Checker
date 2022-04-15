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

package de.hechler.patrick.zeugs.check.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE })
public @interface CheckClass {
	
	boolean disabled() default false;
	
	/**
	 * <code>true</code> if the checks from the super class should be suppressed
	 * 
	 * @return <code>true</code> if the checks from the super class should be suppressed
	 */
	boolean disableSuper() default false;
	
}

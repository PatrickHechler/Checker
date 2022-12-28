package de.hechler.patrick.zeugs.check.anotations;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * this annotation can be used by a method designed for the {@link ParamCreater}
 * to retrieve extra data
 * 
 * @author Patrick
 */
@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface ParamInfo {
	
	String value();
	
}

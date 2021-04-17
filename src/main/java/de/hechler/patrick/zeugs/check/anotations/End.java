package de.hechler.patrick.zeugs.check.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD })
public @interface End {
	
	boolean onlyOnce() default false;
	
	boolean disabled() default false;
	
}

package de.hechler.patrick.zeugs.check.anotations;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Parameter;

/**
 * used when a {@link ParamCreater} method needs to know the {@link Parameter}
 * for which it should create the parameter.
 * <p>
 * if this method is not invoked as an {@link ParamCreater} method the {@link Parameter} will be
 * <code>null</code>, otherwise it will be set to the {@link Parameter}, for which the value should be generated
 * <p>
 * if this annotation is mixed with {@link ResultParam}, {@link ParamCreater},
 * {@link MethodParam} on one {@link Parameter} the one of them is chosen to be
 * used.
 * 
 * @author Patrick
 */
@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface ParamParam {
	
}

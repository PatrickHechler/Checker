package de.hechler.patrick.zeugs.check.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Parameter;

/**
 * used to create the value of a parameter.
 * <p>
 * {@link #method()} defines the name of the method which creates the parameter.<br>
 * {@link #methodParams()} is a list of the methods parameters in form of full qualified class names
 * <p>
 * the described method must be declared in the same class.
 * <p>
 * if the given method returns {@code void} the parameter will be set to <code>null</code>
 * <p>
 * if a parameter is not annotated with this annotation it will be set to <code>null</code>.<br>
 * primitive values will be set to zero or <code>false</code>
 * <p>
 * if this annotation is mixed with {@link ResultParam} and/or
 * {@link ParamCreater} on one {@link Parameter} the one of them is chosen to be used.
 * <p>
 * {@link #disabled()} is by default <code>false</code>.<br>
 * {@link #method()} has no default value.<br>
 * {@link #methodParams()} is by default an empty array.
 * 
 * @author Patrick
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ParamCreater {
	
	/**
	 * <code>true</code> if this annotation should be ignored
	 * 
	 * @return
	 *             <code>true</code> if this annotation should be ignored
	 */
	boolean disabled() default false;
	
	/**
	 * this element defines the name of the method,<br>
	 * which should be used to instantiate the parameter
	 * 
	 * @return
	 *             the name of the method
	 */
	String method();
	
	/**
	 * methodParams defines the parameters of the method.<br>
	 * each element in the array defines one parameter by it's full qualified class name.
	 * 
	 * @return the parameters of the method
	 */
	String[] methodParams() default {};
	
}

package de.hechler.patrick.zeugs.check.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * this annotation is used to mark a class to be checked.
 * <p>
 * normally classes without this annotation will be ignored when searching for check classes.
 * <p>
 * if {@link #disabled()} is <code>true</code> this annotation will be ignored
 * <p>
 * if {@link #singleThread()} is <code>true</code> this class will be executed while no other
 * classes are checked.<br>
 * if {@link #singleThread()} is <code>false</code> this class may be executed while other classes
 * are checked.<br>
 * even if {@link #singleThread()} is <code>false</code> the annotated class itself will always be checked in
 * a single {@link Thread}.
 * <p>
 * if {@link #disableSuper()} is <code>true</code> the checker will only executed the checks
 * declared in this class.<br>
 * if {@link #disableSuper()} is <code>false</code> the checker will also lock in super classes for
 * checks.<br>
 * example:<code><pre>
 * class MyInterfaceChecker {
 *     
 *     public MyInterface check;
 *     
 *     {@literal @}{@link End}
 *     void end() {
 *         if (check != null) {
 *             // cleanup
 *         }
 *         check = null;
 *     }
 *     
 *     {@literal @}{@link Check}
 *     void myInterfaceBehaviorCheck() {
 *         // check something
 *     }
 *     
 * }
 * 
 * {@literal @}{@link CheckClass}
 * class MyInterfaceImplChecker extends MyInterfaceChecker {
 *     
 *     {@literal @}{@link Start}
 *     void start() {
 *         check = new MyInterfaceImpl();
 *     }
 *     
 *     {@literal @}{@link Check}
 *     void implSpecialCheck() {
 *         // check something special
 *     }
 *     
 * }
 * </pre></code>
 * <p>
 * {@link #disabled()}, {@link #singleThread()} and {@link #disableSuper()} are by default
 * <code>false</code>
 * 
 * @author Patrick
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CheckClass {
	
	/**
	 * <code>true</code> when this annotation should be ignored
	 * 
	 * @return
	 *             <code>true</code> when this annotation should be ignored
	 */
	boolean disabled() default false;
	
	/**
	 * <code>true</code> if this class should not be checked, while other classes are checked.<br>
	 * <code>false</code> if this class is allowed to be checked, while other classes are checked.<br>
	 * even if the value is <code>false</code> only one {@link Thread} will be used to execute the
	 * checks from this class.
	 * 
	 * @return <code>true</code> if this class should be checked, while no other class is checked.
	 */
	boolean singleThread() default false;
	
	/**
	 * <code>true</code> if the checks from the super class should be suppressed
	 * <p>
	 * if the super class is annotated with {@link CheckClass} the annotation will always be ignored
	 * <p>
	 * if a checker overwrites a method, the method is doubled checked, when
	 * {@link #disableSuper()} is <code>false</code>.
	 * But the overwriting method will be called both times.
	 * 
	 * @return <code>true</code> if the checks from the super class should be suppressed
	 */
	boolean disableSuper() default false;
	
}

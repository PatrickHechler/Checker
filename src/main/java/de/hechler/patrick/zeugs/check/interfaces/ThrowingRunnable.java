package de.hechler.patrick.zeugs.check.interfaces;

/**
 * this functional interface represents a runnable, which is allowed to throw an {@link Exception}, {@link Error} or any other
 * {@link Throwable}.
 * 
 * @author Patrick
 * @param <T>
 *            the type of the {@link Throwable} which may be thrown by this {@link ThrowingRunnable}
 */
@FunctionalInterface
public interface ThrowingRunnable <T extends Throwable> {
	
	/**
	 * executes this throwable runnable.<br>
	 * this operation is allowed to throw any {@link Throwable} of type {@code T} (or of course some
	 * other unchecked {@link Throwable}).<br>
	 * But this operation is also allowed to end normally without throwing anything.
	 * 
	 * @throws T
	 *             the type of the {@link Throwable} which is possible to be thrown in this operation.
	 */
	void run() throws T;
	
}

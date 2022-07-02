package de.hechler.patrick.zeugs.check.interfaces;


/**
 * a simple interface which contains methods to get two different values.<br>
 * additional this interface provides also optional methods to set these values.
 * 
 * @author Patrick
 * @param <A>
 *            the type of the first value
 * @param <B>
 *            the type of the second value
 */
public interface TwoValues <A, B> {
	
	/**
	 * returns the first value of the two values
	 * 
	 * @return
	 *             the first value of the two values
	 */
	A getValueA();
	
	/**
	 * returns the second value of the two values
	 * 
	 * @return
	 *             the second value of the two values
	 */
	B getValueB();
	
	/**
	 * this method sets the first value of the two values.<br>
	 * this method is optional. If it is not supported an {@link UnsupportedOperationException} will be
	 * thrown.<br>
	 * if the given {@code a} is <code>null</code> and this implementation does not allow
	 * <code>null</code> values for the first value a {@link NullPointerException} will be thrown.
	 * 
	 * @param a
	 *            the new value for the first value
	 * @throws UnsupportedOperationException
	 *             if this operation is not supported
	 * @throws NullPointerException
	 *             if {@code a} is <code>null</code> and this is forbidden
	 */
	default void setValueA(A a) throws UnsupportedOperationException, NullPointerException {
		throw new UnsupportedOperationException("setValueA");
	}
	
	/**
	 * this method sets the second value of the two values.<br>
	 * this method is optional. If it is not supported an {@link UnsupportedOperationException} will be
	 * thrown.<br>
	 * if the given {@code b} is <code>null</code> and this implementation does not allow
	 * <code>null</code> values for the second value a {@link NullPointerException} will be thrown.
	 * 
	 * @param a
	 *            the new value for the second value
	 * @throws UnsupportedOperationException
	 *             if this operation is not supported
	 * @throws NullPointerException
	 *             if {@code b} is <code>null</code> and this is forbidden
	 */
	default void setValueB(B b) throws UnsupportedOperationException, NullPointerException {
		throw new UnsupportedOperationException("setValueA");
	}
	
}

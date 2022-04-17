package de.hechler.patrick.zeugs.check;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

import de.hechler.patrick.zeugs.check.exceptions.CheckerArraysEqualsExeption;
import de.hechler.patrick.zeugs.check.exceptions.CheckerArraysNotEqualsExeption;
import de.hechler.patrick.zeugs.check.exceptions.CheckerBoolException;
import de.hechler.patrick.zeugs.check.exceptions.CheckerEqualsExeption;
import de.hechler.patrick.zeugs.check.exceptions.CheckerException;
import de.hechler.patrick.zeugs.check.exceptions.CheckerFailException;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNoInstanceException;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNotEqualsExeption;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNotGreatherEqualExeption;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNotGreatherExeption;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNotLowerEqualException;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNotLowerException;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNotNegativeException;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNotNullExeption;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNotPositiveException;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNotThrownException;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNullExeption;
import de.hechler.patrick.zeugs.check.interfaces.ThrowingRunnable;

/**
 * this class provides methods to assert statements.
 * <p>
 * the {@code fail(...)} methods will always throw a {@link CheckerFailException}.
 * <p>
 * the {@code assert...} methods has two types of ends.<br>
 * they can either end successfully or they throw a subclass of a {@link CheckerException}.
 * <p>
 * there are multiple types of the {@code assert...} methods.<br>
 * equals, not equals, greater, lower, greater equal, lower equal, null, nut null<br>
 * the equals and not equals assert methods are also available as for arrays.<br>
 * they are also as deep methods available to check for deep equality.
 * <p>
 * the {@code castNotNull(...)} methods will either throw an {@link CheckerNullExeption} if the
 * object to cast us <code>null</code> or return the given object.
 * 
 * @author Patrick
 */
public class Assert {
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * an array is equals with an other array,
	 * if they have the same {@code length} and every element is {@link Object#equals(Object)} with the
	 * element of the
	 * other array at the same index.
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the arrays are equals
	 */
	public static void assertNotArrayEquals(Object[] a, Object[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are not deep equal.<br>
	 * an array is deep equals with an other array,
	 * if they have the same {@code length} and every element is deep equals with the element of the
	 * other array at the same index.<br>
	 * if the elements are arrays, they are deep equals this definition fits.<br>
	 * else the elements are deep equals if they are {@link Object#equals(Object)}.
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the arrays are deep equals
	 */
	public static void assertDeepNotArrayEquals(Object[] a, Object[] b) throws CheckerException {
		if (Arrays.deepEquals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the a
	 * element returns <code>true</code> when the b element is wrapped to a {@link Boolean}.
	 * 
	 * @param a
	 *            the non primitive array
	 * @param b
	 *            the primitive array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(Object[] a, boolean[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null && b == null) {
				throw new CheckerArraysEqualsExeption(a, b);
			}
			return;
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !a[i].equals(b[i])) return;
		}
		throw new CheckerArraysEqualsExeption(a, b);
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the a
	 * element returns <code>true</code> when the b element is wrapped to a {@link Character}.
	 * 
	 * @param a
	 *            the non primitive array
	 * @param b
	 *            the primitive array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(Object[] a, char[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null && b == null) {
				throw new CheckerArraysEqualsExeption(a, b);
			}
			return;
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !a[i].equals(b[i])) return;
		}
		throw new CheckerArraysEqualsExeption(a, b);
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the a
	 * element returns <code>true</code> when the b element is wrapped to a {@link Long}.
	 * 
	 * @param a
	 *            the non primitive array
	 * @param b
	 *            the primitive array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(Object[] a, long[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null && b == null) {
				throw new CheckerArraysEqualsExeption(a, b);
			}
			return;
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !a[i].equals(b[i])) return;
		}
		throw new CheckerArraysEqualsExeption(a, b);
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the a
	 * element returns <code>true</code> when the b element is wrapped to a {@link Integer}.
	 * 
	 * @param a
	 *            the non primitive array
	 * @param b
	 *            the primitive array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(Object[] a, int[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null && b == null) {
				throw new CheckerArraysEqualsExeption(a, b);
			}
			return;
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !a[i].equals(b[i])) return;
		}
		throw new CheckerArraysEqualsExeption(a, b);
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the a
	 * element returns <code>true</code> when the b element is wrapped to a {@link Short}.
	 * 
	 * @param a
	 *            the non primitive array
	 * @param b
	 *            the primitive array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(Object[] a, short[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null && b == null) {
				throw new CheckerArraysEqualsExeption(a, b);
			}
			return;
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !a[i].equals(b[i])) return;
		}
		throw new CheckerArraysNotEqualsExeption(a, b);
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the a
	 * element returns <code>true</code> when the b element is wrapped to a {@link Byte}.
	 * 
	 * @param a
	 *            the non primitive array
	 * @param b
	 *            the primitive array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(Object[] a, byte[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null && b == null) {
				throw new CheckerArraysEqualsExeption(a, b);
			}
			return;
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !a[i].equals(b[i])) return;
		}
		throw new CheckerArraysNotEqualsExeption(a, b);
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the a
	 * element returns <code>true</code> when the b element is wrapped to a {@link Float}.
	 * 
	 * @param a
	 *            the non primitive array
	 * @param b
	 *            the primitive array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(Object[] a, float[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null && b == null) {
				throw new CheckerArraysEqualsExeption(a, b);
			}
			return;
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !a[i].equals(b[i])) return;
		}
		throw new CheckerArraysNotEqualsExeption(a, b);
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the a
	 * element returns <code>true</code> when the b element is wrapped to a {@link Double}.
	 * 
	 * @param a
	 *            the non primitive array
	 * @param b
	 *            the primitive array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(Object[] a, double[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null && b == null) {
				throw new CheckerArraysEqualsExeption(a, b);
			}
			return;
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !a[i].equals(b[i])) return;
		}
		throw new CheckerArraysNotEqualsExeption(a, b);
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the b
	 * element returns <code>true</code> when the a element is wrapped to a {@link Boolean}.
	 * 
	 * @param a
	 *            the primitive array
	 * @param b
	 *            the non primitive array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(boolean[] a, Object[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null && b == null) {
				throw new CheckerArraysEqualsExeption(a, b);
			}
			return;
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !b[i].equals(a[i])) return;
		}
		throw new CheckerArraysNotEqualsExeption(a, b);
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the b
	 * element returns <code>true</code> when the a element is wrapped to a {@link Character}.
	 * 
	 * @param a
	 *            the primitive array
	 * @param b
	 *            the non primitive array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(char[] a, Object[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null && b == null) {
				throw new CheckerArraysEqualsExeption(a, b);
			}
			return;
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !b[i].equals(a[i])) return;
		}
		throw new CheckerArraysNotEqualsExeption(a, b);
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the b
	 * element returns <code>true</code> when the a element is wrapped to a {@link Long}.
	 * 
	 * @param a
	 *            the primitive array
	 * @param b
	 *            the non primitive array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(long[] a, Object[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null && b == null) {
				throw new CheckerArraysEqualsExeption(a, b);
			}
			return;
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !b[i].equals(a[i])) return;
		}
		throw new CheckerArraysNotEqualsExeption(a, b);
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the b
	 * element returns <code>true</code> when the a element is wrapped to a {@link InternalError}.
	 * 
	 * @param a
	 *            the primitive array
	 * @param b
	 *            the non primitive array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(int[] a, Object[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null && b == null) {
				throw new CheckerArraysEqualsExeption(a, b);
			}
			return;
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !b[i].equals(a[i])) return;
		}
		throw new CheckerArraysNotEqualsExeption(a, b);
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the b
	 * element returns <code>true</code> when the a element is wrapped to a {@link Short}.
	 * 
	 * @param a
	 *            the primitive array
	 * @param b
	 *            the non primitive array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(short[] a, Object[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null && b == null) {
				throw new CheckerArraysEqualsExeption(a, b);
			}
			return;
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !b[i].equals(a[i])) return;
		}
		throw new CheckerArraysNotEqualsExeption(a, b);
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the b
	 * element returns <code>true</code> when the a element is wrapped to a {@link Byte}.
	 * 
	 * @param a
	 *            the primitive array
	 * @param b
	 *            the non primitive array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(byte[] a, Object[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null && b == null) {
				throw new CheckerArraysEqualsExeption(a, b);
			}
			return;
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !b[i].equals(a[i])) return;
		}
		throw new CheckerArraysNotEqualsExeption(a, b);
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the b
	 * element returns <code>true</code> when the a element is wrapped to a {@link Float}.
	 * 
	 * @param a
	 *            the primitive array
	 * @param b
	 *            the non primitive array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(float[] a, Object[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null && b == null) {
				throw new CheckerArraysEqualsExeption(a, b);
			}
			return;
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !b[i].equals(a[i])) return;
		}
		throw new CheckerArraysNotEqualsExeption(a, b);
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the b
	 * element returns <code>true</code> when the a element is wrapped to a {@link Double}.
	 * 
	 * @param a
	 *            the primitive array
	 * @param b
	 *            the non primitive array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(double[] a, Object[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null && b == null) {
				throw new CheckerArraysEqualsExeption(a, b);
			}
			return;
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !b[i].equals(a[i])) return;
		}
		throw new CheckerArraysNotEqualsExeption(a, b);
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when they have the same value (both are
	 * <code>true</code> or both are <code>false</code>).
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(boolean[] a, boolean[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when they have the same value
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(char[] a, char[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when they have the same value
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(long[] a, long[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when they have the same value
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(int[] a, int[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when they have the same value
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(short[] a, short[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when they have the same value
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(byte[] a, byte[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when they have the same value
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(float[] a, float[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are not equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when they have the same value
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the two arrays are equal.
	 */
	public static void assertNotArrayEquals(double[] a, double[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * an array is equals with an other array,
	 * if they have the same {@code length} and every element is {@link Object#equals(Object)} with the
	 * element of the
	 * other array at the same index.
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the arrays are not equals
	 */
	public static void assertArrayEquals(Object[] a, Object[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are deep equal.<br>
	 * an array is deep equals with an other array,
	 * if they have the same {@code length} and every element is deep equals with the element of the
	 * other array at the same index.<br>
	 * if the elements are arrays, they are deep equals this definition fits.<br>
	 * else the elements are deep equals if they are {@link Object#equals(Object)}.
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the arrays are not deep equals
	 */
	public static void assertDeepArrayEquals(Object[] a, Object[] b) throws CheckerException {
		if ( !Arrays.deepEquals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the a
	 * element returns <code>true</code> when the b element is wrapped to a {@link Boolean}.
	 * 
	 * @param a
	 *            the non primitive array
	 * @param b
	 *            the primitive array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(Object[] a, boolean[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null ^ b == null) throw new CheckerArraysNotEqualsExeption(a, b);
			else return;
		}
		if (a.length != b.length) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !a[i].equals(b[i])) {
				throw new CheckerArraysNotEqualsExeption(a, b);
			}
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the a
	 * element returns <code>true</code> when the b element is wrapped to a {@link Character}.
	 * 
	 * @param a
	 *            the non primitive array
	 * @param b
	 *            the primitive array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(Object[] a, char[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null ^ b == null) throw new CheckerArraysNotEqualsExeption(a, b);
			else return;
		}
		if (a.length != b.length) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !a[i].equals(b[i])) {
				throw new CheckerArraysNotEqualsExeption(a, b);
			}
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the a
	 * element returns <code>true</code> when the b element is wrapped to a {@link Long}.
	 * 
	 * @param a
	 *            the non primitive array
	 * @param b
	 *            the primitive array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(Object[] a, long[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null ^ b == null) throw new CheckerArraysNotEqualsExeption(a, b);
			else return;
		}
		if (a.length != b.length) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !a[i].equals(b[i])) {
				throw new CheckerArraysNotEqualsExeption(a, b);
			}
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the a
	 * element returns <code>true</code> when the b element is wrapped to a {@link Integer}.
	 * 
	 * @param a
	 *            the non primitive array
	 * @param b
	 *            the primitive array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(Object[] a, int[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null ^ b == null) throw new CheckerArraysNotEqualsExeption(a, b);
			else return;
		}
		if (a.length != b.length) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !a[i].equals(b[i])) {
				throw new CheckerArraysNotEqualsExeption(a, b);
			}
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the a
	 * element returns <code>true</code> when the b element is wrapped to a {@link Short}.
	 * 
	 * @param a
	 *            the non primitive array
	 * @param b
	 *            the primitive array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(Object[] a, short[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null ^ b == null) throw new CheckerArraysNotEqualsExeption(a, b);
			else return;
		}
		if (a.length != b.length) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !a[i].equals(b[i])) {
				throw new CheckerArraysNotEqualsExeption(a, b);
			}
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the a
	 * element returns <code>true</code> when the b element is wrapped to a {@link Byte}.
	 * 
	 * @param a
	 *            the non primitive array
	 * @param b
	 *            the primitive array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(Object[] a, byte[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null ^ b == null) throw new CheckerArraysNotEqualsExeption(a, b);
			else return;
		}
		if (a.length != b.length) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !a[i].equals(b[i])) {
				throw new CheckerArraysNotEqualsExeption(a, b);
			}
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the a
	 * element returns <code>true</code> when the b element is wrapped to a {@link Float}.
	 * 
	 * @param a
	 *            the non primitive array
	 * @param b
	 *            the primitive array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(Object[] a, float[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null ^ b == null) throw new CheckerArraysNotEqualsExeption(a, b);
			else return;
		}
		if (a.length != b.length) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !a[i].equals(b[i])) {
				throw new CheckerArraysNotEqualsExeption(a, b);
			}
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the a
	 * element returns <code>true</code> when the b element is wrapped to a {@link Double}.
	 * 
	 * @param a
	 *            the non primitive array
	 * @param b
	 *            the primitive array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(Object[] a, double[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null ^ b == null) throw new CheckerArraysNotEqualsExeption(a, b);
			else return;
		}
		if (a.length != b.length) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !a[i].equals(b[i])) {
				throw new CheckerArraysNotEqualsExeption(a, b);
			}
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the b
	 * element returns <code>true</code> when the a element is wrapped to a {@link Boolean}.
	 * 
	 * @param a
	 *            the primitive array
	 * @param b
	 *            the non primitive array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(boolean[] a, Object[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null ^ b == null) throw new CheckerArraysNotEqualsExeption(a, b);
			else return;
		}
		if (a.length != b.length) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !b[i].equals(a[i])) {
				throw new CheckerArraysNotEqualsExeption(a, b);
			}
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the b
	 * element returns <code>true</code> when the a element is wrapped to a {@link Character}.
	 * 
	 * @param a
	 *            the primitive array
	 * @param b
	 *            the non primitive array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(char[] a, Object[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null ^ b == null) throw new CheckerArraysNotEqualsExeption(a, b);
			else return;
		}
		if (a.length != b.length) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !b[i].equals(a[i])) {
				throw new CheckerArraysNotEqualsExeption(a, b);
			}
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the b
	 * element returns <code>true</code> when the a element is wrapped to a {@link Long}.
	 * 
	 * @param a
	 *            the primitive array
	 * @param b
	 *            the non primitive array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(long[] a, Object[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null ^ b == null) throw new CheckerArraysNotEqualsExeption(a, b);
			else return;
		}
		if (a.length != b.length) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !b[i].equals(a[i])) {
				throw new CheckerArraysNotEqualsExeption(a, b);
			}
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the b
	 * element returns <code>true</code> when the a element is wrapped to a {@link Integer}.
	 * 
	 * @param a
	 *            the primitive array
	 * @param b
	 *            the non primitive array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(int[] a, Object[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null ^ b == null) throw new CheckerArraysNotEqualsExeption(a, b);
			else return;
		}
		if (a.length != b.length) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !b[i].equals(a[i])) {
				throw new CheckerArraysNotEqualsExeption(a, b);
			}
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the b
	 * element returns <code>true</code> when the a element is wrapped to a {@link Short}.
	 * 
	 * @param a
	 *            the primitive array
	 * @param b
	 *            the non primitive array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(short[] a, Object[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null ^ b == null) throw new CheckerArraysNotEqualsExeption(a, b);
			else return;
		}
		if (a.length != b.length) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !b[i].equals(a[i])) {
				throw new CheckerArraysNotEqualsExeption(a, b);
			}
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the b
	 * element returns <code>true</code> when the a element is wrapped to a {@link Byte}.
	 * 
	 * @param a
	 *            the primitive array
	 * @param b
	 *            the non primitive array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(byte[] a, Object[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null ^ b == null) throw new CheckerArraysNotEqualsExeption(a, b);
			else return;
		}
		if (a.length != b.length) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !b[i].equals(a[i])) {
				throw new CheckerArraysNotEqualsExeption(a, b);
			}
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the b
	 * element returns <code>true</code> when the a element is wrapped to a {@link Float}.
	 * 
	 * @param a
	 *            the primitive array
	 * @param b
	 *            the non primitive array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(float[] a, Object[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null ^ b == null) throw new CheckerArraysNotEqualsExeption(a, b);
			else return;
		}
		if (a.length != b.length) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !b[i].equals(a[i])) {
				throw new CheckerArraysNotEqualsExeption(a, b);
			}
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when {@link Object#equals(Object)} from the b
	 * element returns <code>true</code> when the a element is wrapped to a {@link Double}.
	 * 
	 * @param a
	 *            the primitive array
	 * @param b
	 *            the non primitive array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(double[] a, Object[] b) throws CheckerException {
		if (a == null || b == null) {
			if (a == null ^ b == null) throw new CheckerArraysNotEqualsExeption(a, b);
			else return;
		}
		if (a.length != b.length) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
		for (int i = 0; i < b.length; i ++ ) {
			if ( !b[i].equals(a[i])) {
				throw new CheckerArraysNotEqualsExeption(a, b);
			}
		}
	}
	
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when they have the same value (both are
	 * <code>true</code> or both are <code>false</code>).
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(boolean[] a, boolean[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when they have the same value.
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(char[] a, char[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when they have the same value.
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(long[] a, long[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when they have the same value.
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(int[] a, int[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when they have the same value.
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(short[] a, short[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when they have the same value.
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(byte[] a, byte[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when they have the same value.
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(float[] a, float[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two arrays are equal.<br>
	 * the arrays are equal when for elements from array {@code a} are equal to the element with the
	 * same index of the array {@code b}.<br>
	 * two elements from {@code a} and {@code b} are equal when they have the same value.
	 * 
	 * @param a
	 *            the first array
	 * @param b
	 *            the second array
	 * @throws CheckerException
	 *             if the two arrays are not equal.
	 */
	public static void assertArrayEquals(double[] a, double[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	
	/**
	 * asserts that the two objects are references to the same instance or that both are
	 * <code>null</code>.<br>
	 * the two objects reference the same instance only when {@code a == b} is <code>true</code>
	 * 
	 * @param a
	 *            the first object reference
	 * @param b
	 *            the second object reference
	 * @throws CheckerException
	 *             if the two objects are not the same.
	 */
	public static void assertSame(Object a, Object b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two objects are equal.<br>
	 * the two objects are equal if both are <code>null</code> or {@link Object#equals(Object)} returns
	 * <code>true</code>
	 * 
	 * @param a
	 *            the first object
	 * @param b
	 *            the second object
	 * @throws CheckerException
	 *             if the two objects are not equal.
	 */
	public static void assertEquals(Object a, Object b) throws CheckerException {
		if ( !Objects.equals(a, b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two objects are deep equal.<br>
	 * the two objects are deep equal if both are <code>null</code> or {@link Object#equals(Object)}
	 * returns <code>true</code><br>
	 * or if they are arrays they are deep equal if they have the same {@code length} and the elements
	 * with the same index are deep equal.
	 * 
	 * @param a
	 *            the first object
	 * @param b
	 *            the second object
	 * @throws CheckerException
	 *             if the two objects are not equal.
	 */
	public static void assertDeepEquals(Object a, Object b) throws CheckerException {
		if ( !Objects.deepEquals(a, b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Boolean}.
	 * 
	 * @param a
	 *            the object
	 * @param b
	 *            the primitive value
	 * @throws CheckerException
	 *             if the object is not equal to the primitive value.
	 */
	public static void assertEquals(Object a, boolean b) throws CheckerException {
		if (a == null || !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Character}.
	 * 
	 * @param a
	 *            the object
	 * @param b
	 *            the primitive value
	 * @throws CheckerException
	 *             if the object is not equal to the primitive value.
	 */
	public static void assertEquals(Object a, char b) throws CheckerException {
		if (a == null || !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Long}.
	 * 
	 * @param a
	 *            the object
	 * @param b
	 *            the primitive value
	 * @throws CheckerException
	 *             if the object is not equal to the primitive value.
	 */
	public static void assertEquals(Object a, long b) throws CheckerException {
		if (a == null || !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Integer}.
	 * 
	 * @param a
	 *            the object
	 * @param b
	 *            the primitive value
	 * @throws CheckerException
	 *             if the object is not equal to the primitive value.
	 */
	public static void assertEquals(Object a, int b) throws CheckerException {
		if (a == null || !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Short}.
	 * 
	 * @param a
	 *            the object
	 * @param b
	 *            the primitive value
	 * @throws CheckerException
	 *             if the object is not equal to the primitive value.
	 */
	public static void assertEquals(Object a, short b) throws CheckerException {
		if (a == null || !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Byte}.
	 * 
	 * @param a
	 *            the object
	 * @param b
	 *            the primitive value
	 * @throws CheckerException
	 *             if the object is not equal to the primitive value.
	 */
	public static void assertEquals(Object a, byte b) throws CheckerException {
		if (a == null || !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Float}.
	 * 
	 * @param a
	 *            the object
	 * @param b
	 *            the primitive value
	 * @throws CheckerException
	 *             if the object is not equal to the primitive value.
	 */
	public static void assertEquals(Object a, float b) throws CheckerException {
		if (a == null || !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Double}.
	 * 
	 * @param a
	 *            the object
	 * @param b
	 *            the primitive value
	 * @throws CheckerException
	 *             if the object is not equal to the primitive value.
	 */
	public static void assertEquals(Object a, double b) throws CheckerException {
		if (a == null || !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Boolean}.
	 * 
	 * @param a
	 *            the primitive value
	 * @param b
	 *            the object
	 * @throws CheckerException
	 *             if the object is not equal to the primitive value.
	 */
	public static void assertEquals(boolean a, Object b) throws CheckerException {
		if (b == null || !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Character}.
	 * 
	 * @param a
	 *            the primitive value
	 * @param b
	 *            the object
	 * @throws CheckerException
	 *             if the object is not equal to the primitive value.
	 */
	public static void assertEquals(char a, Object b) throws CheckerException {
		if (b == null || !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Long}.
	 * 
	 * @param a
	 *            the primitive value
	 * @param b
	 *            the object
	 * @throws CheckerException
	 *             if the object is not equal to the primitive value.
	 */
	public static void assertEquals(long a, Object b) throws CheckerException {
		if (b == null || !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Integer}.
	 * 
	 * @param a
	 *            the primitive value
	 * @param b
	 *            the object
	 * @throws CheckerException
	 *             if the object is not equal to the primitive value.
	 */
	public static void assertEquals(int a, Object b) throws CheckerException {
		if (b == null || !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Short}.
	 * 
	 * @param a
	 *            the primitive value
	 * @param b
	 *            the object
	 * @throws CheckerException
	 *             if the object is not equal to the primitive value.
	 */
	public static void assertEquals(short a, Object b) throws CheckerException {
		if (b == null || !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Byte}.
	 * 
	 * @param a
	 *            the primitive value
	 * @param b
	 *            the object
	 * @throws CheckerException
	 *             if the object is not equal to the primitive value.
	 */
	public static void assertEquals(byte a, Object b) throws CheckerException {
		if (b == null || !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Float}.
	 * 
	 * @param a
	 *            the primitive value
	 * @param b
	 *            the object
	 * @throws CheckerException
	 *             if the object is not equal to the primitive value.
	 */
	public static void assertEquals(float a, Object b) throws CheckerException {
		if (b == null || !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Double}.
	 * 
	 * @param a
	 *            the primitive value
	 * @param b
	 *            the object
	 * @throws CheckerException
	 *             if the object is not equal to the primitive value.
	 */
	public static void assertEquals(double a, Object b) throws CheckerException {
		if (b == null || !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that two primitive values are equal.<br>
	 * the two values are equal if {@code a == b} is <code>true</code> (if they both are
	 * <code>true</code> or if both are <code>false</code>)
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the two values are not the equal.
	 */
	public static void assertEquals(boolean a, boolean b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that two primitive values are equal.<br>
	 * the two values are equal if {@code a == b} is <code>true</code>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the two values are not the equal.
	 */
	public static void assertEquals(char a, char b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that two primitive values are equal.<br>
	 * the two values are equal if {@code a == b} is <code>true</code>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the two values are not the equal.
	 */
	public static void assertEquals(long a, long b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that two primitive values are equal.<br>
	 * the two values are equal if {@code a == b} is <code>true</code>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the two values are not the equal.
	 */
	public static void assertEquals(int a, int b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that two primitive values are equal.<br>
	 * the two values are equal if {@code a == b} is <code>true</code>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the two values are not the equal.
	 */
	public static void assertEquals(short a, short b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that two primitive values are equal.<br>
	 * the two values are equal if {@code a == b} is <code>true</code>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the two values are not the equal.
	 */
	public static void assertEquals(byte a, byte b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that two primitive values are equal.<br>
	 * the two values are equal if {@code a == b} is <code>true</code>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the two values are not the equal.
	 */
	public static void assertEquals(float a, float b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that two primitive values are equal.<br>
	 * the two values are equal if {@code a == b} is <code>true</code>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the two values are not the equal.
	 */
	public static void assertEquals(double a, double b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	
	/**
	 * asserts that the two objects are references to the different instances and that not both are
	 * <code>null</code>.<br>
	 * the two objects reference the same instance only when {@code a == b} is <code>true</code>
	 * 
	 * @param a
	 *            the first object reference
	 * @param b
	 *            the second object reference
	 * @throws CheckerException
	 *             if the two objects are the same.
	 */
	public static void assertNotSame(Object a, Object b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two objects are not equal.<br>
	 * the two objects are equal if both are <code>null</code> or {@link Object#equals(Object)} returns
	 * <code>true</code>
	 * 
	 * @param a
	 *            the first object
	 * @param b
	 *            the second object
	 * @throws CheckerException
	 *             if the two objects are equal.
	 */
	public static void assertNotEquals(Object a, Object b) throws CheckerException {
		if (Objects.equals(a, b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the two objects are not deep equal.<br>
	 * the two objects are deep equal if both are <code>null</code> or {@link Object#equals(Object)}
	 * returns <code>true</code><br>
	 * or if they are arrays they are deep equal if they have the same {@code length} and the elements
	 * with the same index are deep equal.
	 * 
	 * @param a
	 *            the first object
	 * @param b
	 *            the second object
	 * @throws CheckerException
	 *             if the two objects are equal.
	 */
	public static void assertDeepNotEquals(Object a, Object b) throws CheckerException {
		if (Objects.deepEquals(a, b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is not equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Boolean}.
	 * 
	 * @param a
	 *            the object
	 * @param b
	 *            the primitive value
	 * @throws CheckerException
	 *             if the object is equal to the primitive value.
	 */
	public static void assertNotEquals(Object a, boolean b) throws CheckerException {
		if (a.equals(b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is not equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Character}.
	 * 
	 * @param a
	 *            the object
	 * @param b
	 *            the primitive value
	 * @throws CheckerException
	 *             if the object is equal to the primitive value.
	 */
	public static void assertNotEquals(Object a, char b) throws CheckerException {
		if (a.equals(b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is not equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Long}.
	 * 
	 * @param a
	 *            the object
	 * @param b
	 *            the primitive value
	 * @throws CheckerException
	 *             if the object is equal to the primitive value.
	 */
	public static void assertNotEquals(Object a, long b) throws CheckerException {
		if (a.equals(b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is not equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Integer}.
	 * 
	 * @param a
	 *            the object
	 * @param b
	 *            the primitive value
	 * @throws CheckerException
	 *             if the object is equal to the primitive value.
	 */
	public static void assertNotEquals(Object a, int b) throws CheckerException {
		if (a.equals(b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is not equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Short}.
	 * 
	 * @param a
	 *            the object
	 * @param b
	 *            the primitive value
	 * @throws CheckerException
	 *             if the object is equal to the primitive value.
	 */
	public static void assertNotEquals(Object a, short b) throws CheckerException {
		if (a.equals(b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is not equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Byte}.
	 * 
	 * @param a
	 *            the object
	 * @param b
	 *            the primitive value
	 * @throws CheckerException
	 *             if the object is equal to the primitive value.
	 */
	public static void assertNotEquals(Object a, byte b) throws CheckerException {
		if (a.equals(b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is not equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Float}.
	 * 
	 * @param a
	 *            the object
	 * @param b
	 *            the primitive value
	 * @throws CheckerException
	 *             if the object is equal to the primitive value.
	 */
	public static void assertNotEquals(Object a, float b) throws CheckerException {
		if (a.equals(b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is not equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Double}.
	 * 
	 * @param a
	 *            the object
	 * @param b
	 *            the primitive value
	 * @throws CheckerException
	 *             if the object is equal to the primitive value.
	 */
	public static void assertNotEquals(Object a, double b) throws CheckerException {
		if (a.equals(b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is not equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Boolean}.
	 * 
	 * @param a
	 *            the primitive value
	 * @param b
	 *            the object
	 * @throws CheckerException
	 *             if the object is equal to the primitive value.
	 */
	public static void assertNotEquals(boolean a, Object b) throws CheckerException {
		if (b.equals(a)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is not equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Character}.
	 * 
	 * @param a
	 *            the primitive value
	 * @param b
	 *            the object
	 * @throws CheckerException
	 *             if the object is equal to the primitive value.
	 */
	public static void assertNotEquals(char a, Object b) throws CheckerException {
		if (b.equals(a)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is not equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link long}.
	 * 
	 * @param a
	 *            the primitive value
	 * @param b
	 *            the object
	 * @throws CheckerException
	 *             if the object is equal to the primitive value.
	 */
	public static void assertNotEquals(long a, Object b) throws CheckerException {
		if (b.equals(a)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is not equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Integer}.
	 * 
	 * @param a
	 *            the primitive value
	 * @param b
	 *            the object
	 * @throws CheckerException
	 *             if the object is equal to the primitive value.
	 */
	public static void assertNotEquals(int a, Object b) throws CheckerException {
		if (b.equals(a)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is not equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Short}.
	 * 
	 * @param a
	 *            the primitive value
	 * @param b
	 *            the object
	 * @throws CheckerException
	 *             if the object is equal to the primitive value.
	 */
	public static void assertNotEquals(short a, Object b) throws CheckerException {
		if (b.equals(a)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is not equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Byte}.
	 * 
	 * @param a
	 *            the primitive value
	 * @param b
	 *            the object
	 * @throws CheckerException
	 *             if the object is equal to the primitive value.
	 */
	public static void assertNotEquals(byte a, Object b) throws CheckerException {
		if (b.equals(a)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is not equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Float}.
	 * 
	 * @param a
	 *            the primitive value
	 * @param b
	 *            the object
	 * @throws CheckerException
	 *             if the object is equal to the primitive value.
	 */
	public static void assertNotEquals(float a, Object b) throws CheckerException {
		if (b.equals(a)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that the object is not equal to the primitive value.<br>
	 * the object is equal to the primitive value, if it is not <code>null</code> and the
	 * {@link Object#equals(Object)} methods returns <code>true</code> when it gets the primitive value
	 * wrapped to a {@link Double}.
	 * 
	 * @param a
	 *            the primitive value
	 * @param b
	 *            the object
	 * @throws CheckerException
	 *             if the object is equal to the primitive value.
	 */
	public static void assertNotEquals(double a, Object b) throws CheckerException {
		if (b.equals(a)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that two primitive values not are equal.<br>
	 * the two values are equal if {@code a == b} is <code>true</code> (if they both are
	 * <code>true</code> or if both are <code>false</code>)
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the two values are the equal.
	 */
	public static void assertNotEquals(boolean a, boolean b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that two primitive values not are equal.<br>
	 * the two values are equal if {@code a == b} is <code>true</code>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the two values are the equal.
	 */
	public static void assertNotEquals(char a, char b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that two primitive values not are equal.<br>
	 * the two values are equal if {@code a == b} is <code>true</code>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the two values are the equal.
	 */
	public static void assertNotEquals(long a, long b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that two primitive values not are equal.<br>
	 * the two values are equal if {@code a == b} is <code>true</code>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the two values are the equal.
	 */
	public static void assertNotEquals(int a, int b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that two primitive values not are equal.<br>
	 * the two values are equal if {@code a == b} is <code>true</code>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the two values are the equal.
	 */
	public static void assertNotEquals(short a, short b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that two primitive values not are equal.<br>
	 * the two values are equal if {@code a == b} is <code>true</code>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the two values are the equal.
	 */
	public static void assertNotEquals(byte a, byte b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that two primitive values not are equal.<br>
	 * the two values are equal if {@code a == b} is <code>true</code>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the two values are the equal.
	 */
	public static void assertNotEquals(float a, float b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	/**
	 * asserts that two primitive values not are equal.<br>
	 * the two values are equal if {@code a == b} is <code>true</code>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the two values are the equal.
	 */
	public static void assertNotEquals(double a, double b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	
	/**
	 * asserts that the first value is lower than the second value or that both values are equal.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is greater than the second value.
	 */
	public static void assertLowerEqual(byte a, byte b) throws CheckerException {
		if (a > b) {
			throw new CheckerNotLowerEqualException(a, b);
		}
	}
	
	/**
	 * asserts that the first value is lower than the second value or that both values are equal.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is greater than the second value.
	 */
	public static void assertLowerEqual(short a, short b) throws CheckerException {
		if (a > b) {
			throw new CheckerNotLowerEqualException(a, b);
		}
	}
	
	/**
	 * asserts that the first value is lower than the second value or that both values are equal.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is greater than the second value.
	 */
	public static void assertLowerEqual(int a, int b) throws CheckerException {
		if (a > b) {
			throw new CheckerNotLowerEqualException(a, b);
		}
	}
	
	/**
	 * asserts that the first value is lower than the second value or that both values are equal.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is greater than the second value.
	 */
	public static void assertLowerEqual(long a, long b) throws CheckerException {
		if (a > b) {
			throw new CheckerNotLowerEqualException(a, b);
		}
	}
	
	/**
	 * asserts that the first value is lower than the second value or that both values are equal.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is greater than the second value.
	 */
	public static void assertLowerEqual(float a, float b) throws CheckerException {
		if (a > b) {
			throw new CheckerNotLowerEqualException(a, b);
		}
	}
	
	/**
	 * asserts that the first value is lower than the second value or that both values are equal.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is greater than the second value.
	 */
	public static void assertLowerEqual(double a, double b) throws CheckerException {
		if (a > b) {
			throw new CheckerNotLowerEqualException(a, b);
		}
	}
	
	
	/**
	 * asserts that the first value is lower than the second value.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is greater than the second value or if both values are equal.
	 */
	public static void assertLower(byte a, byte b) throws CheckerException {
		if (a >= b) {
			throw new CheckerNotLowerException(a, b);
		}
	}
	
	/**
	 * asserts that the first value is lower than the second value.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is greater than the second value or if both values are equal.
	 */
	public static void assertLower(short a, short b) throws CheckerException {
		if (a >= b) {
			throw new CheckerNotLowerException(a, b);
		}
	}
	
	/**
	 * asserts that the first value is lower than the second value.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is greater than the second value or if both values are equal.
	 */
	public static void assertLower(int a, int b) throws CheckerException {
		if (a >= b) {
			throw new CheckerNotLowerException(a, b);
		}
	}
	
	/**
	 * asserts that the first value is lower than the second value.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is greater than the second value or if both values are equal.
	 */
	public static void assertLower(long a, long b) throws CheckerException {
		if (a >= b) {
			throw new CheckerNotLowerException(a, b);
		}
	}
	
	/**
	 * asserts that the first value is lower than the second value.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is greater than the second value or if both values are equal.
	 */
	public static void assertLower(float a, float b) throws CheckerException {
		if (a >= b) {
			throw new CheckerNotLowerException(a, b);
		}
	}
	
	/**
	 * asserts that the first value is lower than the second value.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is greater than the second value or if both values are equal.
	 */
	public static void assertLower(double a, double b) throws CheckerException {
		if (a >= b) {
			throw new CheckerNotLowerException(a, b);
		}
	}
	
	
	/**
	 * asserts that the first value is greater than the second value or that both are equal.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is lower than the second value.
	 */
	public static void assertGreatherEqual(byte a, byte b) throws CheckerException {
		if (a < b) {
			throw new CheckerNotGreatherEqualExeption(a, b);
		}
	}
	
	/**
	 * asserts that the first value is greater than the second value or that both are equal.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is lower than the second value.
	 */
	public static void assertGreatherEqual(short a, short b) throws CheckerException {
		if (a < b) {
			throw new CheckerNotGreatherEqualExeption(a, b);
		}
	}
	
	/**
	 * asserts that the first value is greater than the second value or that both are equal.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is lower than the second value.
	 */
	public static void assertGreatherEqual(int a, int b) throws CheckerException {
		if (a < b) {
			throw new CheckerNotGreatherEqualExeption(a, b);
		}
	}
	
	/**
	 * asserts that the first value is greater than the second value or that both are equal.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is lower than the second value.
	 */
	public static void assertGreatherEqual(long a, long b) throws CheckerException {
		if (a < b) {
			throw new CheckerNotGreatherEqualExeption(a, b);
		}
	}
	
	/**
	 * asserts that the first value is greater than the second value or that both are equal.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is lower than the second value.
	 */
	public static void assertGreatherEqual(float a, float b) throws CheckerException {
		if (a < b) {
			throw new CheckerNotGreatherEqualExeption(a, b);
		}
	}
	
	/**
	 * asserts that the first value is greater than the second value or that both are equal.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is lower than the second value.
	 */
	public static void assertGreatherEqual(double a, double b) throws CheckerException {
		if (a < b) {
			throw new CheckerNotGreatherEqualExeption(a, b);
		}
	}
	
	
	/**
	 * asserts that the first value is greater than the second value.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is lower than the second value or if both values are equal.
	 */
	public static void assertGreather(byte a, byte b) throws CheckerException {
		if (a <= b) {
			throw new CheckerNotGreatherExeption(a, b);
		}
	}
	
	/**
	 * asserts that the first value is greater than the second value.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is lower than the second value or if both values are equal.
	 */
	public static void assertGreather(short a, short b) throws CheckerException {
		if (a <= b) {
			throw new CheckerNotGreatherExeption(a, b);
		}
	}
	
	/**
	 * asserts that the first value is greater than the second value.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is lower than the second value or if both values are equal.
	 */
	public static void assertGreather(int a, int b) throws CheckerException {
		if (a <= b) {
			throw new CheckerNotGreatherExeption(a, b);
		}
	}
	
	/**
	 * asserts that the first value is greater than the second value.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is lower than the second value or if both values are equal.
	 */
	public static void assertGreather(long a, long b) throws CheckerException {
		if (a <= b) {
			throw new CheckerNotGreatherExeption(a, b);
		}
	}
	
	/**
	 * asserts that the first value is greater than the second value.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is lower than the second value or if both values are equal.
	 */
	public static void assertGreather(float a, float b) throws CheckerException {
		if (a <= b) {
			throw new CheckerNotGreatherExeption(a, b);
		}
	}
	
	/**
	 * asserts that the first value is greater than the second value.<br>
	 * two values are equal if {@code a == b} is <code>true</code>.<br>
	 * two first value is greater than the second value if {@code a > b} is <code>true</code>.<br>
	 * two first value is lower than the second value if {@code a < b} is <code>true</code>.<br>
	 * 
	 * @param a
	 *            the first value
	 * @param b
	 *            the second value
	 * @throws CheckerException
	 *             if the first value is lower than the second value or if both values are equal.
	 */
	public static void assertGreather(double a, double b) throws CheckerException {
		if (a <= b) {
			throw new CheckerNotGreatherExeption(a, b);
		}
	}
	
	/**
	 * asserts that the given object reference is a <code>null</code> reference.<br>
	 * a object is a <code>null</code> reference if {@code a == null} is <code>true</code>
	 * 
	 * @param a
	 *            the object reference
	 * @param msg
	 *            the message if the object is no <code>null</code> reference
	 * @throws CheckerException
	 *             if the object reference is not a <code>null</code> reference
	 */
	public static void assertNull(Object a, String msg) throws CheckerException {
		if (a != null) {
			throw new CheckerNotNullExeption(a, msg);
		}
	}
	
	/**
	 * asserts that the given object reference is a <code>null</code> reference.<br>
	 * a object is a <code>null</code> reference if {@code a == null} is <code>true</code>
	 * 
	 * @param a
	 *            the object reference
	 * @param msg
	 *            the message if the object is no <code>null</code> reference
	 * @throws CheckerException
	 *             if the object reference is not a <code>null</code> reference
	 */
	public static void assertNull(Object a, Supplier <String> msg) throws CheckerException {
		if (a != null) {
			throw new CheckerNotNullExeption(a, msg.get());
		}
	}
	
	/**
	 * asserts that the given object reference is a <code>null</code> reference.<br>
	 * a object is a <code>null</code> reference if {@code a == null} is <code>true</code>
	 * 
	 * @param a
	 *            the object reference
	 * @throws CheckerException
	 *             if the object reference is not a <code>null</code> reference
	 */
	public static void assertNull(Object a) throws CheckerException {
		if (a != null) {
			throw new CheckerNotNullExeption(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is zero.<br>
	 * the value is zero if {@code a == 0} is <code>true</code>
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is not zero
	 */
	public static void assertNull(byte a) throws CheckerException {
		if (a != 0) {
			throw new CheckerNotNullExeption(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is zero.<br>
	 * the value is zero if {@code a == 0} is <code>true</code>
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is not zero
	 */
	public static void assertNull(short a) throws CheckerException {
		if (a != 0) {
			throw new CheckerNotNullExeption(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is zero.<br>
	 * the value is zero if {@code a == 0} is <code>true</code>
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is not zero
	 */
	public static void assertNull(int a) throws CheckerException {
		if (a != 0) {
			throw new CheckerNotNullExeption(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is zero.<br>
	 * the value is zero if {@code a == 0L} is <code>true</code>
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is not zero
	 */
	public static void assertNull(long a) throws CheckerException {
		if (a != 0L) {
			throw new CheckerNotNullExeption(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is zero.<br>
	 * the value is zero if {@code a == 0.0f} is <code>true</code>
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is not zero
	 */
	public static void assertNull(float a) throws CheckerException {
		if (a != 0.0f) {
			throw new CheckerNotNullExeption(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is zero.<br>
	 * the value is zero if {@code a == 0.0d} is <code>true</code>
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is not zero
	 */
	public static void assertNull(double a) throws CheckerException {
		if (a != 0.0d) {
			throw new CheckerNotNullExeption(a);
		}
	}
	
	/**
	 * asserts that the given object reference is no <code>null</code> reference.<br>
	 * a object reference is a <code>null</code> reference if {@code a == null} is <code>true</code>
	 * 
	 * @param a
	 *            the object reference
	 * @param msg
	 *            the error message if the object is a <code>null</code> reference
	 * @throws CheckerException
	 *             if the object is a <code>null</code> reference
	 */
	public static void assertNotNull(Object a, String msg) throws CheckerException {
		if (a == null) {
			throw new CheckerNullExeption(msg, Object.class);
		}
	}
	
	/**
	 * asserts that the given object reference is no <code>null</code> reference.<br>
	 * a object reference is a <code>null</code> reference if {@code a == null} is <code>true</code>
	 * 
	 * @param a
	 *            the object reference
	 * @param msg
	 *            the error message if the object is a <code>null</code> reference
	 * @throws CheckerException
	 *             if the object is a <code>null</code> reference
	 */
	public static void assertNotNull(Object a, Supplier <String> msg) throws CheckerException {
		if (a == null) {
			throw new CheckerNullExeption(msg.get(), Object.class);
		}
	}
	
	/**
	 * asserts that the given object reference is no <code>null</code> reference.<br>
	 * a object reference is a <code>null</code> reference if {@code a == null} is <code>true</code>
	 * 
	 * @param a
	 *            the object reference
	 * @throws CheckerException
	 *             if the object is a <code>null</code> reference
	 */
	public static void assertNotNull(Object a) throws CheckerException {
		if (a == null) {
			throw new CheckerNullExeption(Object.class);
		}
	}
	
	/**
	 * asserts that the given primitive value is not zero.<br>
	 * the value is zero if {@code a == 0} is <code>true</code>
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is not zero
	 */
	public static void assertNotNull(byte a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption(Byte.TYPE);
		}
	}
	
	/**
	 * asserts that the given primitive value is not zero.<br>
	 * the value is zero if {@code a == 0} is <code>true</code>
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is not zero
	 */
	public static void assertNotNull(short a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption(Short.TYPE);
		}
	}
	
	/**
	 * asserts that the given primitive value is not zero.<br>
	 * the value is zero if {@code a == 0} is <code>true</code>
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is not zero
	 */
	public static void assertNotNull(int a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption(Integer.TYPE);
		}
	}
	
	/**
	 * asserts that the given primitive value is not zero.<br>
	 * the value is zero if {@code a == 0} is <code>true</code>
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is not zero
	 */
	public static void assertNotNull(long a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption(Long.TYPE);
		}
	}
	
	/**
	 * asserts that the given primitive value is not zero.<br>
	 * the value is zero if {@code a == 0} is <code>true</code>
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is not zero
	 */
	public static void assertNotNull(float a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption(Float.TYPE);
		}
	}
	
	/**
	 * asserts that the given primitive value is not zero.<br>
	 * the value is zero if {@code a == 0} is <code>true</code>
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is not zero
	 */
	public static void assertNotNull(double a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption(Double.TYPE);
		}
	}
	
	/**
	 * asserts that the given primitive value is positive.<br>
	 * the value is positive if it is greater zero (if {@code a > 0} is <code>true</code>)
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is lower than zero or equal to zero
	 */
	public static void assertPositive(byte a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotPositiveException(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is positive.<br>
	 * the value is positive if it is greater zero (if {@code a > 0} is <code>true</code>)
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is lower than zero or equal to zero
	 */
	public static void assertPositive(short a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotPositiveException(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is positive.<br>
	 * the value is positive if it is greater zero (if {@code a > 0} is <code>true</code>)
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is lower than zero or equal to zero
	 */
	public static void assertPositive(int a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotPositiveException(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is positive.<br>
	 * the value is positive if it is greater zero (if {@code a > 0l} is <code>true</code>)
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is lower than zero or equal to zero
	 */
	public static void assertPositive(long a) throws CheckerException {
		if (a <= 0l) {
			throw new CheckerNotPositiveException(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is positive.<br>
	 * the value is positive if it is greater zero (if {@code a > 0.0f} is <code>true</code>)
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is lower than zero or equal to zero
	 */
	public static void assertPositive(float a) throws CheckerException {
		if (a <= 0.0f) {
			throw new CheckerNotPositiveException(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is positive.<br>
	 * the value is positive if it is greater zero (if {@code a > 0.d) is <code>true</code>)
	 * @param a
	 * the primitive value
	 * 
	 * @throws CheckerException
	 *             if the value is lower than zero or equal to zero
	 */
	public static void assertPositive(double a) throws CheckerException {
		if (a <= 0.0d) {
			throw new CheckerNotPositiveException(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is negative.<br>
	 * the value is negative if it is lower zero (if {@code a < 0} is <code>true</code>)
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is greater than zero or equal to zero
	 */
	public static void assertNegative(byte a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotNegativeException(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is negative.<br>
	 * the value is negative if it is lower zero (if {@code a < 0} is <code>true</code>)
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is greater than zero or equal to zero
	 */
	public static void assertNegative(short a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotNegativeException(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is negative.<br>
	 * the value is negative if it is lower zero (if {@code a < 0} is <code>true</code>)
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is greater than zero or equal to zero
	 */
	public static void assertNegative(int a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotNegativeException(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is negative.<br>
	 * the value is negative if it is lower zero (if {@code a < 0l} is <code>true</code>)
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is greater than zero or equal to zero
	 */
	public static void assertNegative(long a) throws CheckerException {
		if (a <= 0l) {
			throw new CheckerNotNegativeException(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is negative.<br>
	 * the value is negative if it is lower zero (if {@code a < 0.0f} is <code>true</code>)
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is greater than zero or equal to zero
	 */
	public static void assertNegative(float a) throws CheckerException {
		if (a <= 0.0f) {
			throw new CheckerNotNegativeException(a);
		}
	}
	
	/**
	 * asserts that the given primitive value is negative.<br>
	 * the value is negative if it is lower zero (if {@code a < 0.0d} is <code>true</code>)
	 * 
	 * @param a
	 *            the primitive value
	 * @throws CheckerException
	 *             if the value is greater than zero or equal to zero
	 */
	public static void assertNegative(double a) throws CheckerException {
		if (a <= 0.0d) {
			throw new CheckerNotNegativeException(a);
		}
	}
	
	
	/**
	 * asserts that the boolean value of the given {@link Boolean} is <code>true</code>.<br>
	 * the {@link Boolean} is <code>true</code> if it is not <code>null</code> and
	 * {@link Boolean#booleanValue()} returns <code>true</code>
	 * 
	 * @param b
	 *            the {@link Boolean} value
	 * @throws CheckerException
	 *             if the {@link Boolean} is <code>null</code> or if the boolean value is
	 *             <code>false</code>
	 */
	public static void assertTrue(Boolean b) throws CheckerException {
		if (b == null || !b) {
			throw new CheckerBoolException(b);
		}
	}
	
	/**
	 * asserts that the boolean value of the given {@link Boolean} is <code>false</code>.<br>
	 * the {@link Boolean} is <code>false</code> if it is not <code>null</code> and
	 * {@link Boolean#booleanValue()} returns <code>false</code>
	 * 
	 * @param b
	 *            the {@link Boolean} value
	 * @throws CheckerException
	 *             if the {@link Boolean} is <code>null</code> or if the boolean value is
	 *             <code>true</code>
	 */
	public static void assertFalse(Boolean b) throws CheckerException {
		if (b == null || b) {
			throw new CheckerBoolException(b);
		}
	}
	
	/**
	 * asserts that the primitive boolean value is <code>true</code>.<br>
	 * a primitive boolean value is <code>true</code> if {@code b == true} is <code>true</code>
	 * 
	 * @param b
	 *            the primitive boolean value
	 * @throws CheckerException
	 *             if the boolean value is <code>false</code>
	 */
	public static void assertTrue(boolean b) throws CheckerException {
		if ( !b) {
			throw new CheckerBoolException(b);
		}
	}
	
	/**
	 * asserts that the primitive boolean value is <code>false</code>.<br>
	 * a primitive boolean value is <code>false</code> if {@code b != true} is <code>true</code>
	 * 
	 * @param b
	 *            the primitive boolean value
	 * @throws CheckerException
	 *             if the boolean value is <code>true</code>
	 */
	public static void assertFalse(boolean b) throws CheckerException {
		if (b) {
			throw new CheckerBoolException(b);
		}
	}
	
	
	/**
	 * asserts that the given class is the exact class of the given object.<br>
	 * the class is the exact class of the object if the object and the class are not and if
	 * {@link Object#getClass()} returns the given class. ({@code cls == o.getClass()})
	 * 
	 * @param cls
	 *            the class
	 * @param o
	 *            the object
	 * @throws CheckerException
	 *             it the class is not the exact class of the object
	 */
	public static void assertExactClass(Class <?> cls, Object o) throws CheckerException {
		if (o == null || cls == null || cls != o.getClass()) {
			throw new CheckerNoInstanceException(o, cls);
		}
	}
	
	/**
	 * asserts that the given object is a instance of the given class.<br>
	 * the class is the exact class of the object if the object and the class are not and if
	 * {@link Class#isInstance(Object)} with the given object returns <code>true</code>
	 * 
	 * @param cls
	 *            the class
	 * @param o
	 *            the object
	 * @throws CheckerException
	 *             it the class is not the exact class of the object
	 */
	public static void assertInstanceOf(Class <?> cls, Object o) throws CheckerException {
		if (o == null || cls == null || !cls.isInstance(o)) {
			throw new CheckerNoInstanceException(cls, o);
		}
	}
	
	/**
	 * asserts that the first class is either the same class or a super class of the second class.<br>
	 * the first class is a the same or a super class if both are <code>null</code> and
	 * {@link Class#isAssignableFrom(Class)} with the second class as argument and first the class as
	 * caller returns <code>true</code>.
	 * 
	 * @param cls
	 *            the first class
	 * @param subClas
	 *            the second class
	 * @throws CheckerException
	 *             it the class is not the exact class of the object
	 */
	public static void assertsSubClass(Class <?> cls, Class <?> subClas) throws CheckerException {
		if (cls == null || subClas == null || !cls.isAssignableFrom(subClas)) {
			throw new CheckerNoInstanceException(cls, subClas);
		}
	}
	
	
	/**
	 * assert that the given throwing runnable throws any {@link Throwable} with a message wich is equal
	 * to the given message.<br>
	 * the message is equal to the given message if both are <code>null</code> or
	 * {@link String#equals(Object)} returns <code>true</code>.
	 * 
	 * @param msg
	 *            the expected message of the throwable
	 * @param r
	 *            the throwing runnable
	 * @throws CheckerException
	 *             if no throwable is thrown or if the message does not fit
	 */
	public static void assertThrowsAny(String msg, ThrowingRunnable <?> r) throws CheckerException {
		CheckerNotThrownException thrown = null;
		try {
			r.run();
			thrown = new CheckerNotThrownException();
			throw thrown;
		} catch (Throwable err) {
			if (thrown == err) {
				throw thrown;// Do not catch self thrown exception (CheckerNotThrownException)
			} else if ( !Objects.equals(msg, err.getMessage())) {
				throw new CheckerNotThrownException(msg, err.getMessage(), err.getClass());
			}
		}
	}
	
	/**
	 * assert that the given throwing runnable throws a {@link Throwable} wich
	 * {@link Class#isInstance(Object)} of the given class and has a message wich is equal
	 * to the given message.<br>
	 * the message is equal to the given message if both are <code>null</code> or
	 * {@link String#equals(Object)} returns <code>true</code>.
	 * 
	 * @param msg
	 *            the expected message of the throwable
	 * @param assertThrown
	 *            the class wich is expected to bee thrown.
	 * @param r
	 *            the throwing runnable
	 * @throws CheckerException
	 *             if no throwable is thrown, a throwable from the wrong class is thrown or if the
	 *             message does not fit
	 */
	public static void assertThrows(String msg, Class <? extends Throwable> assertThrown, ThrowingRunnable <?> r) throws CheckerException {
		CheckerNotThrownException thrown = null;
		try {
			r.run();
			thrown = new CheckerNotThrownException(assertThrown);
			throw thrown;
		} catch (Throwable err) {
			if (thrown == err) {
				throw thrown;// Do not catch self thrown exception (CheckerNotThrownException)
			} else if ( !assertThrown.isInstance(err)) {
				throw new CheckerNotThrownException(msg, assertThrown, err.getMessage(), err.getClass());
			} else if ( !Objects.equals(msg, err.getMessage())) {
				throw new CheckerNotThrownException(msg, assertThrown, err.getMessage(), err.getClass());
			}
		}
	}
	
	/**
	 * assert that the given throwing runnable throws a {@link Throwable} wich
	 * is from the correct class and has a message wich is equal
	 * to the given message.
	 * <p>
	 * the message is equal to the given message if both are <code>null</code> or
	 * {@link String#equals(Object)} returns <code>true</code>.<br>
	 * if {@code exactClass} is <code>true</code> the throwable has only the correct class if
	 * <code>({@link Object#getClass()} == assertThrown)</code> is <code>true</code>.<br>
	 * if {@code exactClass} is <code>false</code> the throwable has the correct class if
	 * it {@link Class#isInstance(Object)} of {@code assertThrown}.
	 * 
	 * @param msg
	 *            the expected message of the throwable
	 * @param exactClass
	 *            if also an error should be thrown if the throwable has not the exact class as the
	 *            given class
	 * @param assertThrown
	 *            the class wich is expected to bee thrown.
	 * @param r
	 *            the throwing runnable
	 * @throws CheckerException
	 *             if no throwable is thrown, a throwable from the wrong class is thrown or if the
	 *             message does not fit
	 */
	public static void assertThrows(String msg, boolean exactClass, Class <? extends Throwable> assertThrown, ThrowingRunnable <?> r) throws CheckerException {
		CheckerNotThrownException thrown = null;
		try {
			r.run();
			thrown = new CheckerNotThrownException(assertThrown);
			throw thrown;
		} catch (Throwable err) {
			if (thrown == err) {
				throw thrown;// Do not catch self thrown exception (CheckerNotThrownException)
			}
			Class <? extends Throwable> cls = err.getClass();
			if (exactClass) {
				if (assertThrown != cls) {
					throw new CheckerNotThrownException(msg, assertThrown, err.getMessage(), cls, true);
				}
			} else if ( !assertThrown.isInstance(err)) {
				throw new CheckerNotThrownException(msg, assertThrown, err.getMessage(), cls, false);
			}
			if ( !Objects.equals(msg, err.getMessage())) {
				throw new CheckerNotThrownException(msg, assertThrown, err.getMessage(), cls);
			}
		}
	}
	
	/**
	 * assert that the given throwing runnable throws any {@link Throwable}.<br>
	 * 
	 * @param msg
	 *            the expected message of the throwable
	 * @param r
	 *            the throwing runnable
	 * @throws CheckerException
	 *             if no throwable is thrown
	 */
	public static void assertThrowsAny(ThrowingRunnable <?> r) throws CheckerException {
		CheckerNotThrownException thrown = null;
		try {
			r.run();
			thrown = new CheckerNotThrownException();
			throw thrown;
		} catch (Throwable err) {
			if (thrown == err) {
				throw thrown;// Do not catch self thrown exception (CheckerNotThrownException)
			}
		}
	}
	
	/**
	 * assert that the given throwing runnable throws a {@link Throwable} wich
	 * {@link Class#isInstance(Object)} of the given class.
	 * 
	 * @param assertThrown
	 *            the class wich is expected to bee thrown.
	 * @param r
	 *            the throwing runnable
	 * @throws CheckerException
	 *             if no throwable is thrown, a throwable from the wrong class is thrown or if the
	 *             message does not fit
	 */
	public static void assertThrows(Class <? extends Throwable> assertThrown, ThrowingRunnable <?> r) throws CheckerException {
		CheckerNotThrownException thrown = null;
		try {
			r.run();
			thrown = new CheckerNotThrownException(assertThrown);
			throw thrown;
		} catch (Throwable err) {
			if (thrown == err) throw thrown;// Do not catch self thrown exception (CheckerNotThrownException)
			Class <? extends Throwable> cls = err.getClass();
			if (cls != assertThrown) {
				if ( !assertThrown.isAssignableFrom(cls)) {
					throw new CheckerNotThrownException(assertThrown, cls);
				}
			}
		}
	}
	
	/**
	 * assert that the given throwing runnable throws a {@link Throwable} wich has a correct class.
	 * <p>
	 * the class is correct if {@code exactClass} is <code>true</code> and the throwable has only the
	 * correct class if <code>({@link Object#getClass()} == assertThrown)</code> is
	 * <code>true</code>.<br>
	 * the class is also correct if {@code exactClass} is <code>false</code> and the throwable has the
	 * correct class if it {@link Class#isInstance(Object)} of {@code assertThrown}.
	 * 
	 * @param exactClass
	 *            if also an error should be thrown if the throwable has not the exact class as the
	 *            given class
	 * @param assertThrown
	 *            the class wich is expected to bee thrown.
	 * @param r
	 *            the throwing runnable
	 * @throws CheckerException
	 *             if no throwable is thrown, a throwable from the wrong class is thrown or if the
	 *             message does not fit
	 */
	public static void assertThrows(boolean exactClass, Class <? extends Throwable> assertThrown, ThrowingRunnable <?> r) throws CheckerException {
		CheckerNotThrownException thrown = null;
		try {
			r.run();
			thrown = new CheckerNotThrownException(assertThrown);
			throw thrown;
		} catch (Throwable err) {
			if (thrown == err) throw thrown;// Do not catch self thrown exception (CheckerNotThrownException)
			Class <? extends Throwable> cls = err.getClass();
			if (cls != assertThrown) {
				if (exactClass) {
					throw new CheckerNotThrownException(assertThrown, cls, true);
				}
				if ( !assertThrown.isAssignableFrom(cls)) {
					throw new CheckerNotThrownException(assertThrown, cls, false);
				}
			}
		}
	}
	
	
	
	/**
	 * throws a {@link CheckerFailException} with the given message.
	 * 
	 * @param msg
	 *            the message of the {@link CheckerFailException} which will be thrown
	 * @throws CheckerFailException
	 *             always
	 */
	public static void fail(String msg) throws CheckerFailException {
		throw new CheckerFailException(msg);
	}
	
	/**
	 * throws a {@link CheckerFailException}.
	 * 
	 * @throws CheckerFailException
	 *             always
	 */
	public static void fail() throws CheckerFailException {
		throw new CheckerFailException("fail");
	}
	
	/**
	 * throws a {@link CheckerFailException} with the given {@link Throwable} as cause
	 * 
	 * @param cause
	 *            the cause of the {@link CheckerFailException} which will be thrown
	 * @throws CheckerFailException
	 *             always
	 */
	public static void fail(Throwable cause) throws CheckerFailException {
		throw new CheckerFailException(cause);
	}
	
	/**
	 * throws a {@link CheckerFailException} with the given {@link Throwable} as cause and the given
	 * {@link String} as detailed message
	 * 
	 * @param msg
	 *            the message of the {@link CheckerFailException} which will be thrown
	 * @param cause
	 *            the cause of the {@link CheckerFailException} which will be thrown
	 * @throws CheckerFailException
	 *             always
	 */
	public static void fail(String msg, Throwable cause) throws CheckerFailException {
		throw new CheckerFailException(msg, cause);
	}
	
	
	
	/**
	 * casts the given object the type.<br>
	 * this operation will fail if either the given object is <code>null</code> or if it is no
	 * {@link Class#isInstance(Object)} of the given class.
	 * 
	 * @param <T>
	 *            the type represented by the given class
	 * @param a
	 *            the object to cast
	 * @param type
	 *            the type
	 * @param msg
	 *            the message
	 * @return the given object
	 * @throws CheckerNullExeption
	 *             if the given object is <code>null</code>
	 * @throws CheckerNoInstanceException
	 *             if the given object is no instance of the given class
	 */
	@SuppressWarnings("unchecked")
	public static <T> T castNotNull(Object a, Class <T> type, String msg) throws CheckerNullExeption, CheckerNoInstanceException {
		if (a == null) {
			throw new CheckerNullExeption(msg, type);
		}
		if ( !type.isInstance(a)) {
			throw new CheckerNoInstanceException(msg, type, a);
		}
		return (T) a;
	}
	
	/**
	 * casts the given object the type.<br>
	 * this operation will fail if either the given object is <code>null</code> or if it is no
	 * {@link Class#isInstance(Object)} of the given class.
	 * 
	 * @param <T>
	 *            the type represented by the given class
	 * @param a
	 *            the object to cast
	 * @param type
	 *            the type
	 * @param msg
	 *            the message
	 * @return the given object
	 * @throws CheckerNullExeption
	 *             if the given object is <code>null</code>
	 * @throws CheckerNoInstanceException
	 *             if the given object is no instance of the given class
	 */
	@SuppressWarnings("unchecked")
	public static <T> T castNotNull(Object a, Class <T> type, Supplier <String> msg) throws CheckerNullExeption, CheckerNoInstanceException {
		if (a == null) {
			throw new CheckerNullExeption(msg.get(), type);
		}
		if ( !type.isInstance(a)) {
			throw new CheckerNoInstanceException(msg.get(), type, a);
		}
		return (T) a;
	}
	
	/**
	 * casts the given object the type.<br>
	 * this operation will fail if either the given object is <code>null</code> or if it is no
	 * {@link Class#isInstance(Object)} of the given class.
	 * 
	 * @param <T>
	 *            the type represented by the given class
	 * @param a
	 *            the object to cast
	 * @param type
	 *            the type
	 * @return the given object
	 * @throws CheckerNullExeption
	 *             if the given object is <code>null</code>
	 * @throws CheckerNoInstanceException
	 *             if the given object is no instance of the given class
	 */
	@SuppressWarnings("unchecked")
	public static <T> T castNotNull(Object a, Class <T> type) throws CheckerNullExeption, CheckerNoInstanceException {
		if (a == null) {
			throw new CheckerNullExeption(type);
		}
		if ( !type.isInstance(a)) {
			throw new CheckerNoInstanceException(type, a);
		}
		return (T) a;
	}
	
}

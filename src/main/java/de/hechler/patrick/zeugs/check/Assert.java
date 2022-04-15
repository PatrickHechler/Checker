package de.hechler.patrick.zeugs.check;

import java.util.Arrays;
import java.util.Objects;

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

public class Assert {
	
	public Assert() {}
	
	public static void assertDeepNotArrayEquals(Object[] a, Object[] b) throws CheckerException {
		if (Arrays.deepEquals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	public static void assertNotArrayEquals(Object[] a, Object[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
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
	
	public static void assertNotArrayEquals(boolean[] a, boolean[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	public static void assertNotArrayEquals(char[] a, char[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	public static void assertNotArrayEquals(long[] a, long[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	public static void assertNotArrayEquals(int[] a, int[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	public static void assertNotArrayEquals(short[] a, short[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	public static void assertNotArrayEquals(byte[] a, byte[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	public static void assertNotArrayEquals(float[] a, float[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	public static void assertNotArrayEquals(double[] a, double[] b) throws CheckerException {
		if (Arrays.equals(a, b)) {
			throw new CheckerArraysEqualsExeption(a, b);
		}
	}
	
	
	public static void assertArrayEquals(Object[] a, Object[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	public static void assertDeepArrayEquals(Object[] a, Object[] b) throws CheckerException {
		if ( !Arrays.deepEquals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
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
	
	
	public static void assertArrayEquals(boolean[] a, boolean[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	public static void assertArrayEquals(char[] a, char[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	public static void assertArrayEquals(long[] a, long[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	public static void assertArrayEquals(int[] a, int[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	public static void assertArrayEquals(short[] a, short[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	public static void assertArrayEquals(byte[] a, byte[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	public static void assertArrayEquals(float[] a, float[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	public static void assertArrayEquals(double[] a, double[] b) throws CheckerException {
		if ( !Arrays.equals(a, b)) {
			throw new CheckerArraysNotEqualsExeption(a, b);
		}
	}
	
	
	public static void assertSame(Object a, Object b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, Object b) throws CheckerException {
		if ( !Objects.equals(a, b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertDeepEquals(Object a, Object b) throws CheckerException {
		if ( !Objects.deepEquals(a, b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, boolean b) throws CheckerException {
		if (a == null || !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, char b) throws CheckerException {
		if (a == null || !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, long b) throws CheckerException {
		if (a == null || !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, int b) throws CheckerException {
		if (a == null || !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, short b) throws CheckerException {
		if (a == null || !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, byte b) throws CheckerException {
		if (a == null || !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, float b) throws CheckerException {
		if (a == null || !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, double b) throws CheckerException {
		if (a == null || !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(boolean a, Object b) throws CheckerException {
		if (b == null || !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(char a, Object b) throws CheckerException {
		if (b == null || !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(long a, Object b) throws CheckerException {
		if (b == null || !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(int a, Object b) throws CheckerException {
		if (b == null || !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(short a, Object b) throws CheckerException {
		if (b == null || !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(byte a, Object b) throws CheckerException {
		if (b == null || !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(float a, Object b) throws CheckerException {
		if (b == null || !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(double a, Object b) throws CheckerException {
		if (b == null || !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(boolean a, boolean b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(char a, char b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(long a, long b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(int a, int b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(short a, short b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(byte a, byte b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(float a, float b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(double a, double b) throws CheckerException {
		if (a != b) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	
	public static void assertNotSame(Object a, Object b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(Object a, Object b) throws CheckerException {
		if (Objects.equals(a, b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertDeepNotEquals(Object a, Object b) throws CheckerException {
		if (Objects.deepEquals(a, b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(Object a, boolean b) throws CheckerException {
		if (a.equals(b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(Object a, char b) throws CheckerException {
		if (a.equals(b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(Object a, long b) throws CheckerException {
		if (a.equals(b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(Object a, int b) throws CheckerException {
		if (a.equals(b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(Object a, short b) throws CheckerException {
		if (a.equals(b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(Object a, byte b) throws CheckerException {
		if (a.equals(b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(Object a, float b) throws CheckerException {
		if (a.equals(b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(Object a, double b) throws CheckerException {
		if (a.equals(b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(boolean a, Object b) throws CheckerException {
		if (b.equals(a)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(char a, Object b) throws CheckerException {
		if (b.equals(a)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(long a, Object b) throws CheckerException {
		if (b.equals(a)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(int a, Object b) throws CheckerException {
		if (b.equals(a)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(short a, Object b) throws CheckerException {
		if (b.equals(a)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(byte a, Object b) throws CheckerException {
		if (b.equals(a)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(float a, Object b) throws CheckerException {
		if (b.equals(a)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(double a, Object b) throws CheckerException {
		if (b.equals(a)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(boolean a, boolean b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(char a, char b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(long a, long b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(int a, int b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(short a, short b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(byte a, byte b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(float a, float b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(double a, double b) throws CheckerException {
		if (a == b) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	
	public static void assertLowerEqual(byte a, byte b) throws CheckerException {
		if (a > b) {
			throw new CheckerNotLowerEqualException(a, b);
		}
	}
	
	public static void assertLowerEqual(short a, short b) throws CheckerException {
		if (a > b) {
			throw new CheckerNotLowerEqualException(a, b);
		}
	}
	
	public static void assertLowerEqual(int a, int b) throws CheckerException {
		if (a > b) {
			throw new CheckerNotLowerEqualException(a, b);
		}
	}
	
	public static void assertLowerEqual(long a, long b) throws CheckerException {
		if (a > b) {
			throw new CheckerNotLowerEqualException(a, b);
		}
	}
	
	public static void assertLowerEqual(float a, float b) throws CheckerException {
		if (a > b) {
			throw new CheckerNotLowerEqualException(a, b);
		}
	}
	
	public static void assertLowerEqual(double a, double b) throws CheckerException {
		if (a > b) {
			throw new CheckerNotLowerEqualException(a, b);
		}
	}
	
	
	public static void assertLower(byte a, byte b) throws CheckerException {
		if (a >= b) {
			throw new CheckerNotLowerException(a, b);
		}
	}
	
	public static void assertLower(short a, short b) throws CheckerException {
		if (a >= b) {
			throw new CheckerNotLowerException(a, b);
		}
	}
	
	public static void assertLower(int a, int b) throws CheckerException {
		if (a >= b) {
			throw new CheckerNotLowerException(a, b);
		}
	}
	
	public static void assertLower(long a, long b) throws CheckerException {
		if (a >= b) {
			throw new CheckerNotLowerException(a, b);
		}
	}
	
	public static void assertLower(float a, float b) throws CheckerException {
		if (a >= b) {
			throw new CheckerNotLowerException(a, b);
		}
	}
	
	public static void assertLower(double a, double b) throws CheckerException {
		if (a >= b) {
			throw new CheckerNotLowerException(a, b);
		}
	}
	
	
	public static void assertGreatherEqual(byte a, byte b) throws CheckerException {
		if (a < b) {
			throw new CheckerNotGreatherEqualExeption(a, b);
		}
	}
	
	public static void assertGreatherEqual(short a, short b) throws CheckerException {
		if (a < b) {
			throw new CheckerNotGreatherEqualExeption(a, b);
		}
	}
	
	public static void assertGreatherEqual(int a, int b) throws CheckerException {
		if (a < b) {
			throw new CheckerNotGreatherEqualExeption(a, b);
		}
	}
	
	public static void assertGreatherEqual(long a, long b) throws CheckerException {
		if (a < b) {
			throw new CheckerNotGreatherEqualExeption(a, b);
		}
	}
	
	public static void assertGreatherEqual(float a, float b) throws CheckerException {
		if (a < b) {
			throw new CheckerNotGreatherEqualExeption(a, b);
		}
	}
	
	public static void assertGreatherEqual(double a, double b) throws CheckerException {
		if (a < b) {
			throw new CheckerNotGreatherEqualExeption(a, b);
		}
	}
	
	
	public static void assertGreather(byte a, byte b) throws CheckerException {
		if (a <= b) {
			throw new CheckerNotGreatherExeption(a, b);
		}
	}
	
	public static void assertGreather(short a, short b) throws CheckerException {
		if (a <= b) {
			throw new CheckerNotGreatherExeption(a, b);
		}
	}
	
	public static void assertGreather(int a, int b) throws CheckerException {
		if (a <= b) {
			throw new CheckerNotGreatherExeption(a, b);
		}
	}
	
	public static void assertGreather(long a, long b) throws CheckerException {
		if (a <= b) {
			throw new CheckerNotGreatherExeption(a, b);
		}
	}
	
	public static void assertGreather(float a, float b) throws CheckerException {
		if (a <= b) {
			throw new CheckerNotGreatherExeption(a, b);
		}
	}
	
	public static void assertGreather(double a, double b) throws CheckerException {
		if (a <= b) {
			throw new CheckerNotGreatherExeption(a, b);
		}
	}
	
	
	public static void assertNull(Object a) throws CheckerException {
		if (a != null) {
			throw new CheckerNotNullExeption(a);
		}
	}
	
	public static void assertNull(byte a) throws CheckerException {
		if (a != 0) {
			throw new CheckerNotNullExeption(a);
		}
	}
	
	public static void assertNull(short a) throws CheckerException {
		if (a != 0) {
			throw new CheckerNotNullExeption(a);
		}
	}
	
	public static void assertNull(int a) throws CheckerException {
		if (a != 0) {
			throw new CheckerNotNullExeption(a);
		}
	}
	
	public static void assertNull(long a) throws CheckerException {
		if (a != 0) {
			throw new CheckerNotNullExeption(a);
		}
	}
	
	public static void assertNull(float a) throws CheckerException {
		if (a != 0) {
			throw new CheckerNotNullExeption(a);
		}
	}
	
	public static void assertNull(double a) throws CheckerException {
		if (a != 0) {
			throw new CheckerNotNullExeption(a);
		}
	}
	
	
	public static <T> T castNotNull(Object a, Class <T> type, String msg) throws CheckerException {
		if (a == null) {
			throw new CheckerNullExeption(msg, type);
		}
		return type.cast(a);
	}
	
	public static <T> T castNotNull(Object a, Class <T> type) throws CheckerException {
		if (a == null) {
			throw new CheckerNullExeption(type);
		}
		return type.cast(a);
	}
	
	public static void assertNotNull(Object a, String msg) throws CheckerException {
		if (a == null) {
			throw new CheckerNullExeption(msg, Object.class);
		}
	}
	
	public static void assertNotNull(Object a) throws CheckerException {
		if (a == null) {
			throw new CheckerNullExeption(Object.class);
		}
	}
	
	public static void assertNotNull(byte a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption(Byte.TYPE);
		}
	}
	
	public static void assertNotNull(short a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption(Short.TYPE);
		}
	}
	
	public static void assertNotNull(int a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption(Integer.TYPE);
		}
	}
	
	public static void assertNotNull(long a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption(Long.TYPE);
		}
	}
	
	public static void assertNotNull(float a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption(Float.TYPE);
		}
	}
	
	public static void assertNotNull(double a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption(Double.TYPE);
		}
	}
	
	public static void assertPositive(byte a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotPositiveException(a);
		}
	}
	
	public static void assertPositive(short a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotPositiveException(a);
		}
	}
	
	public static void assertPositive(int a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotPositiveException(a);
		}
	}
	
	public static void assertPositive(long a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotPositiveException(a);
		}
	}
	
	public static void assertPositive(float a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotPositiveException(a);
		}
	}
	
	public static void assertPositive(double a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotPositiveException(a);
		}
	}
	
	public static void assertNegative(byte a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotNegativeException(a);
		}
	}
	
	public static void assertNegative(short a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotNegativeException(a);
		}
	}
	
	public static void assertNegative(int a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotNegativeException(a);
		}
	}
	
	public static void assertNegative(long a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotNegativeException(a);
		}
	}
	
	public static void assertNegative(float a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotNegativeException(a);
		}
	}
	
	public static void assertNegative(double a) throws CheckerException {
		if (a <= 0) {
			throw new CheckerNotNegativeException(a);
		}
	}
	
	
	public static void assertTrue(Boolean b) throws CheckerException {
		if (b != true) throw new CheckerBoolException(b);
	}
	
	public static void assertFalse(Boolean b) throws CheckerException {
		if (b != false) throw new CheckerBoolException(b);
	}
	
	public static void assertTrue(boolean b) throws CheckerException {
		if ( !b) throw new CheckerBoolException(b);
	}
	
	public static void assertFalse(boolean b) throws CheckerException {
		if (b) throw new CheckerBoolException(b);
	}
	
	
	public static void assertExactClass(Class <?> cls, Object o) throws CheckerException {
		if (cls != o.getClass()) throw new CheckerNoInstanceException(cls, o, null);
	}
	
	public static void assertInstanceOf(Class <?> cls, Object o) throws CheckerException {
		if ( !cls.isInstance(o)) throw new CheckerNoInstanceException(cls, o);
	}
	
	public static void assertsSubClass(Class <?> cls, Class <?> subClas) throws CheckerException {
		if ( !cls.isAssignableFrom(subClas)) throw new CheckerNoInstanceException(cls, subClas);
	}
	
	
	
	public static void assertThrowsAny(String msg, ThrowingRunnable <?> r) throws CheckerException {
		CheckerNotThrownException thrown = null;
		try {
			r.run();
			thrown = new CheckerNotThrownException();
			throw thrown;
		} catch (Throwable err) {
			if (thrown == err) throw thrown;// Do not catch self thrown exception (CheckerNotThrownException)
			else if ( !Objects.equals(msg, err.getMessage())) throw new CheckerNotThrownException(msg, err.getMessage(), err.getClass());
			else if (err instanceof ThreadDeath) throw (ThreadDeath) err;
		}
	}
	
	public static <T extends Throwable> void assertThrows(String msg, Class <T> assertThrown, ThrowingRunnable <?> r) throws CheckerException {
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
					throw new CheckerNotThrownException(msg, assertThrown, err.getMessage(), cls);
				}
			}
			if ( !Objects.equals(msg, err.getMessage())) {
				throw new CheckerNotThrownException(msg, assertThrown, err.getMessage(), cls);
			}
		}
	}
	
	public static <T extends Throwable> void assertThrows(String msg, boolean exactClass, Class <T> assertThrown, ThrowingRunnable <?> r) throws CheckerException {
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
					throw new CheckerNotThrownException(msg, assertThrown, err.getMessage(), cls, true);
				}
				if ( !assertThrown.isAssignableFrom(cls)) {
					throw new CheckerNotThrownException(msg, assertThrown, err.getMessage(), cls, false);
				}
			}
			if ( !Objects.equals(msg, err.getMessage())) {
				throw new CheckerNotThrownException(msg, assertThrown, err.getMessage(), cls);
			}
		}
	}
	
	public static void assertThrowsAny(ThrowingRunnable <?> r) throws CheckerException {
		CheckerNotThrownException thrown = null;
		try {
			r.run();
			thrown = new CheckerNotThrownException();
			throw thrown;
		} catch (Throwable err) {
			if (thrown == err) throw thrown;
			else if (err instanceof ThreadDeath) throw (ThreadDeath) err;
		}
	}
	
	public static <T extends Throwable> void assertThrows(Class <T> assertThrown, ThrowingRunnable <?> r) throws CheckerException {
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
	
	public static <T extends Throwable> void assertThrows(boolean exactClass, Class <T> assertThrown, ThrowingRunnable <?> r) throws CheckerException {
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
	
	
	
	public static void fail(String msg) throws CheckerFailException {
		throw new CheckerFailException(msg);
	}
	
	public static void fail() throws CheckerFailException {
		throw new CheckerFailException("fail");
	}
	
	public static void fail(Throwable cause) throws CheckerFailException {
		throw new CheckerFailException(cause);
	}
	
	public static void fail(String msg, Throwable cause) throws CheckerFailException {
		throw new CheckerFailException(msg, cause);
	}
	
}

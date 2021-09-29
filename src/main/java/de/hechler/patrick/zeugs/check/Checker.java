package de.hechler.patrick.zeugs.check;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;
import de.hechler.patrick.zeugs.check.anotations.End;
import de.hechler.patrick.zeugs.check.anotations.ParamCreater;
import de.hechler.patrick.zeugs.check.anotations.Start;
import de.hechler.patrick.zeugs.check.exceptions.CheckerArraysEqualsExeption;
import de.hechler.patrick.zeugs.check.exceptions.CheckerArraysNotEqualsExeption;
import de.hechler.patrick.zeugs.check.exceptions.CheckerBoolException;
import de.hechler.patrick.zeugs.check.exceptions.CheckerEqualsExeption;
import de.hechler.patrick.zeugs.check.exceptions.CheckerException;
import de.hechler.patrick.zeugs.check.exceptions.CheckerFailException;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNoInstanceException;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNotEqualsExeption;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNotNullExeption;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNotThrownException;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNullExeption;
import de.hechler.patrick.zeugs.check.interfaces.ThrowingRunnable;

public class Checker implements Runnable {
	
	static {
		Checker.class.getClassLoader().setDefaultAssertionStatus(true);
	}
	
	private final Object instance;
	
	private List <Method> init = null;
	private List <Method> start = null;
	private List <Method> end = null;
	private List <Method> finalize = null;
	private List <Method> check = null;
	
	private CheckResult result;
	
	
	
	/**
	 * creates a new {@link Checker}.<br>
	 * this {@link Checker} will use itself to call methods: {@link Method#invoke(Object, Object...) method.invoke(this, params);}
	 */
	public Checker() {
		instance = this;
	}
	
	/**
	 * creates a {@link Checker} with the specified <code>instance</code>.<br>
	 * This {@code instance} will be used to call methods.<br>
	 * 
	 * @param instance
	 *            the {@code instance} used by this {@link Checker}
	 * 			
	 * @see {@link Method#invoke(Object, Object...) method.invoke(instance, params);}
	 */
	public Checker(Object instance) {
		this.instance = instance;
	}
	
	
	
	public static void assertNotArrayEquals(Object[] a, Object[] b) throws CheckerException {
		if (Arrays.deepEquals(a, b)) {
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
	
	public static void assertSimpleEquals(Object a, Object b) throws CheckerException {
		if ( !Objects.equals(a, b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, Object b) throws CheckerException {
		if ( !Objects.deepEquals(a, b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, boolean b) throws CheckerException {
		if ( !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, char b) throws CheckerException {
		if ( !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, long b) throws CheckerException {
		if ( !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, int b) throws CheckerException {
		if ( !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, short b) throws CheckerException {
		if ( !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, byte b) throws CheckerException {
		if ( !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, float b) throws CheckerException {
		if ( !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(Object a, double b) throws CheckerException {
		if ( !a.equals(b)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(boolean a, Object b) throws CheckerException {
		if ( !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(char a, Object b) throws CheckerException {
		if ( !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(long a, Object b) throws CheckerException {
		if ( !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(int a, Object b) throws CheckerException {
		if ( !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(short a, Object b) throws CheckerException {
		if ( !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(byte a, Object b) throws CheckerException {
		if ( !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(float a, Object b) throws CheckerException {
		if ( !b.equals(a)) {
			throw new CheckerNotEqualsExeption(a, b);
		}
	}
	
	public static void assertEquals(double a, Object b) throws CheckerException {
		if ( !b.equals(a)) {
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
	
	public static void assertSimpleNotEquals(Object a, Object b) throws CheckerException {
		if ( !Objects.equals(a, b)) {
			throw new CheckerEqualsExeption(a, b);
		}
	}
	
	public static void assertNotEquals(Object a, Object b) throws CheckerException {
		if ( !Objects.deepEquals(a, b)) {
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
	
	
	public static void assertNull(Object a) throws CheckerException {
		if (a != null) {
			throw new CheckerNotNullExeption(a);
		}
	}
	
	public static void assertNull(byte a) throws CheckerException {
		if (a != 0) {
			throw new CheckerNotNullExeption(a, null);
		}
	}
	
	public static void assertNull(short a) throws CheckerException {
		if (a != 0) {
			throw new CheckerNotNullExeption(a, null);
		}
	}
	
	public static void assertNull(int a) throws CheckerException {
		if (a != 0) {
			throw new CheckerNotNullExeption(a, null);
		}
	}
	
	public static void assertNull(long a) throws CheckerException {
		if (a != 0) {
			throw new CheckerNotNullExeption(a, null);
		}
	}
	
	public static void assertNull(float a) throws CheckerException {
		if (a != 0) {
			throw new CheckerNotNullExeption(a, null);
		}
	}
	
	public static void assertNull(double a) throws CheckerException {
		if (a != 0) {
			throw new CheckerNotNullExeption(a, null);
		}
	}
	
	
	public static void assertNotNull(Object a) throws CheckerException {
		if (a == null) {
			throw new CheckerNullExeption();
		}
	}
	
	public static void assertNotNull(byte a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption("byte");
		}
	}
	
	public static void assertNotNull(short a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption("short");
		}
	}
	
	public static void assertNotNull(int a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption("int");
		}
	}
	
	public static void assertNotNull(long a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption("long");
		}
	}
	
	public static void assertNotNull(float a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption("float");
		}
	}
	
	public static void assertNotNull(double a) throws CheckerException {
		if (a == 0) {
			throw new CheckerNullExeption("double");
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
		} catch (Throwable ignore) {
			if (thrown == ignore) throw thrown;// Do not catch self thrown exception (CheckerNotThrownException)
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
	
	
	
	public boolean checkedAlready() {
		return result == null;
	}
	
	public CheckResult result() {
		if (result == null) {
			run();
		}
		return result;
	}
	
	@Override
	public final void run() {
		if (this.init == null) {
			load(instance.getClass());
		}
		this.result = new CheckResult();
		this.init.forEach(m -> run(m, instance));
		this.check.forEach(m -> {
			this.start.forEach(r -> run(r, instance));
			Result res = run(m, instance);
			this.result.set(m, res);
			this.end.forEach(r -> run(r, instance));
		});
		this.finalize.forEach(m -> run(m, instance));
		this.result.setEnd(System.currentTimeMillis());
	}
	
	
	private static Result run(final Method m, Object invoker) {
		Result retVal;
		Parameter[] params = m.getParameters();
		Object[] ps = new Object[params.length];
		for (int i = 0; i < params.length; i ++ ) {
			ParamCreater pc = params[i].getAnnotation(ParamCreater.class);
			Class <?> type = params[i].getType();
			if (pc != null) {
				String[] method = pc.method();
				Class <?>[] classes = new Class[method.length - 1];
				for (int ii = 0; ii < classes.length; ii ++ ) {
					try {
						classes[ii] = Class.forName(method[ii + 1]);
					} catch (ClassNotFoundException e) {
						throw new InternalError("could not find class of Parameter ('" + method[ii + 1] + "'): " + e.getMessage(), e);
					}
				}
				Method met;
				try {
					met = invoker.getClass().getDeclaredMethod(method[0], classes);
				} catch (NoSuchMethodException | SecurityException e) {
					throw new InternalError("could not get Method ('" + method[0] + "(" + Arrays.deepToString(method) + ")'): " + e.getMessage(), e);
				}
				boolean flag = met.isAccessible();
				met.setAccessible(true);
				Result r = run(met, invoker);
				if (r.badResult()) {
					throw new InternalError("could not get Parameter: " + r.getErr().getMessage(), r.getErr());
				}
				met.setAccessible(flag);
				ps[i] = r.getResult();
			} else if (type.isPrimitive()) {
				if (type == Boolean.TYPE) ps[i] = Boolean.FALSE;
				else if (type == Integer.TYPE) ps[i] = 0;
				else if (type == Double.TYPE) ps[i] = 0.0d;
				else if (type == Character.TYPE) ps[i] = '\0';
				else if (type == Byte.TYPE) ps[i] = (Byte) (byte) 0;
				else if (type == Long.TYPE) ps[i] = 0l;
				else if (type == Float.TYPE) ps[i] = 0.0f;
				else if (type == Short.TYPE) ps[i] = (Short) (short) 0;
				else throw new InternalError("unknown primitiv param: '" + type + '\'');
			} else if (params[i].isVarArgs()) ps[i] = Array.newInstance(type.getComponentType(), 0);
			else ps[i] = null;
		}
		try {
			boolean flag = m.isAccessible();
			m.setAccessible(true);
			Object res = m.invoke(invoker, ps);
			m.setAccessible(flag);
			retVal = new Result(res);
		} catch (IllegalAccessException e) {
			throw new AssertionError("can't acces method: " + m.getName(), e);
		} catch (IllegalArgumentException e) {
			throw new AssertionError("can't check method: '" + m.getName() + "' params: " + m.getParameterCount() + " : " + Arrays.deepToString(m.getParameterTypes())
					+ "   ||| my params: {" + Arrays.deepToString(ps) + '}', e);
		} catch (InvocationTargetException e) {
			Throwable err = e.getCause();
			retVal = new Result(err);
		}
		return retVal;
	}
	
	private void load(Class <?> clas) {
		try {
			clas.getClassLoader().setClassAssertionStatus(clas.getCanonicalName(), true);
		} catch (Throwable e) {// sollte eigentlich nicht passieren
			e.printStackTrace();
		}
		Method[] methods = clas.getDeclaredMethods();
		this.init = new ArrayList <>();
		this.start = new ArrayList <>();
		this.end = new ArrayList <>();
		this.finalize = new ArrayList <>();
		this.check = new ArrayList <>();
		for (Method m : methods) {
			Start s = m.getAnnotation(Start.class);
			if (s != null && !s.disabled()) {
				if (s.onlyOnce()) {
					this.init.add(m);
				} else {
					this.start.add(m);
				}
			}
			End e = m.getAnnotation(End.class);
			if (e != null && !e.disabled()) {
				if (e.onlyOnce()) {
					this.finalize.add(m);
				} else {
					this.end.add(m);
				}
			}
			Check c = m.getAnnotation(Check.class);
			if (c != null && !c.disabled()) {
				if ( !c.disabled()) {
					this.check.add(m);
				}
			}
		}
	}
	
	/**
	 * this will generate a {@link Checker} of {@code clas}, even if {@code clas} does not {@code extend} {@link Checker}<br>
	 * 
	 * the generated {@link Checker} will be used to return the {@link CheckResult}.
	 * 
	 * @param clas
	 *            the {@link Class} to be checked
	 * @return the result of the created {@link Checker}
	 * @implNote it behaves like <code>{@link #generateChecker(Class)}.{@link #result()}</code>
	 */
	public static CheckResult check(final Class <?> clas) {
		try {
			Class <?> cls = clas;
			Start s;
			boolean noStart = true;
			Constructor <?>[] cs = cls.getConstructors();
			for (Constructor <?> c : cs) {
				s = c.getAnnotation(Start.class);
				if (s == null) continue;
				if (s.disabled()) continue;
				noStart = false;
				Parameter[] params = c.getParameters();
				Object[] ps = new Object[params.length];
				for (int i = 0; i < params.length; i ++ ) {
					ParamCreater pc = params[i].getAnnotation(ParamCreater.class);
					Class <?> type = params[i].getType();
					if (pc != null) {
						String[] method = pc.method();
						Class <?>[] classes = new Class[method.length - 1];
						for (int ii = 0; ii < classes.length; ii ++ ) {
							classes[ii] = Class.forName(method[ii + 1]);
						}
						Method met = clas.getDeclaredMethod(method[0], classes);
						boolean flag = met.isAccessible();
						met.setAccessible(true);
						Result r = run(met, null);
						met.setAccessible(flag);
						if (r.badResult()) {
							CheckResult cr = new CheckResult();
							cr.set(met, r);
							return cr;
						}
						ps[i] = r.getResult();
					} else if (type.isPrimitive()) {
						if (type == Boolean.TYPE) ps[i] = Boolean.FALSE;
						else if (type == Integer.TYPE) ps[i] = 0;
						else if (type == Double.TYPE) ps[i] = 0.0d;
						else if (type == Character.TYPE) ps[i] = '\0';
						else if (type == Byte.TYPE) ps[i] = (Byte) (byte) 0;
						else if (type == Long.TYPE) ps[i] = 0l;
						else if (type == Float.TYPE) ps[i] = 0.0f;
						else if (type == Short.TYPE) ps[i] = (Short) (short) 0;
						else throw new InternalError("unknown primitiv param: '" + type + '\'');
					} else if (params[i].isVarArgs()) ps[i] = Array.newInstance(type, 0);
					else ps[i] = null;
				}
				Object instance = c.newInstance(ps);
				if (instance instanceof Checker) {
					return ((Checker) instance).result();
				} else {
					return new Checker(instance).result();
				}
			}
			Constructor <?> c = cls.getConstructor();
			s = c.getAnnotation(Start.class);
			if ( (s == null && noStart) && (s == null || !s.disabled())) {
				boolean flag = c.isAccessible();
				c.setAccessible(true);
				// c.canAccess(null);
				Object instance = c.newInstance();
				c.setAccessible(flag);
				if (instance instanceof Checker) {
					return ((Checker) instance).result();
				} else {
					return new Checker(instance).result();
				}
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| ClassNotFoundException ignore) {
		}
		Checker c = new Checker();
		c.load(clas);
		return c.result();
	}
	
	/**
	 * this will generate a {@link Checker} of {@code clas}, even if {@code clas} does not {@code extend} {@link Checker}<br>
	 * 
	 * @param clas
	 *            the {@link Class} to be checked
	 * @return the result of the created {@link Checker}
	 */
	public static Checker generateChecker(final Class <?> clas) {
		if (Checker.class.isAssignableFrom(clas)) {
			try {
				@SuppressWarnings("unchecked")
				Class <? extends Checker> cls = (Class <? extends Checker>) clas;
				Constructor <? extends Checker> c = cls.getConstructor();
				return c.newInstance();
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ignore) {
			}
		}
		Checker c = new Checker();
		c.load(clas);
		return c;
	}
	
	public static BigCheckResult checkAll(boolean needEnabedCheckClass, ClassLoader classLoader, String... fullClassNames) {
		BigCheckResult bcr = new BigCheckResult();
		for (String fcn : fullClassNames) {
			try {
				Class <?> cls;
				cls = Class.forName(fcn, true, classLoader);
				if (needEnabedCheckClass) {
					CheckClass cc = cls.getAnnotation(CheckClass.class);
					if (cc == null) continue;
					if (cc.disabled()) continue;
				}
				bcr.put(cls, check(cls));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		bcr.setEnd(System.currentTimeMillis());
		return bcr;
	}
	
	public static BigCheckResult checkAll(boolean needEnabedCheckClass, ClassLoader classLoader, String[] fullClassNames, Class <?>... check) {
		BigCheckResult bcr = new BigCheckResult();
		for (String fcn : fullClassNames) {
			try {
				Class <?> cls;
				cls = Class.forName(fcn, true, classLoader);
				if (needEnabedCheckClass) {
					CheckClass cc = cls.getAnnotation(CheckClass.class);
					if (cc == null) continue;
					if (cc.disabled()) continue;
				}
				bcr.put(cls, check(cls));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		for (Class <?> cls : check) {
			if (needEnabedCheckClass) {
				CheckClass cc = cls.getAnnotation(CheckClass.class);
				if (cc == null) {
					continue;
				}
				if (cc.disabled()) {
					continue;
				}
			}
			bcr.put(cls, check(cls));
		}
		bcr.setEnd(System.currentTimeMillis());
		return bcr;
	}
	
	public static BigCheckResult checkAll(boolean needEnabedCheckClass, String... fullClassNames) {
		BigCheckResult bcr = new BigCheckResult();
		for (String fcn : fullClassNames) {
			try {
				Class <?> cls;
				cls = Class.forName(fcn);
				if (needEnabedCheckClass) {
					CheckClass cc = cls.getAnnotation(CheckClass.class);
					if (cc == null) continue;
					if (cc.disabled()) continue;
				}
				bcr.put(cls, check(cls));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		bcr.setEnd(System.currentTimeMillis());
		return bcr;
	}
	
	public static BigCheckResult checkAll(boolean needEnabedCheckClass, String[] fullClassNames, Class <?>... check) {
		BigCheckResult bcr = new BigCheckResult();
		for (String fcn : fullClassNames) {
			try {
				Class <?> cls;
				cls = Class.forName(fcn);
				if (needEnabedCheckClass) {
					CheckClass cc = cls.getAnnotation(CheckClass.class);
					if (cc == null) continue;
					if (cc.disabled()) continue;
				}
				bcr.put(cls, check(cls));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		for (Class <?> cls : check) {
			if (needEnabedCheckClass) {
				CheckClass cc = cls.getAnnotation(CheckClass.class);
				if (cc == null) {
					continue;
				}
				if (cc.disabled()) {
					continue;
				}
			}
			bcr.put(cls, check(cls));
		}
		bcr.setEnd(System.currentTimeMillis());
		return bcr;
	}
	
	public static BigCheckResult checkAll(boolean needEnabedCheckClass, Class <?>... check) {
		BigCheckResult bcr = new BigCheckResult();
		for (Class <?> cls : check) {
			if (needEnabedCheckClass) {
				CheckClass cc = cls.getAnnotation(CheckClass.class);
				if (cc == null) {
					continue;
				}
				if (cc.disabled()) {
					continue;
				}
			}
			bcr.put(cls, check(cls));
		}
		bcr.setEnd(System.currentTimeMillis());
		return bcr;
	}
	
	public static BigCheckResult checkAll(boolean needEnabedCheckClass, Iterator <Class <?>> check) {
		BigCheckResult bcr = new BigCheckResult();
		while (check.hasNext()) {
			Class <?> cls = check.next();
			if (needEnabedCheckClass) {
				CheckClass cc = cls.getAnnotation(CheckClass.class);
				if (cc == null) {
					continue;
				}
				if (cc.disabled()) {
					continue;
				}
			}
			
			CheckResult cr = check(cls);
			bcr.put(cls, cr);
		}
		bcr.setEnd(System.currentTimeMillis());
		return bcr;
	}
	
}

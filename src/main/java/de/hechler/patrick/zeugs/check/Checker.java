package de.hechler.patrick.zeugs.check;

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiConsumer;

import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;
import de.hechler.patrick.zeugs.check.anotations.End;
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

public abstract class Checker implements Runnable {
	
	static {
		Checker.class.getClassLoader().setDefaultAssertionStatus(true);
	}
	
	private List <Method> init     = null;
	private List <Method> start    = null;
	private List <Method> end      = null;
	private List <Method> finalize = null;
	private List <Method> check    = null;
	
	private CheckResult result;
	
	//@formatter:off
	protected Checker() { }
	//@formatter:on
	
	
	
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
		throw new CheckerFailException("irgendetwas ist falsch");
	}
	
	
	
	public CheckResult result() {
		if (result == null) run();
		return result;
	}
	
	
	public final void run() {
		if (this.init == null) load(this.getClass());
		this.result = new CheckResult();
		this.init.forEach(m -> run(m));
		this.check.forEach(m -> {
			this.start.forEach(r -> run(r));
			Throwable res = run(m);
			this.result.set(m, res);
			this.end.forEach(r -> run(r));
		});
		this.finalize.forEach(m -> run(m));
	}
	
	private Throwable run(Method m) {
		try {
			m.invoke(this);
			return null;
		} catch (IllegalAccessException e) {
			throw new AssertionError("can't acces method: " + m.getName(), e);
		} catch (IllegalArgumentException e) {
			if ( !Checker.class.isAssignableFrom(m.getDeclaringClass())) {
				throw new AssertionError("can't check method: '" + m.getName() + "' of class '" + m.getDeclaringClass().getName() + "'", e);
			}
			throw new AssertionError("can't check method: '" + m.getName() + "' params: " + m.getParameterCount() + " : " + Arrays.deepToString(m.getParameterTypes()), e);
		} catch (InvocationTargetException e) {
			Throwable err = e.getCause();
			return err;
		}
	}
	
	
	
	private void load(Class <?> clas) {
		try {
			clas.getClassLoader().setClassAssertionStatus(clas.getCanonicalName(), true);
		} catch (Throwable e) {// sollte eigentlich nicht passieren
			e.printStackTrace();
		}
		boolean needStatic = !Checker.class.isAssignableFrom(clas);
		Method[] methods = clas.getDeclaredMethods();
		this.init = new ArrayList <>();
		this.start = new ArrayList <>();
		this.end = new ArrayList <>();
		this.finalize = new ArrayList <>();
		this.check = new ArrayList <>();
		for (Method m : methods) {
			Start s = m.getAnnotation(Start.class);
			if (s != null) {
				if (m.getParameterTypes().length != 0) {
					throw new AssertionError("can't chack methods with parameters: " + m.getName());
				}
				if (needStatic) {
					m.canAccess(null);
				}
				if (s.onlyOnce()) {
					this.init.add(m);
				} else {
					this.start.add(m);
				}
			}
			End e = m.getAnnotation(End.class);
			if (e != null) {
				if (m.getParameterTypes().length != 0) {
					throw new AssertionError("can't chack methods with parameters: " + m.getName());
				}
				if (needStatic) {
					m.canAccess(null);
				}
				if (e.onlyOnce()) {
					this.finalize.add(m);
				} else {
					this.end.add(m);
				}
			}
			Check c = m.getAnnotation(Check.class);
			if (c != null) {
				if ( !c.disabled()) {
					if (m.getParameterTypes().length != 0) {
						throw new AssertionError("can't chack methods with parameters: " + m.getName());
					}
					if (needStatic) {
						m.canAccess(null);
					}
					this.check.add(m);
				}
			}
		}
	}
	
	
	
	/**
	 * this will generate a {@link Checker} of {@code clas}, even if {@code clas} does not {@code extend} {@link Checker}<br>
	 * 
	 * if {@code clas} is no {@link Checker} all {@link Check}, {@link Start} and {@link End} methods (if enabled) have to be {@code static}.<br>
	 * if {@code clas} is a {@link Check}, but has no reachable {@link Constructor} without {@link Parameter}, it will be handled like a non {@link Check} {@link Class}<br>
	 * 
	 * this method will use the generated {@link Check} to generate a {@link CheckResult}.
	 * 
	 * @param clas
	 *            the {@link Class} to be checked
	 * @return the result of the created {@link Checker}
	 * @implNote it behaves exactly like <code>{@link #generateChecker(Class)}.{@link #result()}</code>
	 * @deprecated you should make {@code clas} assignable from {@link Checker} and than use the {@link #result} method
	 */
	@Deprecated
	public static CheckResult check(final Class <?> clas) {
		if (Checker.class.isAssignableFrom(clas)) {
			try {
				@SuppressWarnings("unchecked")
				Class <? extends Checker> cls = (Class <? extends Checker>) clas;
				Constructor <? extends Checker> c = cls.getConstructor();
				Checker instance = c.newInstance();
				return instance.result();
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			}
		}
		Checker c = new Checker() {
		};
		c.load(clas);
		return c.result();
	}
	
	/**
	 * this will generate a {@link Checker} of {@code clas}, even if {@code clas} does not {@code extend} {@link Checker}<br>
	 * 
	 * if {@code clas} is no {@link Checker} all {@link Check}, {@link Start} and {@link End} methods (if enabled) have to be {@code static}.<br>
	 * if {@code clas} is a {@link Check}, but has no reachable {@link Constructor} without {@link Parameter}, it will be handled like a non {@link Check} {@link Class}<br>
	 * 
	 * @param clas
	 *            the {@link Class} to be checked
	 * @return the result of the created {@link Checker}
	 * @deprecated you should make {@code clas} assignable from {@link Checker} and than use a {@link Constructor}
	 */
	@Deprecated
	public static Checker generateChecker(final Class <?> clas) {
		if (Checker.class.isAssignableFrom(clas)) {
			try {
				@SuppressWarnings("unchecked")
				Class <? extends Checker> cls = (Class <? extends Checker>) clas;
				Constructor <? extends Checker> c = cls.getConstructor();
				return c.newInstance();
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			}
		}
		Checker c = new Checker() {
		};
		c.load(clas);
		return c;
	}
	
	public static final class CheckResult {
		
		private Map <String, Method>    methods = new HashMap <>();
		private Map <Method, Throwable> results = new HashMap <>();
		
		
		
		private void set(Method m, Throwable value) {
			this.methods.put(m.getName(), m);
			this.results.put(m, value);
		}
		
		public boolean checked(String mname) {
			return this.methods.containsKey(mname);
		}
		
		public boolean checked(Method m) {
			return this.results.containsKey(m);
		}
		
		public boolean wentExpected(String mname) throws NoSuchElementException {
			Method m = this.methods.get(mname);
			if (m == null) throw new NoSuchElementException("missing method '" + mname + "' in my methods: " + this.methods.keySet());
			return this.results.get(m) == null;
		}
		
		public boolean wentExpected(Method m) throws NoSuchElementException {
			if ( !this.results.containsKey(m)) throw new NoSuchElementException("missing method '" + m.getName() + "' in my methods: " + this.methods.keySet());
			return this.results.get(m) != null;
		}
		
		public boolean wentExpected() {
			for (Throwable o : this.results.values()) {
				if (o != null) return false;
			}
			return true;
		}
		
		public int cehckedCount() {
			return this.results.size();
		}
		
		public Throwable getException(String methodName) throws NoSuchElementException {
			Method m = methods.get(methodName);
			if (m == null) throw new NoSuchElementException("missing method '" + methodName + "' in my methods: " + this.methods.keySet());
			return results.get(m);
		}
		
		public Throwable getException(Method m) throws NoSuchElementException {
			if ( !results.containsKey(m)) throw new NoSuchElementException("missing method '" + m.getName() + "' in my methods: " + this.methods.keySet());
			return results.get(m);
		}
		
		public Map <Method, Throwable> allUnexpected() {
			Map <Method, Throwable> ret = new HashMap <Method, Throwable>();
			results.forEach((m, t) -> {
				if (t != null) {
					ret.put(m, t);
				}
			});
			return ret;
		}
		
		public void forAll(BiConsumer <Method, Throwable> c) {
			results.forEach(c);
		}
		
		public void forAllUnexpected(BiConsumer <Method, Throwable> c) {
			results.forEach((m, t) -> {
				if (t != null) c.accept(m, t);
			});
		}
		
		public void print() {
			print(System.out);
		}
		
		public void print(final PrintStream out) {
			StringBuilder str = new StringBuilder(System.lineSeparator());
			IntInt cnt = new IntInt();
			this.results.forEach((m, r) -> {
				boolean b = (r == null);
				cnt.a ++ ;
				if (b) cnt.b ++ ;
				str.append("   ").append(m.getName()).append(" -> ");
				if (b) {
					str.append("good");
				} else {
					str.append("bad: ");
					str.append(r);
				}
				str.append(System.lineSeparator());
			});
			str.insert(0, (cnt.b == cnt.a) ? "good" : "bad");
			str.insert(0, " -> ");
			str.insert(0, cnt.a);
			str.insert(0, '/');
			str.insert(0, cnt.b);
			str.insert(0, "RESULT: ");
			out.print(str);
		}
		
		private String printStr(String name, IntInt ii) {
			StringBuilder str = new StringBuilder();
			IntInt cnt = new IntInt();
			this.results.forEach((m, r) -> {
				boolean b = (r == null);
				cnt.a ++ ;
				if (b) cnt.b ++ ;
				str.append("   ").append(m.getName()).append(" -> ");
				if (b) {
					str.append("good");
				} else {
					str.append("bad: ");
					str.append(r);
				}
				str.append(System.lineSeparator());
			});
			str.insert(0, System.lineSeparator());
			str.insert(0, (cnt.b == cnt.a) ? "good" : "bad");
			str.insert(0, " -> ");
			str.insert(0, cnt.a);
			str.insert(0, '/');
			str.insert(0, cnt.b);
			str.insert(0, ": ");
			str.insert(0, name);
			ii.a += cnt.a;
			ii.b += cnt.b;
			return str.toString();
		}
		
	}
	
	//@formatter:off
	private static class IntInt { int a, b; }
	//@formatter:on
	
	
	
	public static BigCheckResult checkAll(boolean needEnabedCheckClass, Class <?>... check) {
		BigCheckResult bcr = new BigCheckResult();
		for (Class <?> cls : check) {
			if (needEnabedCheckClass) {
				CheckClass cc = cls.getAnnotation(CheckClass.class);
				if (cc == null) continue;
				if (cc.disabled()) continue;
			}
			bcr.put(cls, check(cls));
		}
		return bcr;
	}
	
	public static BigCheckResult checkAll(boolean needEnabedCheckClass, Iterable <Class <?>> check) {
		BigCheckResult bcr = new BigCheckResult();
		for (Class <?> cls : check) {
			if (needEnabedCheckClass) {
				CheckClass cc = cls.getAnnotation(CheckClass.class);
				if (cc == null) continue;
				if (cc.disabled()) continue;
			}
			
			CheckResult cr = check(cls);
			bcr.put(cls, cr);
		}
		return bcr;
	}
	
	public static BigCheckResult checkAll(boolean needEnabedCheckClass, Iterator <Class <?>> check) {
		BigCheckResult bcr = new BigCheckResult();
		while (check.hasNext()) {
			Class <?> cls = check.next();
			if (needEnabedCheckClass) {
				CheckClass cc = cls.getAnnotation(CheckClass.class);
				if (cc == null) continue;
				if (cc.disabled()) continue;
			}
			
			CheckResult cr = check(cls);
			bcr.put(cls, cr);
		}
		return bcr;
	}
	
	
	
	public final static class BigCheckResult {
		
		private Map <String, Class <?>>      classes = new HashMap <>();
		private Map <Class <?>, CheckResult> results = new HashMap <>();
		
		public CheckResult put(Class <?> cls, CheckResult result) {
			classes.put(cls.getName(), cls);
			return results.put(cls, result);
		}
		
		public CheckResult get(Class <?> cls) {
			return results.get(cls);
		}
		
		public CheckResult get(String fullClassName) {
			Class <?> cls = classes.get(fullClassName);
			return results.get(cls);
		}
		
		public boolean wentExpected(Class <?> cls) {
			return results.get(cls).wentExpected();
		}
		
		public boolean wentExpected(String fullClassName) {
			Class <?> cls = classes.get(fullClassName);
			return results.get(cls).wentExpected();
		}
		
		public Map <Class <?>, CheckResult> allUnexpected() {
			Map <Class <?>, CheckResult> ret = new HashMap <Class <?>, Checker.CheckResult>();
			results.forEach((c, r) -> {
				if ( !r.wentExpected()) {
					ret.put(c, r);
				}
			});
			return ret;
		}
		
		public void forAllCheckResults(BiConsumer <Class <?>, CheckResult> m) {
			results.forEach(m);
		}
		
		public void forAllUnexpectedCheckResults(BiConsumer <Class <?>, CheckResult> m) {
			results.forEach((c, r) -> {
				if ( !r.wentExpected()) {
					m.accept(c, r);
				}
			});
		}
		
		public void forAll(TriConsumer <Class <?>, Method, Throwable> tc) {
			results.forEach((c, r) -> r.forAll((m, t) -> tc.accept(c, m, t)));
		}
		
		public void forAllUnexpected(TriConsumer <Class <?>, Method, Throwable> tc) {
			results.forEach((c, r) -> r.forAllUnexpected((m, t) -> tc.accept(c, m, t)));
		}
		
		public static interface TriConsumer <A, B, C> {
			
			void accept(A a, B b, C c);
			
		}
		
		public Class <?> getClass(String fullClassName) {
			return classes.get(fullClassName);
		}
		
		public void print() {
			print(System.out);
		}
		public void print(PrintStream out) {
			List <String> prints = new ArrayList <>();
			IntInt ii = new IntInt();
			results.forEach((c, r) -> {
				prints.add(r.printStr(c.getSimpleName(), ii));
			});
			out.println("RESULT: " + ii.b + '/' + ii.a + " -> " + ( (ii.b == ii.a) ? "good" : "bad"));
			prints.forEach(s -> out.print(s));
		}
		
	}
	
}

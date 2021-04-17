package de.hechler.patrick.zeugs.check;

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

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
import de.hechler.patrick.zeugs.check.exceptions.CheckerNotEqualsExeption;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNotNullExeption;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNotThrownException;
import de.hechler.patrick.zeugs.check.exceptions.CheckerNullExeption;
import de.hechler.patrick.zeugs.check.interfaces.ThrowingRunnable;

public abstract class Checker implements Runnable {
	
	private List <Method> init;
	private List <Method> start;
	private List <Method> end;
	private List <Method> finalize;
	private List <Method> check;
	
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
	
	public static void assertNotNull(Object a) throws CheckerException {
		if (a == null) {
			throw new CheckerNullExeption();
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
	
	
	
	public static void assertThrowsAny(ThrowingRunnable <?> r) throws CheckerException {
		try {
			r.run();
			throw new CheckerNotThrownException();
		} catch (Throwable ignore) {
		}
	}
	
	public static <T extends Throwable> void assertThrows(Class <T> assertThrown, ThrowingRunnable <?> r) throws CheckerException {
		try {
			r.run();
			throw new CheckerNotThrownException(assertThrown);
		} catch (Throwable err) {
			Class <? extends Throwable> cls = err.getClass();
			if (cls != assertThrown) {
				if ( !assertThrown.isAssignableFrom(cls)) {
					throw new CheckerNotThrownException(assertThrown, cls);
				}
			}
		}
	}
	
	public static <T extends Throwable> void assertThrows(boolean exactClass, Class <T> assertThrown, ThrowingRunnable <?> r) throws CheckerException {
		try {
			r.run();
			throw new CheckerNotThrownException(assertThrown);
		} catch (Throwable err) {
			Class <? extends Throwable> cls = err.getClass();
			if (cls != assertThrown) {
				if (exactClass) {
					throw new CheckerNotThrownException(assertThrown, cls, exactClass);
				}
				if ( !assertThrown.isAssignableFrom(cls)) {
					throw new CheckerNotThrownException(assertThrown, cls, exactClass);
				}
			}
		}
	}
	
	
	public static void fail(String msg) throws CheckerFailException {
		throw new CheckerFailException(msg);
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
		
		private Map <String, Method> methods = new HashMap <String, Method>();
		private Map <Method, Object> results = new HashMap <Method, Object>();
		
		
		
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
			for (Object o : this.results.values()) {
				if (o != null) return false;
			}
			return true;
		}
		
		public int cehckedCount() {
			return this.results.size();
		}
		
		public void print(final PrintStream out) {
			StringBuilder str = new StringBuilder(System.lineSeparator());
			IntInt cnt = new IntInt();
			this.results.forEach((m, r) -> {
				boolean b = (r == null);
				cnt.a ++ ;
				if (b) cnt.b ++ ;
				str.append("   ").append(m.getName()).append(" -> ").append(b ? "good" : "bad").append(System.lineSeparator());
			});
			str.insert(0, (cnt.b == cnt.a) ? "good" : "bad");
			str.insert(0, " -> ");
			str.insert(0, cnt.a);
			str.insert(0, '/');
			str.insert(0, cnt.b);
			str.insert(0, "RESULT: ");
			out.print(str);
		}
		
		public String printStr(String name, IntInt ii) {
			StringBuilder str = new StringBuilder();
			IntInt cnt = new IntInt();
			this.results.forEach((m, r) -> {
				boolean b = (r == null);
				cnt.a ++ ;
				if (b) cnt.b ++ ;
				str.append("      ").append(m.getName()).append(" -> ").append(b ? "good" : "bad").append(System.lineSeparator());
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
	
	
	
	public static BigCheckerResult checkAll(boolean needEnabedCheckClass, Class <?>... check) {
		BigCheckerResult bcr = new BigCheckerResult();
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
	
	public static BigCheckerResult checkAll(boolean needEnabedCheckClass, Collection <Class <?>> check) {
		BigCheckerResult bcr = new BigCheckerResult();
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
	
	public final static class BigCheckerResult {
		
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
		
		public Class <?> getClass(String fullClassName) {
			return classes.get(fullClassName);
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

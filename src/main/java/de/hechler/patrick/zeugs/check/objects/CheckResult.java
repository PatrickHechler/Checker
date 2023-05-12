//This file is part of the Checker Project
//DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
//Copyright (C) 2023  Patrick Hechler
//
//This program is free software: you can redistribute it and/or modify
//it under the terms of the GNU General Public License as published by
//the Free Software Foundation, either version 3 of the License, or
//(at your option) any later version.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//You should have received a copy of the GNU General Public License
//along with this program.  If not, see <https://www.gnu.org/licenses/>.
package de.hechler.patrick.zeugs.check.objects;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;

import de.hechler.patrick.zeugs.check.interfaces.TriConsumer;
import de.hechler.patrick.zeugs.check.interfaces.TwoVals;

/**
 * this class saves the {@link Result}s of a {@link Checker}.<br>
 * The {@link Result} of each checked {@link Method} is accessible with the
 * {@link #getResult(Method)} method
 * <p>
 * in addition to all checked methods, the executed time for all checks is
 * saved.<br>
 * to get the time needed for the execution the class check {@link #getTime()}
 * can be used.<br>
 * to get the start time of the class check the field {@link #start} can be
 * accessed.<br>
 * to get the end time of the class check {@link #end} can be accessed.
 * 
 * @author Patrick
 */
public final class CheckResult {
	
	/**
	 * this map contains all {@link Result} and the {@link Method} with the
	 * parameters, from which the
	 * results are
	 */
	private final Map<TwoVals<Method, Object[]>, Result> results;
	/**
	 * the time when the checks were started
	 */
	public final long                                    start;
	/**
	 * the time when all checks were finished
	 */
	public final long                                    end;
	
	/**
	 * creates a new {@link CheckResult} with the given values.
	 * 
	 * @param results
	 *                this map contains the {@link Result} of the checks.
	 * @param start
	 *                the time when the checker started to check
	 * @param end
	 *                the time when the checks were finished
	 */
	public CheckResult(Map<TwoVals<Method, Object[]>, Result> results, long start, long end) {
		this.results = Collections.unmodifiableMap(results);
		this.start   = start;
		this.end     = end;
	}
	
	/**
	 * returns the total time needed for this {@link CheckResult}
	 * 
	 * @return the total time needed for this {@link CheckResult}
	 */
	public long getTime() { return end - start; }
	
	/**
	 * this method saves the {@link Result} with the {@link Method} in the
	 * {@code results} and the method with its {@link Method#getName() name} (and
	 * {@link Method#getParameters() params}) in the
	 * {@code methods}.<br>
	 * When the {@link Method} has no params
	 * (<code>{@link Method#getParameterCount()} == 0</code>) there will be two
	 * links created to the {@link Method} in {@code methods}: one wit only the
	 * {@link Method#getName() name} and one with the {@link Method#getName() name}
	 * and the braces '()'.<br>
	 * When the {@link Method} has params
	 * (<code>{@link Method#getParameterCount()} > 0</code>) there will be one link
	 * in the {@code methods}: it will start with the {@link Method#getName() name}
	 * and
	 * then the braces. between the braces will be the {@link Method#getParameters()
	 * params} with only their {@link Parameter#getType() type} as fully qualifying
	 * name. if a param is a
	 * {@link Parameter#isVarArgs() varArg}, it will have '...' at the end and a
	 * second with '[]' at the end all other arrays will have only '[]' at the end.
	 * The {@link Method#getParameters() params}
	 * will be separated by <code>', '</code>.
	 * 
	 * @param methods
	 *                the map containing the methods refereed by their names
	 * @param results
	 *                the map containing the results refereed by the Method
	 * @param met
	 *                the checked {@link Method}
	 * @param value
	 *                the {@link Result} value of the checked {@link Method}
	 *                public static void put(Map<String, Method> methods,
	 *                Map<Method, Result> results, Method met, Result value) {
	 *                if (met.getParameterCount() > 0) {
	 *                StringBuilder name = new
	 *                StringBuilder(met.getName()).append('(');
	 *                Parameter[] clss = met.getParameters();
	 *                Class<?> zw = clss[0].getType();
	 *                name.append(zw.getCanonicalName());
	 *                for (int i = 1; i < clss.length; i++) {
	 *                zw = clss[i].getType();
	 *                name.append(", ").append(zw.getCanonicalName());
	 *                }
	 *                String methodName = name.append(')').toString();
	 *                addToMethods(methods, results, met, methodName);
	 *                results.put(met, value);
	 *                name = new StringBuilder(met.getName()).append('(');
	 *                zw = clss[0].getType();
	 *                name.append(zw.getCanonicalName());
	 *                for (int i = 1; i < clss.length - 1; i++) {
	 *                zw = clss[i].getType();
	 *                name.append(", ").append(zw.getCanonicalName());
	 *                }
	 *                if (clss[clss.length - 1].isVarArgs()) {
	 *                zw = clss[clss.length - 1].getType();
	 *                name.append(",
	 *                ").append(zw.getComponentType().getCanonicalName());
	 *                name.append("...");
	 *                } else {
	 *                zw = clss[clss.length - 1].getType();
	 *                name.append(", ").append(zw.getCanonicalName());
	 *                }
	 *                addToMethods(methods, results, met, name.toString());
	 *                } else {
	 *                methods.put(met.getName() + "()", met);
	 *                methods.put(met.getName(), met);
	 *                results.put(met, value);
	 *                }
	 *                }
	 *                private static void addToMethods(Map<String, Method> methods,
	 *                Map<Method, Result> results, Method m, String methodName) {
	 *                Method met = methods.get(methodName);
	 *                if (met != null) {
	 *                Result res = results.get(met);
	 *                if (res.badResult()) { return; }
	 *                }
	 *                methods.put(methodName, m);
	 *                }
	 */
	
	/**
	 * returns the result, which was checked with the given parameters
	 * 
	 * @param m      the checked method
	 * @param params the parameters of the method
	 * 
	 * @return the result, which was checked with the given parameters
	 * 
	 * @throws NoSuchElementException if there is no such result
	 */
	public Result getResult(Method m, Object[] params) throws NoSuchElementException {
		Result r = results.get(new TwoValues<>(m, params));
		if (r == null) { throw new NoSuchElementException(); }
		return r;
	}
	
	/**
	 * returns the result, which was checked with the given parameters
	 * 
	 * @param tv the {@link TwoVals} object holds as {@link TwoVals#valA()} the
	 *           checked method and as {@link TwoVals#valB()} the parameters
	 * 			
	 * @return the result, which was checked with the given parameters
	 * 			
	 * @throws NoSuchElementException if there is no such result
	 */
	public Result getResult(TwoVals<Method, Object[]> tv) throws NoSuchElementException {
		Result r = results.get(tv);
		if (r == null) { throw new NoSuchElementException(); }
		return r;
	}
	
	/**
	 * returns the {@link Result} from the {@link #results} by the {@link Method}
	 * 
	 * @param m
	 *          the {@link Method}
	 * 			
	 * @return the {@link Result} from the {@link #results} by the {@link Method}
	 * 			
	 * @throws NoSuchElementException
	 *                                if there is no {@link Result} in the
	 *                                {@link #results} for the {@link Method}
	 * 								
	 * @see #getResult(String)
	 */
	public Map<Object[], Result> getResult(Method m) throws NoSuchElementException {
		Map<Object[], Result> res = new HashMap<>();
		results.entrySet().stream().filter(e -> e.getKey().valA().equals(m)).forEach(e -> res.put(e.getKey().valB(), e.getValue()));
		if (res.isEmpty()) { throw new NoSuchElementException(); }
		return res;
	}
	
	/**
	 * returns all {@link Method} objects with the given name with all parameters
	 * used for execution
	 * <p>
	 * the method name is the name of the method plus it's parameter classes wrapped
	 * in parentheses and separated with a comma and a single space character.<br>
	 * if the method has no parameters, the parentheses are optional.
	 * <p>
	 * if due to super checkers multiple methods with the name has been checked it
	 * is not defined, which method will be returned.<br>
	 * but if at least one of the results is a {@link Result#badResult()} it is
	 * ensured that a {@link Result#badResult()} will be returned
	 * 
	 * @param mname
	 *              the name of the {@link Method}
	 * 				
	 * @return all {@link Method} objects with the given name with all parameters
	 *         used for execution
	 * 		
	 * @throws NoSuchElementException
	 *                                if the no {@link Method} with the given name
	 *                                was checked
	 */
	public Map<Method, List<Object[]>> getMethod(String mname) throws NoSuchElementException {
		Map<Method, List<Object[]>> res = new HashMap<>();
		results.entrySet().stream().filter(e -> e.getKey().valA().getName().equals(mname)).forEach(e -> {
			TwoVals<Method, Object[]> key = e.getKey();
			res.computeIfAbsent(key.valA(), m -> new LinkedList<>()).add(key.valB());
		});
		if (res.size() == 0) { throw new NoSuchElementException(); }
		return res;
	}
	
	/**
	 * returns <code>true</code> if this {@link CheckResult} contains a
	 * {@link Result} for a {@link Method} with this name (and with the params if
	 * the {@link Method} have some), <code>false</code>
	 * otherwise
	 * 
	 * @param mname
	 *              the name of the {@link Method} and their potentially params
	 * 				
	 * @return <code>true</code> if this {@link CheckResult} contains a
	 *         {@link Result} for a {@link Method} with this name (and with the
	 *         params if the {@link Method} have some), <code>false</code>
	 *         otherwise
	 */
	public boolean checked(String mname) { return results.entrySet().stream().anyMatch(e -> e.getKey().valA().getName().equals(mname)); }
	
	/**
	 * returns <code>true</code> if this {@link CheckResult} contains a
	 * {@link Result} for this {@link Method}, <code>false</code> otherwise
	 * 
	 * @param m
	 *          the {@link Method} which is potentially saved in this
	 *          {@link CheckResult}
	 * 
	 * @return <code>true</code> if this {@link CheckResult} contains a
	 *         {@link Result} for this {@link Method}, <code>false</code> otherwise
	 */
	public boolean checked(Method m) { return results.entrySet().stream().anyMatch(e -> e.getKey().valA().equals(m)); }
	
	/**
	 * returns <code>true</code> if the execution of the method went as expected (if
	 * no {@link Throwable} was thrown)
	 * 
	 * @param mname
	 *              the name of the {@link Method}
	 * 				
	 * @return <code>true</code> if the execution of the method went as expected (if
	 *         no {@link Throwable} was thrown)
	 * 		
	 * @throws NoSuchElementException
	 *                                if no {@link Result} is saved for the method
	 *                                in this {@link CheckResult}
	 */
	public boolean wentExpected(String mname) throws NoSuchElementException {
		Iterator<Entry<TwoVals<Method, Object[]>, Result>> iter = results.entrySet().stream().filter(e -> e.getKey().valA().getName().equals(mname))
				.iterator();
		if (!iter.hasNext()) { throw new NoSuchElementException(); }
		while (iter.hasNext()) {
			if (iter.next().getValue().badResult()) { return false; }
		}
		return true;
	}
	
	/**
	 * returns <code>true</code> if the execution of the {@link Method} went as
	 * expected (if no {@link Throwable} was thrown)
	 * 
	 * @param m
	 *          the {@link Method} identifier
	 * 			
	 * @return <code>true</code> if the execution of the {@link Method} went as
	 *         expected (if no {@link Throwable} was thrown)
	 * 		
	 * @throws NoSuchElementException
	 *                                if no {@link Result} is saved for the
	 *                                {@link Method} in this {@link CheckResult}
	 */
	public boolean wentExpected(Method m) throws NoSuchElementException {
		Iterator<Entry<TwoVals<Method, Object[]>, Result>> iter = results.entrySet().stream().filter(e -> e.getKey().valA().equals(m)).iterator();
		if (!iter.hasNext()) { throw new NoSuchElementException(); }
		while (iter.hasNext()) {
			if (iter.next().getValue().badResult()) { return false; }
		}
		return true;
	}
	
	/**
	 * this method will return <code>true</code> if and only if all Checks went as
	 * {@link Result#goodResult() expected} (without any {@link Throwable} thrown).
	 * if at least one Check is
	 * {@link Result#badResult() bad} this method will return <code>false</code>
	 * 
	 * @return <code>true</code> if none of the {@link #results} is a
	 *         {@link Result#badResult() bad Result}
	 * 		
	 * @see #wentUnexpected()
	 */
	public boolean wentExpected() {
		for (Result r : this.results.values()) {
			if (r.badResult()) return false;
		}
		return true;
	}
	
	/**
	 * returns <code>true</code> if the {@link Result} of the method is a
	 * {@link Result#badResult() bad Result} and <code>false</code> if not.
	 * 
	 * @param mname
	 *              the name of the method
	 * 				
	 * @return <code>true</code> if the {@link Result} of the method is a
	 *         {@link Result#badResult() bad Result} and <code>false</code> if not
	 * 		
	 * @throws NoSuchElementException
	 *                                if there is was no method checked with the
	 *                                given the given name
	 * 								
	 * @see #wentUnexpected(Method)
	 * @see #wentExpected(String)
	 * @see #wentExpected(Method)
	 */
	public boolean wentUnexpected(String mname) throws NoSuchElementException {
		Iterator<Result> iter = results.entrySet().stream().filter(e -> e.getKey().valA().getName().equals(mname)).map(Entry::getValue).iterator();
		if (!iter.hasNext()) { throw new NoSuchElementException(); }
		while (iter.hasNext()) {
			if (iter.next().badResult()) { return true; }
		}
		return false;
	}
	
	/**
	 * returns <code>true</code> if the {@link Result} of the method is a
	 * {@link Result#badResult() bad Result} and <code>false</code> if not. The
	 * {@link Result} will be searched in the
	 * {@link #results} map
	 * 
	 * @param m
	 *          the {@link Method} of which the {@link Result} is checked
	 * 			
	 * @return <code>true</code> if the {@link Result} of the method is a
	 *         {@link Result#badResult() bad Result} and <code>false</code> if not
	 * 		
	 * @throws NoSuchElementException
	 *                                if there is no mapping in the {@link #results}
	 *                                for the given {@link Method}
	 * 								
	 * @see #wentUnexpected(String)
	 * @see #wentExpected(String)
	 * @see #wentExpected(Method)
	 */
	public boolean wentUnexpected(Method m) throws NoSuchElementException {
		Iterator<Entry<TwoVals<Method, Object[]>, Result>> iter = results.entrySet().stream().filter(e -> e.getKey().valA().equals(m)).iterator();
		if (!iter.hasNext()) { throw new NoSuchElementException(); }
		while (iter.hasNext()) {
			if (iter.next().getValue().badResult()) { return true; }
		}
		return false;
	}
	
	/**
	 * returns <code>true</code> when at least one {@link Result} is
	 * {@link Result#badResult() bad}.
	 * 
	 * @return <code>true</code> when at least one {@link Result} is
	 *         {@link Result#badResult() bad}.
	 * 		
	 * @see #wentExpected()
	 */
	public boolean wentUnexpected() {
		for (Result r : this.results.values()) {
			if (r.badResult()) { return true; }
		}
		return false;
	}
	
	/**
	 * returns the number of {@link Result}s saved in this {@link CheckResult}.
	 * 
	 * @return the number of checks saved by this {@link CheckResult}
	 */
	public int cehckedCount() { return this.results.size(); }
	
	/**
	 * returns all executed checks with the given name
	 * 
	 * @param mname
	 *              the name of the {@link Method}
	 * 				
	 * @return all executed checks with the given name
	 * 				
	 * @throws NoSuchElementException
	 *                                if there is no {@link Method} with the name
	 *                                which was checked
	 * 								
	 * @see #getResult(Method)
	 * @see #getMethod(String)
	 */
	public Map<Method, List<TwoVals<Object[], Result>>> getResult(String mname) throws NoSuchElementException {
		Map<Method, List<TwoVals<Object[], Result>>> res = new HashMap<>();
		results.entrySet().stream().filter(e -> e.getKey().valA().getName().equals(mname)).forEach(e -> {
			TwoVals<Method, Object[]>       key  = e.getKey();
			List<TwoVals<Object[], Result>> list = res.computeIfAbsent(key.valA(), m -> new LinkedList<>());
			list.add(new TwoValues<>(key.valB(), e.getValue()));
		});
		return res;
	}
	
	/**
	 * returns all {@link Method}s, which {@link #wentUnexpected(Method) went
	 * unexpected}, with their {@link Throwable}s.
	 * 
	 * @return all {@link Method}s, which {@link #wentUnexpected(Method) went
	 *         unexpected}, with their {@link Throwable}s.
	 */
	public Map<TwoVals<Method, Object[]>, Result> allUnexpected() {
		Map<TwoVals<Method, Object[]>, Result> res = new HashMap<>();
		results.entrySet().stream().filter(e -> e.getValue().badResult()).forEach(e -> res.put(e.getKey(), e.getValue()));
		return res;
	}
	
	/**
	 * returns all {@link Method}s, which {@link #wentExpected(Method) went
	 * expected}, with their returned {@link Object}s.
	 * 
	 * @return all {@link Method}s, which {@link #wentExpected(Method) went
	 *         expected}, with their returned {@link Object}s.
	 */
	public Map<TwoVals<Method, Object[]>, Result> allExpected() {
		Map<TwoVals<Method, Object[]>, Result> res = new HashMap<>();
		results.entrySet().stream().filter(e -> e.getValue().goodResult()).forEach(e -> res.put(e.getKey(), e.getValue()));
		return res;
	}
	
	/**
	 * returns all {@link Method}s, with their {@link Result}s.
	 * 
	 * @return all {@link Method}s, with their {@link Result}s.
	 */
	public Map<TwoVals<Method, Object[]>, Result> allCecked() { return results; }
	
	/**
	 * iterates though {@link #results} and invokes on every {@link Result} the
	 * consumer
	 * 
	 * @param c
	 *          what should be done for all
	 * 			
	 * @see Map#forEach(BiConsumer)
	 */
	public void forAll(TriConsumer<Method, Object[], Result> c) { results.forEach((tv, r) -> c.accept(tv.valA(), tv.valB(), r)); }
	
	/**
	 * iterates though {@link #results} and invokes on every
	 * {@link Result#badResult() unexpected} {@link Result} the consumer
	 * 
	 * @param c
	 *          what should be done for all {@link Result#badResult() unexpected}
	 *          {@link Result}s
	 * 
	 * @see #forAllExpected(TriConsumer)
	 */
	public void forAllUnexpected(TriConsumer<Method, Object[], Result> c) {
		results.entrySet().stream().filter(e -> e.getValue().badResult()).forEach(e -> c.accept(e.getKey().valA(), e.getKey().valB(), e.getValue()));
	}
	
	/**
	 * iterates though {@link #results} and invokes on every
	 * {@link Result#goodResult() expected} {@link Result} the consumer
	 * 
	 * @param c
	 *          what should be done for all {@link Result#goodResult() expected}
	 *          {@link Result}s
	 * 
	 * @see #forAllUnexpected(TriConsumer)
	 */
	public void forAllExpected(TriConsumer<Method, Object[], Result> c) {
		results.entrySet().stream().filter(e -> e.getValue().goodResult()).forEach(e -> c.accept(e.getKey().valA(), e.getKey().valB(), e.getValue()));
	}
	
	/**
	 * prints this {@link CheckResult} on the {@link System#out default out} with an
	 * indention of {@code 4}
	 */
	public void print() { print(System.out, 4); }
	
	/**
	 * prints this {@link CheckResult} on the {@link System#out default out} with
	 * the given indention
	 * 
	 * @param indention
	 *                  the indention of spaces (' ') of the checks
	 */
	public void print(int indention) { print(System.out, indention); }
	
	/**
	 * prints this {@link CheckResult} on the given {@link PrintStream} with an
	 * indention of {@code 4}
	 * 
	 * @param out
	 *            the {@link PrintStream} to use to print this {@link CheckResult}
	 */
	public void print(final PrintStream out) { print(out, 4); }
	
	/**
	 * prints this {@link CheckResult} on the given {@link PrintStream} with the
	 * given indention
	 * 
	 * @param out
	 *                  the {@link PrintStream} to use to print this
	 *                  {@link CheckResult}
	 * @param indention
	 *                  the indention of spaces (' ') of the checks
	 */
	public void print(final PrintStream out, int indention) {
		final String startStr;
		char[]       zw = new char[indention];
		Arrays.fill(zw, ' ');
		startStr = new String(zw);
		StringBuilder str = new StringBuilder(System.lineSeparator());
		TwoInts       cnt = new TwoInts();
		this.results.forEach((m, r) -> {
			str.append(startStr).append(m.valA().getName()).append('(');
			Object[] params = m.valB();
			if (params.length > 0) {
				str.append('\'').append(params[0]).append('\'');
				for (int i = 1; i < params.length; i++) {
					str.append(", '").append(params[i]).append('\'');
				}
			}
			str.append(')').append(" -> ");
			cnt.a++;
			if (r.goodResult()) {
				cnt.b++;
				if (m.valA().getReturnType() == void.class) {
					str.append("good");
				} else {
					str.append("good: ");
					if (r.getResult() == null) {
						str.append("null");
					} else {
						str.append(r.toSimpleString());
					}
				}
			} else {
				str.append("bad: ");
				str.append(r.toSimpleString());
			}
			str.append(System.lineSeparator());
		});
		str.append(System.lineSeparator());
		str.insert(0, cnt.a == cnt.b ? "good" : "bad");
		str.insert(0, " -> ");
		str.insert(0, cnt.a);
		str.insert(0, '/');
		str.insert(0, cnt.b);
		str.insert(0, "RESULT: ");
		out.print(str);
	}
	
	/**
	 * creates a detailed string which contains all {@link Result}s of this
	 * {@link CheckResult} indented with spaces by the doubled given indention.<br>
	 * The given name will be set to the beginning and be indented with spaces by
	 * the given indention.<br>
	 * The given {@code counter} will be modified:<br>
	 * {@link TwoInts#a} will be incremented by the number of {@link Result}s in
	 * this {@link CheckResult}<br>
	 * {@link TwoInts#b} will be incremented by the number of
	 * {@link Result#getResult() good Result}s in this {@link CheckResult}
	 * 
	 * @param builder
	 *                        the {@link StringBuilder} to be filled with a detailed
	 *                        string representation of this {@link CheckResult}
	 * @param name
	 *                        the name of this {@link CheckResult}
	 * @param counter
	 *                        the {@link TwoInts} will be changed as above
	 *                        specified: the {@link Result}number will be added to
	 *                        {@link TwoInts#a a} and the {@link Result#getResult()
	 *                        good Result} number will be
	 *                        added to {@link TwoInts#b b}
	 * @param classIndention
	 *                        the indention in spaces of this {@link CheckResult}
	 * @param methodIndention
	 *                        the indention in spaces for the methods of this
	 *                        {@link CheckResult}
	 */
	public void toString(StringBuilder builder, String name, TwoInts counter, int classIndention, int methodIndention) {
		final char[] start;
		final char[] doubleStart;
		start = new char[classIndention];
		Arrays.fill(start, ' ');
		doubleStart = new char[methodIndention];
		Arrays.fill(doubleStart, ' ');
		int     startIndex = builder.length();
		TwoInts cnt        = new TwoInts();
		this.results.forEach((m, r) -> {
			builder.append(doubleStart).append(m.valA().getName()).append('(');
			Object[] params = m.valB();
			if (params.length > 0) {
				builder.append('\'').append(params[0]).append('\'');
				for (int i = 1; i < params.length; i++) {
					builder.append(", '").append(params[i]).append('\'');
				}
			}
			builder.append(')').append(" -> ");
			cnt.a++;
			if (r.goodResult()) {
				cnt.b++;
				if (m.valA().getReturnType() == void.class) {
					builder.append("good");
				} else {
					builder.append("good: ");
					if (r.getResult() == null) {
						builder.append("null");
					} else {
						builder.append(r.toSimpleString());
					}
				}
			} else {
				builder.append("bad: ");
				builder.append(r.toSimpleString());
			}
			builder.append(System.lineSeparator());
		});
		builder.insert(startIndex, System.lineSeparator());
		builder.insert(startIndex, cnt.a == cnt.b ? "good" : "bad");
		builder.insert(startIndex, " -> ");
		builder.insert(startIndex, cnt.a);
		builder.insert(startIndex, '/');
		builder.insert(startIndex, cnt.b);
		builder.insert(startIndex, ": ");
		builder.insert(startIndex, name);
		builder.insert(startIndex, start);
		counter.a += cnt.a;
		counter.b += cnt.b;
	}
	
	/**
	 * prints a all results in a detailed format this just uses
	 * {@link #detailedPrint(PrintStream, int, int)} with a {@code doubleIndention}
	 * which is <code>(indention * 2)}</code><br>
	 * the time will be in the first line, which will NOT be intended!
	 * 
	 * @param out
	 *                  the {@link PrintStream} on which the {@link Result}s should
	 *                  be printed
	 * @param indention
	 *                  the normal indention
	 * 					
	 * @see #detailedPrintUnexpected(PrintStream, int, int)
	 */
	public void detailedPrint(PrintStream out, int indention) { detailedPrint(out, indention, indention << 1); }
	
	/**
	 * prints a all results in a detailed format<br>
	 * the time will be in the first line, which will NOT be intended!
	 * 
	 * @param out
	 *                       the {@link PrintStream} on which the {@link Result}s
	 *                       should be printed
	 * @param indention
	 *                       the normal indention
	 * @param doubleIndented
	 *                       the complete indention for the doubled indented lines
	 */
	public void detailedPrint(PrintStream out, int indention, int doubleIndented) {
		String indent  = null;
		String dindent = null;
		{
			StringBuilder zw = new StringBuilder(Math.max(indention, doubleIndented));
			for (int i = 0; i < Math.min(indention, doubleIndented); i++) {
				zw.append(' ');
			}
			if (indention < doubleIndented) {
				indent = zw.toString();
			} else {
				dindent = zw.toString();
			}
			for (int i = 0; i < Math.max(doubleIndented, indention); i++) {
				zw.append(' ');
			}
			if (doubleIndented < indention) {
				indent = zw.toString();
			} else {
				dindent = zw.toString();
			}
		}
		int cnt = 0;
		for (Result r : this.results.values()) {
			if (r.goodResult()) { cnt++; }
		}
		out.println("time=" + (end - start) + "ms " + cnt + "/" + this.results.size());
		final String i = indent, di = dindent;
		this.forAll((m, p, r) -> r.detailedPrint(out, m, p, i, di));
	}
	
	/**
	 * prints a all unexpected results with their stack traces this just uses
	 * {@link #detailedPrintUnexpected(PrintStream, int, int)} with a
	 * {@code doubleIndention} which is
	 * <code>(indention * 2)}</code><br>
	 * the time will be in the first line, which will NOT be intended!
	 * 
	 * @param out
	 *                  the {@link PrintStream} on which the
	 *                  {@link Result#badResult() bad} {@link Result}s should be
	 *                  printed
	 * @param indention
	 *                  the normal indention
	 * 					
	 * @see #detailedPrintUnexpected(PrintStream, int, int)
	 */
	public void detailedPrintUnexpected(PrintStream out, int indention) { detailedPrintUnexpected(out, indention, indention << 1); }
	
	/**
	 * prints a all unexpected results with their stack traces<br>
	 * the time will be in the first line, which will NOT be intended!
	 * 
	 * @param out
	 *                       the {@link PrintStream} on which the
	 *                       {@link Result#badResult() bad} {@link Result}s should
	 *                       be printed
	 * @param indention
	 *                       the normal indention
	 * @param doubleIndented
	 *                       the complete indention for the doubled indented lines
	 */
	public void detailedPrintUnexpected(PrintStream out, int indention, int doubleIndented) {
		String indent;
		String dindent;
		{
			StringBuilder zw = new StringBuilder(Math.max(indention, doubleIndented));
			for (int i = 0; i < indention; i++) {
				zw.append(' ');
			}
			indent = zw.toString();
			for (int i = 0; i < doubleIndented; i++) {
				zw.append(' ');
			}
			if (doubleIndented < indention) {
				dindent = indent.substring(indention - doubleIndented);
			} else {
				dindent = zw.toString();
			}
		}
		out.println("time=" + (end - start) + "ms");
		this.forAll((m, p, r) -> { if (r.badResult()) { r.detailedPrint(out, m, p, indent, dindent); } });
	}
	
	/**
	 * creates a detailed string which contains all {@link Result}s of this
	 * {@link CheckResult} indented with spaces by the doubled given indention.<br>
	 * The given name will be set to the beginning and be indented with spaces by
	 * the given indention.<br>
	 * The given {@code counter} will be modified:<br>
	 * {@link TwoInts#a} will be incremented by the number of {@link Result}s in
	 * this {@link CheckResult}<br>
	 * {@link TwoInts#b} will be incremented by the number of
	 * {@link Result#getResult() good Result}s in this {@link CheckResult}
	 * 
	 * @param builder
	 *                  the {@link StringBuilder} to be filled with a detailed
	 *                  string representation of this {@link CheckResult}
	 * @param name
	 *                  the name of this {@link CheckResult}
	 * @param counter
	 *                  the {@link TwoInts} will be changed as above specified: the
	 *                  {@link Result}number will be added to {@link TwoInts#a a}
	 *                  and the {@link Result#getResult() good Result} number will
	 *                  be
	 *                  added to {@link TwoInts#b b}
	 * @param indention
	 *                  the indention in spaces of this {@link CheckResult} and the
	 *                  half indention in spaces for the methods of this
	 *                  {@link CheckResult}
	 */
	public void toString(StringBuilder builder, String name, TwoInts counter, int indention) {
		toString(builder, name, counter, indention, indention << 1);
	}
	
	/**
	 * creates a detailed string which contains all {@link Result}s of this
	 * {@link CheckResult} indented with spaces by the doubled given indention.<br>
	 * The given name will be set to the beginning and be indented with spaces by
	 * the given indention.<br>
	 * The given {@code counter} will be modified:<br>
	 * {@link TwoInts#a} will be incremented by the number of {@link Result}s in
	 * this {@link CheckResult}<br>
	 * {@link TwoInts#b} will be incremented by the number of
	 * {@link Result#getResult() good Result}s in this {@link CheckResult}
	 * <p>
	 * it works like:
	 * 
	 * <pre><code>
	 * {@link StringBuilder} zw = new {@link StringBuilder#StringBuilder() StringBuilder()};
	 * {@link #toString(StringBuilder, String, TwoInts, int) toString(zw, name, counter, indention)};
	 * return {@link StringBuilder#toString() zw.toString()};
	 * </code></pre>
	 * 
	 * @param name
	 *                  the name of this {@link CheckResult}
	 * @param counter
	 *                  the {@link TwoInts} will be changed as above specified: the
	 *                  {@link Result}number will be added to {@link TwoInts#a a}
	 *                  and the {@link Result#getResult() good Result} number will
	 *                  be
	 *                  added to {@link TwoInts#b b}
	 * @param indention
	 *                  the indention in spaces of this {@link CheckResult} and the
	 *                  half indention in spaces for the methods of this
	 *                  {@link CheckResult}
	 * 
	 * @return creates a detailed {@link String} representing this
	 *         {@link CheckResult}
	 * 
	 * @see #toString(StringBuilder, String, TwoInts, int)
	 */
	public String toString(String name, TwoInts counter, int indention) {
		StringBuilder str = new StringBuilder();
		toString(str, name, counter, indention);
		return str.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		toString(str, "<classname>", new TwoInts(), 0, 4);
		return str.toString();
	}
	
}

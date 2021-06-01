package de.hechler.patrick.zeugs.check;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public final class BigCheckResult {
	
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
		Map <Class <?>, CheckResult> ret = new HashMap <Class <?>, CheckResult>();
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
	
	public void forAll(TriConsumer <Class <?>, Method, Result> tc) {
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
	
	/**
	 * prints this {@link BigCheckResult} to the {@link PrintStream} {@link System#out}
	 * 
	 * @see {@link #print(PrintStream) print(System.out)}
	 */
	public void print() {
		print(System.out);
	}
	
	/**
	 * prints this {@link BigCheckResult} to the {@link PrintStream}
	 * 
	 * @param out
	 *            the {@link PrintStream} to be used to display this {@link BigCheckResult}
	 */
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

package de.hechler.patrick.zeugs.check.objects;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public final class BigCheckResult {
	
	private Map <String, Class <?>> classes = new HashMap <>();
	private Map <Class <?>, CheckResult> results = new HashMap <>();
	/**
	 * the time when this {@link BigCheckResult} was created
	 */
	public final long start = System.currentTimeMillis();
	/**
	 * the time when all {@link Checker} objects finished checking for this {@link BigCheckResult}.
	 */
	private long end;
	
	
	
	/**
	 * sets the end time of this {@link BigCheckResult} to {@link System#currentTimeMillis()}
	 * 
	 */
	void setEnd() {
		this.end = System.currentTimeMillis();
	}
	
	/**
	 * returns the end time of this {@link BigCheckResult}
	 * 
	 * @return the end time of this {@link BigCheckResult}
	 */
	public long getEnd() {
		return end;
	}
	
	/**
	 * returns the total time needed for this {@link BigCheckResult}
	 * 
	 * @return the total time needed for this {@link BigCheckResult}
	 */
	public long getTime() {
		return end - start;
	}
	
	/**
	 * puts a {@link CheckResult} in the {@link #results} {@link Map} and the {@link Class} with it's name in the {@link #classes} {@link Map}
	 * 
	 * @param cls
	 *            the class wich was checked
	 * @param result
	 *            the result which was created by checking the class
	 * @return
	 */
	CheckResult put(Class <?> cls, CheckResult result) {
		classes.put(cls.getName(), cls);
		return results.put(cls, result);
	}
	
	/**
	 * returns the {@link CheckResult} which belongs to the given {@link Class} or <code>null</code> if there is no such {@link CheckResult} in the {@link #results}
	 * 
	 * @param cls
	 *            the checked {@link Class}
	 * @return the {@link CheckResult}
	 */
	public CheckResult get(Class <?> cls) {
		return results.get(cls);
	}
	
	/**
	 * returns the {@link CheckResult} which belongs to the given {@link Class}, which is represented by it's full class name, or <code>null</code> if there is no matching
	 * {@link Class} in the {@link #classes}.
	 * 
	 * @param fullClassName
	 *            the full name of the checked class
	 * @return the {@link CheckResult}
	 * @see #get(Class)
	 */
	public CheckResult get(String fullClassName) {
		Class <?> cls = classes.get(fullClassName);
		if (cls == null) {
			return null;
		}
		return results.get(cls);
	}
	
	/**
	 * returns <code>true</code> if all {@link CheckResult} went {@link CheckResult#wentExpected()} and <code>false</code> if not.
	 * 
	 * @return <code>true</code> if all {@link CheckResult} went {@link CheckResult#wentExpected()} and <code>false</code> if not
	 * @see #wentUnexpected()
	 */
	public boolean wentExpected() {
		for (CheckResult cr : results.values()) {
			if (cr.wentUnexpected()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * returns <code>true</code> if any {@link CheckResult} went {@link CheckResult#wentUnexpected()} and <code>false</code> if not.
	 * 
	 * @return <code>true</code> if any {@link CheckResult} went {@link CheckResult#wentUnexpected()} and <code>false</code> if not
	 * @see #wentExpected()
	 */
	public boolean wentUnexpected() {
		for (CheckResult cr : results.values()) {
			if (cr.wentUnexpected()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * returns <code>true</code> if the {@link CheckResult} of the {@link Class} {@link CheckResult#wentExpected() went expected} and <code>false</code> if not.
	 * 
	 * @param cls
	 *            the checked class
	 * @return <code>true</code> if the {@link CheckResult} of the {@link Class} {@link CheckResult#wentExpected() went expected} and <code>false</code> if not
	 */
	public boolean wentExpected(Class <?> cls) {
		return results.get(cls).wentExpected();
	}
	
	/**
	 * returns <code>true</code> if the {@link CheckResult} of the {@link Class} {@link CheckResult#wentExpected() went expected} and <code>false</code> if not.
	 * 
	 * @param fullClassName
	 *            the checked class
	 * @return <code>true</code> if the {@link CheckResult} of the {@link Class} {@link CheckResult#wentExpected() went expected} and <code>false</code> if not
	 * @see #wentExpected(Class)
	 */
	public boolean wentExpected(String fullClassName) {
		Class <?> cls = classes.get(fullClassName);
		return results.get(cls).wentExpected();
	}
	
	/**
	 * returns a {@link Map} of all {@link CheckResult}, which went {@link CheckResult#wentUnexpected() unexpected}.
	 * 
	 * @return a {@link Map} of all {@link CheckResult}, which went {@link CheckResult#wentUnexpected() unexpected}
	 */
	public Map <Class <?>, CheckResult> allUnexpected() {
		Map <Class <?>, CheckResult> ret = new HashMap <Class <?>, CheckResult>();
		results.forEach((c, r) -> {
			if ( !r.wentExpected()) {
				ret.put(c, r);
			}
		});
		return ret;
	}
	
	/**
	 * performs the given with all {@link CheckResult}s.
	 * 
	 * @param act
	 *            the action to be performed
	 * @see Map#forEach(BiConsumer)
	 */
	public void forAllCheckResults(BiConsumer <Class <?>, CheckResult> act) {
		results.forEach(act);
	}
	
	/**
	 * performs the given with all {@link CheckResult}s, which went {@link CheckResult#wentUnexpected() unexpected}.
	 * 
	 * @param act
	 *            the action to be performed
	 */
	public void forAllUnexpectedCheckResults(BiConsumer <Class <?>, CheckResult> act) {
		results.forEach((c, r) -> {
			if ( !r.wentExpected()) {
				act.accept(c, r);
			}
		});
	}
	
	/**
	 * performs the given with all {@link Result}s.
	 * 
	 * @param act
	 *            the action to be performed
	 */
	public void forAll(TriConsumer <Class <?>, Method, Result> act) {
		results.forEach((c, r) -> r.forAll((m, t) -> act.accept(c, m, t)));
	}
	
	/**
	 * performs the given with all {@link Result}s, which went {@link Result#badResult() unexpected}.
	 * 
	 * @param act
	 *            the action to be performed
	 */
	public void forAllUnexpected(TriConsumer <Class <?>, Method, Throwable> act) {
		results.forEach((c, r) -> r.forAllUnexpected((m, t) -> act.accept(c, m, t)));
	}
	
	public static interface TriConsumer <A, B, C> {
		
		void accept(A a, B b, C c);
		
	}
	
	/**
	 * returns a checked {@link Class} from the {@link #classes}, which has the given full class name or <code>null</code>.
	 * 
	 * @param fullClassName
	 *            the full class name
	 * @return a checked {@link Class} from the {@link #classes}, which has the given full class name or <code>null</code>
	 */
	public Class <?> getClass(String fullClassName) {
		return classes.get(fullClassName);
	}
	
	/**
	 * prints this {@link BigCheckResult} on the {@link System#out} stream
	 * 
	 * @see #print(PrintStream)
	 */
	public void print() {
		print(System.out);
	}
	
	public void print(PrintStream out) {
		List <String> prints = new ArrayList <>();
		TwoInts ii = new TwoInts();
		results.forEach((c, r) -> {
			prints.add(r.toString(c.getSimpleName(), ii, 4));
		});
		out.println("RESULT: " + ii.b + '/' + ii.a + " -> " + (ii.a == ii.b ? "good" : "bad"));
		prints.forEach(s -> out.print(s));
	}
	
	public void print(PrintStream out, int indention) {
		List <String> prints = new ArrayList <>();
		TwoInts ii = new TwoInts();
		results.forEach((c, r) -> {
			prints.add(r.toString(c.getSimpleName(), ii, indention));
		});
		out.println("RESULT: " + ii.b + '/' + ii.a + " -> " + (ii.a == ii.b ? "good" : "bad"));
		prints.forEach(s -> out.print(s));
	}
	
	public void detailedPrint() {
		detailedPrint(System.out, 4, 8);
	}
	
	public void detailedPrint(PrintStream out) {
		detailedPrint(out, 4, 8);
	}
	
	public void detailedPrint(PrintStream out, int indention) {
		detailedPrint(out, indention, indention << 1);
	}
	
	public void detailedPrint(PrintStream out, int indention, int doubleIndented) {
		out.println("detailed big check result: time=" + (end - start) + "ms");
		this.forAllCheckResults((cls, r) -> {
			out.print("results in class " + cls.getName() + ": ");
			r.detailedPrint(out, indention, doubleIndented);
		});
	}
	
	
	public void detailedPrintUnexpected(PrintStream out) {
		detailedPrintUnexpected(out, 4, 8);
	}
	
	public void detailedPrintUnexpected(PrintStream out, int indention) {
		detailedPrintUnexpected(out, indention, indention << 1);
	}
	
	public void detailedPrintUnexpected(PrintStream out, int indention, int doubleIndented) {
		out.println("detailed big check result: time=" + (end - start) + "ms");
		this.forAllUnexpectedCheckResults((cls, r) -> {
			out.print("bad results in class " + cls.getName() + ": ");
			r.detailedPrintUnexpected(out, indention, doubleIndented);
		});
	}
	
	public int checkedResultCount() {
		return this.results.size();
	}
	
	@Override
	public String toString() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		print(new PrintStream(baos));
		byte[] bytes = baos.toByteArray();
		return new String(bytes);// default charset on both sides
	}
	
}

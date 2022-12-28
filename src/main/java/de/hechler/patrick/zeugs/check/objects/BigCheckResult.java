package de.hechler.patrick.zeugs.check.objects;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import de.hechler.patrick.zeugs.check.interfaces.TriConsumer;
import de.hechler.patrick.zeugs.check.interfaces.TwoVals;

/**
 * this class saves the {@link CheckResult} objects of multiple class checks.
 * <p>
 * to get the {@link CheckResult} of a {@link Class} the {@link #get(Class)}
 * method can be used.
 * <p>
 * in addition to all {@link CheckResult} objects also the execution time is
 * saved.<br>
 * to get the execution time {@link #getTime()} can be used.<br>
 * to get end start of all the {@link BigCheckResult} {@link #start} can be
 * accessed.<br>
 * to get end time of all class checks {@link #end} can be accessed.
 * 
 * @author Patrick
 */
public final class BigCheckResult {
	
	/**
	 * saves all checked classes refereed with their full names.
	 */
	private final Map<String, Class<?>>      classes;
	/**
	 * saves all {@link CheckResult} objects of this {@link BigCheckResult}
	 */
	private final Map<Class<?>, CheckResult> results;
	/**
	 * the time when the class checks started
	 */
	public final long                        start;
	/**
	 * the time when all class checks finished
	 */
	public final long                        end;
	
	/**
	 * creates a new {@link BigCheckResult} with the given values.
	 * <p>
	 * to build the maps {@link #classes} and {@link #results} the
	 * {@link #put(Map, Map, Class, CheckResult)} method is recommended.
	 * 
	 * @param classes
	 *                the map containing all checked {@link Class} objects refereed
	 *                by their names
	 * @param results
	 *                the map containing the {@link CheckResult} objects refereed by
	 *                the {@link Class} objects
	 * @param start
	 *                the time when the {@link BigChecker} started to execute the
	 *                checks
	 * @param end
	 *                the time wen the {@link BigChecker} was finished with the
	 *                checks
	 */
	public BigCheckResult(Map<String, Class<?>> classes, Map<Class<?>, CheckResult> results, long start, long end) {
		this.classes = classes;
		this.results = results;
		this.start   = start;
		this.end     = end;
	}
	
	/**
	 * puts a {@link CheckResult} in the {@link #results} {@link Map} and the
	 * {@link Class} with it's name in the {@link #classes} {@link Map}
	 * 
	 * @param classes
	 *                the map used to save the checked classes refereed by their
	 *                full names.
	 * @param results
	 *                the map used to save the results of the checked classes
	 * @param cls
	 *                the class wich was checked
	 * @param result
	 *                the result which was created by checking the class
	 * 				
	 * @return the old {@link CheckResult} associated with the given {@link Class}
	 */
	public static CheckResult put(Map<String, Class<?>> classes, Map<Class<?>, CheckResult> results, Class<?> cls, CheckResult result) {
		classes.put(cls.getName(), cls);
		if (cls.getCanonicalName() != null) { classes.put(cls.getCanonicalName(), cls); }
		return results.put(cls, result);
	}
	
	/**
	 * returns the total time needed for this {@link BigCheckResult}
	 * 
	 * @return the total time needed for this {@link BigCheckResult}
	 */
	public long getTime() { return end - start; }
	
	/**
	 * returns the {@link CheckResult} which belongs to the given {@link Class} or
	 * <code>null</code> if there is no such {@link CheckResult} in the
	 * {@link #results}
	 * 
	 * @param cls
	 *            the checked {@link Class}
	 * 			
	 * @return the {@link CheckResult}
	 */
	public CheckResult get(Class<?> cls) { return results.get(cls); }
	
	/**
	 * returns the {@link CheckResult} which belongs to the given {@link Class},
	 * which is represented by it's full class name, or <code>null</code> if there
	 * is no matching {@link Class} in the
	 * {@link #classes}.
	 * 
	 * @param fullClassName
	 *                      the full name of the checked class
	 * 						
	 * @return the {@link CheckResult}
	 * 						
	 * @see #get(Class)
	 */
	public CheckResult get(String fullClassName) {
		Class<?> cls = classes.get(fullClassName);
		if (cls == null) { return null; }
		return results.get(cls);
	}
	
	/**
	 * returns <code>true</code> if all {@link CheckResult} went
	 * {@link CheckResult#wentExpected()} and <code>false</code> if not.
	 * 
	 * @return <code>true</code> if all {@link CheckResult} went
	 *         {@link CheckResult#wentExpected()} and <code>false</code> if not
	 * 		
	 * @see #wentUnexpected()
	 */
	public boolean wentExpected() {
		for (CheckResult cr : results.values()) {
			if (cr.wentUnexpected()) { return false; }
		}
		return true;
	}
	
	/**
	 * returns <code>true</code> if any {@link CheckResult} went
	 * {@link CheckResult#wentUnexpected()} and <code>false</code> if not.
	 * 
	 * @return <code>true</code> if any {@link CheckResult} went
	 *         {@link CheckResult#wentUnexpected()} and <code>false</code> if not
	 * 		
	 * @see #wentExpected()
	 */
	public boolean wentUnexpected() {
		for (CheckResult cr : results.values()) {
			if (cr.wentUnexpected()) { return true; }
		}
		return false;
	}
	
	/**
	 * returns <code>true</code> if the {@link CheckResult} of the {@link Class}
	 * {@link CheckResult#wentExpected() went expected} and <code>false</code> if
	 * not.
	 * 
	 * @param cls
	 *            the checked class
	 * 			
	 * @return <code>true</code> if the {@link CheckResult} of the {@link Class}
	 *         {@link CheckResult#wentExpected() went expected} and
	 *         <code>false</code> if not
	 */
	public boolean wentExpected(Class<?> cls) { return results.get(cls).wentExpected(); }
	
	/**
	 * returns <code>true</code> if the {@link CheckResult} of the {@link Class}
	 * {@link CheckResult#wentExpected() went expected} and <code>false</code> if
	 * not.
	 * 
	 * @param fullClassName
	 *                      the checked class
	 * 						
	 * @return <code>true</code> if the {@link CheckResult} of the {@link Class}
	 *         {@link CheckResult#wentExpected() went expected} and
	 *         <code>false</code> if not
	 * 		
	 * @see #wentExpected(Class)
	 */
	public boolean wentExpected(String fullClassName) {
		Class<?> cls = classes.get(fullClassName);
		return results.get(cls).wentExpected();
	}
	
	/**
	 * returns a {@link Map} of all {@link CheckResult}, which went
	 * {@link CheckResult#wentUnexpected() unexpected}.
	 * 
	 * @return a {@link Map} of all {@link CheckResult}, which went
	 *         {@link CheckResult#wentUnexpected() unexpected}
	 */
	public Map<Class<?>, CheckResult> allUnexpected() {
		Map<Class<?>, CheckResult> ret = new HashMap<>();
		results.forEach((c, r) -> { if (!r.wentExpected()) { ret.put(c, r); } });
		return ret;
	}
	
	/**
	 * performs the given with all {@link CheckResult}s.
	 * 
	 * @param act
	 *            the action to be performed
	 * 			
	 * @see Map#forEach(BiConsumer)
	 */
	public void forAllCheckResults(BiConsumer<Class<?>, CheckResult> act) { results.forEach(act); }
	
	/**
	 * performs the given with all {@link CheckResult}s, which went
	 * {@link CheckResult#wentUnexpected() unexpected}.
	 * 
	 * @param act
	 *            the action to be performed
	 */
	public void forAllUnexpectedCheckResults(BiConsumer<Class<?>, CheckResult> act) {
		results.entrySet().stream().filter(e -> e.getValue().wentUnexpected()).forEach(e -> act.accept(e.getClass(), e.getValue()));
	}
	
	/**
	 * performs the given with all {@link Result}s.
	 * 
	 * @param act
	 *            the action to be performed
	 */
	public void forAll(TriConsumer<Class<?>, TwoVals<Method, Object[]>, Result> act) {
		results.forEach((c, r) -> r.forAll((m, ps, res) -> act.accept(c, new TwoValues<>(m, ps), res)));
	}
	
	/**
	 * performs the given with all {@link Result}s, which went
	 * {@link Result#badResult() unexpected}.
	 * 
	 * @param act
	 *            the action to be performed
	 */
	public void forAllUnexpected(TriConsumer<Class<?>, TwoVals<Method, Object[]>, Result> act) {
		results.forEach((c, r) -> r.forAllExpected((m, ps, res) -> act.accept(c, new TwoValues<>(m, ps), res)));
	}
	
	/**
	 * returns a checked {@link Class} from the {@link #classes}, which has the
	 * given full class name or <code>null</code>.
	 * 
	 * @param fullClassName
	 *                      the full class name
	 * 						
	 * @return a checked {@link Class} from the {@link #classes}, which has the
	 *         given full class name or <code>null</code>
	 */
	public Class<?> getClass(String fullClassName) { return classes.get(fullClassName); }
	
	/**
	 * prints this {@link BigCheckResult} on the {@link System#out} stream
	 * 
	 * @see #print(PrintStream)
	 */
	public void print() { print(System.out); }
	
	/**
	 * prints this {@link BigCheckResult} on the given {@link PrintStream}
	 * {@code out}
	 * 
	 * @param out
	 *            the {@link PrintStream} on which this {@link BigCheckResult}
	 *            should be printed
	 */
	public void print(PrintStream out) {
		List<String> prints = new ArrayList<>();
		TwoInts      ii     = new TwoInts();
		results.forEach((c, r) -> prints.add(r.toString(c.getSimpleName(), ii, 4)));
		out.println("RESULT: " + ii.b + '/' + ii.a + " -> " + (ii.a == ii.b ? "good" : "bad"));
		prints.forEach(out::print);
	}
	
	/**
	 * prints this {@link BigCheckResult} on the given {@link PrintStream}
	 * {@code out} and the given {@code indention} in spaces (<code>' '</code>)
	 * 
	 * @param out
	 *                  the {@link PrintStream} on which this {@link BigCheckResult}
	 *                  should be printed
	 * @param indention
	 *                  the indention in spaces
	 */
	public void print(PrintStream out, int indention) {
		List<String> prints = new ArrayList<>();
		TwoInts      ii     = new TwoInts();
		results.forEach((c, r) -> prints.add(r.toString(c.getSimpleName(), ii, indention)));
		out.println("RESULT: " + ii.b + '/' + ii.a + " -> " + (ii.a == ii.b ? "good" : "bad"));
		prints.forEach(out::print);
	}
	
	/**
	 * prints this {@link BigCheckResult} on the {@link System#out} stream in a
	 * detailed format
	 * 
	 * @see #detailedPrint(PrintStream)
	 */
	public void detailedPrint() { detailedPrint(System.out, 4, 8); }
	
	/**
	 * prints this {@link BigCheckResult} on the given stream {@code out} in a
	 * detailed format
	 * 
	 * @param out
	 *            the print stream which should be used to print this
	 *            {@link BigCheckResult}
	 */
	public void detailedPrint(PrintStream out) { detailedPrint(out, 4, 8); }
	
	/**
	 * prints this {@link BigCheckResult} on the given stream {@code out} in a
	 * detailed format and the given indention
	 * 
	 * @param out
	 *                  the print stream which should be used to print this
	 *                  {@link BigCheckResult}
	 * @param indention
	 *                  the indention in spaces {@code ' '}
	 */
	public void detailedPrint(PrintStream out, int indention) { detailedPrint(out, indention, indention << 1); }
	
	/**
	 * prints this {@link BigCheckResult} on the given stream {@code out} in a
	 * detailed format and the given indentions
	 * 
	 * @param out
	 *                       the print stream which should be used to print this
	 *                       {@link BigCheckResult}
	 * @param indention
	 *                       the indention in spaces {@code ' '}
	 * @param doubleIndented
	 *                       the indention in spaces {@code ' '} for double intended
	 *                       lines
	 */
	public void detailedPrint(PrintStream out, int indention, int doubleIndented) {
		int cnt = 0;
		for (CheckResult cr : this.results.values()) {
			if (cr.wentExpected()) { cnt++; }
		}
		out.println("detailed big check result: time=" + (end - start) + "ms  " + cnt + "/" + this.results.size());
		this.forAllCheckResults((cls, r) -> {
			out.print("results in class " + cls.getName() + ": ");
			r.detailedPrint(out, indention, doubleIndented);
		});
	}
	
	/**
	 * prints all unexpected checks in a detailed format on the given stream
	 * {@code out}
	 * 
	 * @param out
	 *            the stream on which the unexpected checks should be printed
	 */
	public void detailedPrintUnexpected(PrintStream out) { detailedPrintUnexpected(out, 4, 8); }
	
	/**
	 * prints all unexpected checks in a detailed format on the given stream
	 * {@code out}
	 * 
	 * @param out
	 *                  the stream on which the unexpected checks should be printed
	 * @param indention
	 *                  the indention in spaces {@code ' '}
	 */
	public void detailedPrintUnexpected(PrintStream out, int indention) { detailedPrintUnexpected(out, indention, indention << 1); }
	
	/**
	 * prints all unexpected checks in a detailed format on the given stream
	 * {@code out}
	 * 
	 * @param out
	 *                       the stream on which the unexpected checks should be
	 *                       printed
	 * @param indention
	 *                       the indention in spaces {@code ' '}
	 * @param doubleIndented
	 *                       the indention in spaces {@code ' '} for double intended
	 *                       lines
	 */
	public void detailedPrintUnexpected(PrintStream out, int indention, int doubleIndented) {
		out.println("detailed big check result: time=" + (end - start) + "ms");
		this.forAllUnexpectedCheckResults((cls, r) -> {
			out.print("bad results in class " + cls.getName() + ": ");
			r.detailedPrintUnexpected(out, indention, doubleIndented);
		});
	}
	
	/**
	 * returns the number of {@link CheckResult} objects saved by this
	 * {@link BigCheckResult}.
	 * 
	 * @return the number of {@link CheckResult} objects saved by this
	 *         {@link BigCheckResult}.
	 */
	public int checkedResultCount() { return this.results.size(); }
	
	@Override
	public String toString() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		print(new PrintStream(baos, true));
		byte[] bytes = baos.toByteArray();
		return new String(bytes);// default charset on both sides
	}
	
}

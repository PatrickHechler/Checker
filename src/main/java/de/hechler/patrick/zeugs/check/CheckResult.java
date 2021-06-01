package de.hechler.patrick.zeugs.check;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;

public final class CheckResult {
	
	private Map <String, Method> methods = new HashMap <>();
	private Map <Method, Result> results = new HashMap <>();
	
	
	
	void set(Method m, Result value) {
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
		return this.results.get(m).goodResult();
	}
	
	public boolean wentExpected(Method m) throws NoSuchElementException {
		if ( !this.results.containsKey(m)) throw new NoSuchElementException("missing method '" + m.getName() + "' in my methods: " + this.methods.keySet());
		return this.results.get(m).goodResult();
	}
	
	public boolean wentExpected() {
		for (Result r : this.results.values()) {
			if (r.badResult()) return false;
		}
		return true;
	}
	
	public int cehckedCount() {
		return this.results.size();
	}
	
	public Result getResult(String methodName) throws NoSuchElementException {
		Method m = methods.get(methodName);
		if (m == null) throw new NoSuchElementException("missing method '" + methodName + "' in my methods: " + this.methods.keySet());
		return results.get(m);
	}
	
	public Result getResult(Method m) throws NoSuchElementException {
		if ( !results.containsKey(m)) throw new NoSuchElementException("missing method '" + m.getName() + "' in my methods: " + this.methods.keySet());
		return results.get(m);
	}
	
	public Map <Method, Throwable> allUnexpected() {
		Map <Method, Throwable> ret = new HashMap <Method, Throwable>();
		results.forEach((m, r) -> {
			if (r.badResult()) {
				ret.put(m, r.getErr());
			}
		});
		return ret;
	}
	
	public void forAll(BiConsumer <Method, Result> c) {
		results.forEach(c);
	}
	
	public void forAllUnexpected(BiConsumer <Method, Throwable> c) {
		results.forEach((m, t) -> {
			if (t.badResult()) c.accept(m, t.getErr());
		});
	}
	
	public void print() {
		print(System.out);
	}
	
	public void print(final PrintStream out) {
		StringBuilder str = new StringBuilder(System.lineSeparator());
		IntInt cnt = new IntInt();
		this.results.forEach((m, r) -> {
			boolean b = (r.goodResult());
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
	
	String printStr(String name, IntInt ii) {
		StringBuilder str = new StringBuilder();
		IntInt cnt = new IntInt();
		this.results.forEach((m, r) -> {
			boolean b = (r.goodResult());
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

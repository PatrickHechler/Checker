package de.hechler.patrick.zeugs.check.objects;

import static de.hechler.patrick.zeugs.check.Assert.assertEquals;
import static de.hechler.patrick.zeugs.check.Assert.assertFalse;
import static de.hechler.patrick.zeugs.check.Assert.assertNotEquals;
import static de.hechler.patrick.zeugs.check.Assert.assertTrue;

import java.lang.reflect.Method;

import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;
import de.hechler.patrick.zeugs.check.anotations.MethodParam;
import de.hechler.patrick.zeugs.check.anotations.ParamCreater;
import de.hechler.patrick.zeugs.check.anotations.Start;

@CheckClass
@SuppressWarnings("static-method")
public class CheckerCheckingChecker extends Checker {
	
	@Start
	private void start(@MethodParam Method met) { LogHandler.LOG.finer(() -> met.getName() + " start"); }
	
	@Start
	private void end(@MethodParam Method met) { LogHandler.LOG.finer(() -> met.getName() + " finish"); }
	
	@Check
	public void resultChecker() {
		CheckResult r = new SubChecker1().get();
		assertFalse(r.wentExpected());
		assertTrue(r.checked("empty"));
		assertTrue(r.wentExpected("empty"));
		assertTrue(r.wentExpected("good"));
		assertFalse(r.wentExpected("bad"));
		assertEquals(3, r.cehckedCount());
		
		r = new SubChecker2().get();
		assertTrue(r.wentExpected());
		assertTrue(r.checked("good"));
		assertFalse(r.checked("bad"));
		assertTrue(r.wentExpected("empty"));
		assertTrue(r.wentExpected("good"));
		assertFalse(r.checked("bad"));
		assertTrue(r.wentExpected("goodBad"));
		assertEquals(3, r.cehckedCount());
		
		r = new SubChecker3().get();
		assertFalse(r.wentExpected());
		assertFalse(r.wentExpected("baadGood"));
		assertFalse(r.wentExpected("bad"));
		assertEquals(2, r.cehckedCount());
		
		r = new SubChecker4().get();
		assertTrue(r.wentExpected());
		assertEquals(0, r.cehckedCount());
	}
	
	public class SubChecker1 extends Checker {
		
		@Check
		public void empty() {}
		
		@Check
		public void good() {
			assertEquals(0, 0);
			assertEquals(-1l, -1l);
		}
		
		@Check
		public void bad() {
			assertEquals(0, 0);
			assertEquals(-2l, -1l);
		}
		
	}
	
	public class SubChecker2 extends Checker {
		
		@Check
		public void empty() {}
		
		@Check
		public void good() {
			assertEquals(0, 0);
			assertEquals(-1l, -1l);
		}
		
		@Check
		public void goodBad() {
			assertEquals(0, 0);
			assertNotEquals(-2l, -1l);
		}
		
	}
	
	public class SubChecker3 extends Checker {
		
		@Check
		public void baadGood() {
			assertEquals(new Object(), new Object());
			assertEquals(-1l, -1l);
		}
		
		@Check
		public void bad() {
			assertEquals(0, 0);
			assertEquals(-2l, -1l);
		}
		
	}
	
	public class SubChecker4 extends Checker {
		
		@Check(disabled = true)
		public void empty() {}
		
		@Check(disabled = true)
		public void good() {
			assertEquals(0, 0);
			assertEquals(-1l, -1l);
		}
		
		@Check(disabled = true)
		public void bad() {
			assertEquals(0, 0);
			assertEquals(-2l, -1l);
		}
		
		@Check(disabled = true)
		public void goodBad() {
			assertEquals(0, 0);
			assertNotEquals(-2l, -1l);
		}
		
	}
	
	@Check
	public void checkerCreateCheck() {
		Checker     c   = new Checker(this.new InnerChecker());
		CheckResult res = c.get();
		assertTrue(res.wentExpected());
		res = Checker.check(SubFormerWrongChecker.class);
		assertTrue(res.wentExpected());
		Checker.check(InnerChecker.class);
	}
	
	public static class SubFormerWrongChecker extends Checker {
		
		@Check
		private void name(@ParamCreater(method = "voidValue") Object param) {}
		
		@SuppressWarnings("unused")
		private void voidValue() {}
		
	}
	
	private class InnerChecker {
		
		@Start
		private InnerChecker() {}
		
		@Check
		private void name() {}
		
	}
	
}

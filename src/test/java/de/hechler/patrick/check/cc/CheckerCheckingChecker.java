package de.hechler.patrick.check.cc;

import de.hechler.patrick.zeugs.check.Checker;
import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;

@CheckClass
public class CheckerCheckingChecker extends Checker {
	
	@Check
	public void resultChecker() {
		System.out.println("resultChecker start");
		
		CheckResult r = new SubChecker1().result();
		assertFalse(r.wentExpected());
		assertTrue(r.checked("empty"));
		assertTrue(r.wentExpected("empty"));
		assertTrue(r.wentExpected("good"));
		assertFalse(r.wentExpected("bad"));
		assertEquals(3, r.cehckedCount());
		
		r = new SubChecker2().result();
		assertTrue(r.wentExpected());
		assertTrue(r.checked("good"));
		assertFalse(r.checked("bad"));
		assertTrue(r.wentExpected("empty"));
		assertTrue(r.wentExpected("good"));
		assertFalse(r.checked("bad"));
		assertTrue(r.wentExpected("goodBad"));
		assertEquals(3, r.cehckedCount());
		
		r = new SubChecker3().result();
		assertFalse(r.wentExpected());
		assertFalse(r.wentExpected("baadGood"));
		assertFalse(r.wentExpected("bad"));
		assertEquals(2, r.cehckedCount());
		
		r = new SubChecker4().result();
		assertTrue(r.wentExpected());
		assertEquals(0, r.cehckedCount());
		
		System.out.println("             finish rc");
	}
	
	public class SubChecker1 extends Checker {
		
		@Check
		public void empty() {
		}
		
		@Check
		public void good() {
			assertEquals(0, 0);
			assertEquals( -1l, -1l);
		}
		
		@Check
		public void bad() {
			assertEquals(0, 0);
			assertEquals( -2l, -1l);
		}
		
	}
	
	public class SubChecker2 extends Checker {
		
		@Check
		public void empty() {
		}
		
		@Check
		public void good() {
			assertEquals(0, 0);
			assertEquals( -1l, -1l);
		}
		
		@Check
		public void goodBad() {
			assertEquals(0, 0);
			assertNotEquals( -2l, -1l);
		}
		
	}
	
	public class SubChecker3 extends Checker {
		
		@Check
		public void baadGood() {
			assertEquals(new Object(), new Object());
			assertEquals( -1l, -1l);
		}
		
		@Check
		public void bad() {
			assertEquals(0, 0);
			assertEquals( -2l, -1l);
		}
		
	}
	
	public class SubChecker4 extends Checker {
		
		@Check(disabled = true)
		public void empty() {
		}
		
		@Check(disabled = true)
		public void good() {
			assertEquals(0, 0);
			assertEquals( -1l, -1l);
		}
		
		@Check(disabled = true)
		public void bad() {
			assertEquals(0, 0);
			assertEquals( -2l, -1l);
		}
		
		@Check(disabled = true)
		public void goodBad() {
			assertEquals(0, 0);
			assertNotEquals( -2l, -1l);
		}
		
	}
	
	@Check
	public void compileErrors() {
		System.out.println("compileErrors start");
		new SubFormerWrongChecker().run();//former compile error
		assertThrowsAny(() -> Checker.check(SubNotChecker.class));
		System.out.println("              finish ce");
	}
	
	public class SubFormerWrongChecker extends Checker {@Check private void name(Object param) {} }
	public class SubNotChecker {@Check private void name() {} }
	
}

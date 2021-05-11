package de.hechler.patrick.check.cc;

import de.hechler.patrick.zeugs.check.Checker;
import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;

@CheckClass
public class AssertionsChecker extends Checker {
	
	@Check
	public void ckeckAssert() {
		CheckResult r = new SubAssertionChecker().result();
		assertFalse(r.wentExpected("badAssert"));
		Result err = r.getResult("badAssert");
		assertNotNull(err);
		assertTrue(err.badResult());
		assertExactClass(AssertionError.class, err.getErr());
		assertEquals("fail message", err.getErr().getMessage());
		assertThrows("spezialized fail message", AssertionError.class, () -> {
			assert false : "spezialized fail message";
		});
	}
	
	public static class SubAssertionChecker extends Checker {
		
		@Check
		public void badAssert() {
			assert false : "fail message";
		}
		
	}
	
}

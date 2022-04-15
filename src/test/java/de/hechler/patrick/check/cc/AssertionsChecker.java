package de.hechler.patrick.check.cc;

import static de.hechler.patrick.zeugs.check.Assert.assertEquals;
import static de.hechler.patrick.zeugs.check.Assert.assertExactClass;
import static de.hechler.patrick.zeugs.check.Assert.assertFalse;
import static de.hechler.patrick.zeugs.check.Assert.assertNotNull;
import static de.hechler.patrick.zeugs.check.Assert.assertThrows;
import static de.hechler.patrick.zeugs.check.Assert.assertTrue;

import de.hechler.patrick.zeugs.check.CheckResult;
import de.hechler.patrick.zeugs.check.Checker;
import de.hechler.patrick.zeugs.check.Result;
import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;

@CheckClass
public class AssertionsChecker {
	
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

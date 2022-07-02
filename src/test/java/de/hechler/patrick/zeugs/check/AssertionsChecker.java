package de.hechler.patrick.zeugs.check;

import static de.hechler.patrick.zeugs.check.Assert.assertEquals;
import static de.hechler.patrick.zeugs.check.Assert.assertExactClass;
import static de.hechler.patrick.zeugs.check.Assert.assertFalse;
import static de.hechler.patrick.zeugs.check.Assert.assertNotNull;
import static de.hechler.patrick.zeugs.check.Assert.assertThrows;
import static de.hechler.patrick.zeugs.check.Assert.assertTrue;

import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;
import de.hechler.patrick.zeugs.check.objects.CheckResult;
import de.hechler.patrick.zeugs.check.objects.Checker;
import de.hechler.patrick.zeugs.check.objects.Result;

@CheckClass
public class AssertionsChecker {
	
	@Check
	public void ckeckAssert() {
		CheckResult r = new SubAssertionChecker().get();
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

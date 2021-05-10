package de.hechler.patrick.check.cc;

import de.hechler.patrick.zeugs.check.Checker;
import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;

@CheckClass
public class AssertionsChecker extends Checker {
	
	@Check
	public void ckeckAssert() {
		Throwable err = new SubAssertionChecker().result().getException("badAssert");
		assertNotNull(err);
		assertExactClass(AssertionError.class, err);
		assertEquals("fail message", err.getMessage());
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

package de.hechler.patrick.zeugs.check.objects;

import static de.hechler.patrick.zeugs.check.Assert.assertFalse;
import static de.hechler.patrick.zeugs.check.Assert.assertTrue;
import static de.hechler.patrick.zeugs.check.Assert.fail;

import java.lang.reflect.Method;

import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;

@CheckClass public class OverwriteCheckingChecker extends Checker {
	
	@Check
	private void doCheck() throws NoSuchMethodException, SecurityException {
		CheckResult cr = Checker.check(SubChecker.class);
		assertFalse(cr.wentExpected());
		assertTrue(cr.wentUnexpected("overwrittenFormerPrivateBadCheck"));
		Method met = SubChecker.class.getDeclaredMethod("overwrittenFormerPrivateBadCheck");
		assertTrue(cr.wentExpected(met));
		met = SubChecker.class.getDeclaredMethod("overwrittenFormerPublicBadCheck");
		assertTrue(cr.wentExpected(met));
		met = SuperChecker.class.getDeclaredMethod("overwrittenFormerPrivateBadCheck");
		assertFalse(cr.wentExpected(met));
		met = SuperChecker.class.getDeclaredMethod("overwrittenFormerPublicBadCheck");
		assertTrue(cr.wentExpected(met));
		assertTrue(cr.wentExpected("goodCheck"));
		assertTrue(cr.wentExpected(met));
		assertTrue(cr.wentExpected("overwrittenFormerPublicBadCheck"));
	}
	
	
	static class SubChecker extends SuperChecker {
		
		@Check
		private void overwrittenFormerPrivateBadCheck() {
		}
		
		@Check
		@Override
		public void overwrittenFormerPublicBadCheck() {
		}
		
	}
	
	
	static class SuperChecker extends Checker {
		
		@Check
		private void goodCheck() {
		}
		
		@Check
		private void overwrittenFormerPrivateBadCheck() {
			fail();
		}
		
		@Check
		public void overwrittenFormerPublicBadCheck() {
			fail();
		}
		
	}
	
}

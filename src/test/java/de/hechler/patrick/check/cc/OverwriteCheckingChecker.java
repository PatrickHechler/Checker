package de.hechler.patrick.check.cc;

import static de.hechler.patrick.zeugs.check.Assert.assertFalse;
import static de.hechler.patrick.zeugs.check.Assert.assertTrue;
import static de.hechler.patrick.zeugs.check.Assert.fail;

import java.lang.reflect.Method;

import de.hechler.patrick.zeugs.check.CheckResult;
import de.hechler.patrick.zeugs.check.Checker;
import de.hechler.patrick.zeugs.check.anotations.Check;

public class OverwriteCheckingChecker extends Checker {
	
	@Check
	private void doCheck() throws NoSuchMethodException, SecurityException {
		CheckResult cr = Checker.check(SubChecker.class);
		assertFalse(cr.wentExpected());
		Method met = SubChecker.class.getMethod("overwrittenFormerPrivateBadCheck");
		assertTrue(cr.wentExpected(met));
		met = SubChecker.class.getMethod("overwrittenFormerPublicBadCheck");
		assertTrue(cr.wentExpected(met));
		met = SuperChecker.class.getMethod("overwrittenFormerPrivateBadCheck");
		assertFalse(cr.wentExpected(met));
		met = SuperChecker.class.getMethod("overwrittenFormerPublicBadCheck");
		assertTrue(cr.wentExpected(met));
		assertTrue(cr.wentExpected("goodCheck"));
		assertTrue(cr.wentExpected(met));
		assertTrue(cr.wentExpected("overwrittenFormerPublicBadCheck"));
	}
	
}


class SubChecker extends SuperChecker {
	
	@Check
	private void overwrittenFormerPrivateBadCheck() {
	}
	
	@Check
	@Override
	public void overwrittenFormerPublicBadCheck() {
	}
	
}


class SuperChecker extends Checker {
	
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

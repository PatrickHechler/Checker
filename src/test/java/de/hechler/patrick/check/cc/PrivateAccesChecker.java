package de.hechler.patrick.check.cc;

import de.hechler.patrick.zeugs.check.Assert;
import de.hechler.patrick.zeugs.check.Checker;
import de.hechler.patrick.zeugs.check.Result;
import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;


@CheckClass
public class PrivateAccesChecker {
	
	@Check
	private void checkPrivateAcces() {
		Result r = Checker.check(PrivateSubChecker.class).getResult("string");
		Assert.assertTrue(r.goodResult());
		Assert.assertEquals("hello world", r.getResult());
	}
	
	private static class PrivateSubChecker {
		
		private PrivateSubChecker() {
		}
		
		@Check
		private String string() {
			return "hello world";
		}
		
	}
	
}

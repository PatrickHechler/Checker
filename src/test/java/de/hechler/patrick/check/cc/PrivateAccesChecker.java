package de.hechler.patrick.check.cc;

import de.hechler.patrick.zeugs.check.Checker;
import de.hechler.patrick.zeugs.check.Checker.Result;
import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;


@CheckClass
public class PrivateAccesChecker {
	
	@Check
	private void checkPrivateAcces() {
		Result r = Checker.check(PrivateSubChecker.class).getResult("string");
		Checker.assertTrue(r.goodResult());
		Checker.assertEquals("hello world", r.getResult());
	}
	
	private static class PrivateSubChecker {
		
		@SuppressWarnings("unused")
		public PrivateSubChecker() {// needed, because constructors can't be set accessible
		}
		
		@Check
		private String string() {
			return "hello world";
		}
		
	}
	
}

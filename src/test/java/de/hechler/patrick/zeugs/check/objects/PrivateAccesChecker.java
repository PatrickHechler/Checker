package de.hechler.patrick.zeugs.check.objects;

import de.hechler.patrick.zeugs.check.Assert;
import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;


@CheckClass
@SuppressWarnings("static-method")
class PrivateAccesChecker {
	
	@Check
	private void checkPrivateAcces() {
		Result r = Checker.check(PrivateSubChecker.class).getResult("string").values().stream().findAny().get().get(0).valB();
		Assert.assertTrue(r.goodResult());
		Assert.assertEquals("hello world", r.getResult());
	}
	
	private static class PrivateSubChecker {
		
		private PrivateSubChecker() {}
		
		@Check
		private String string() { return "hello world"; }
		
	}
	
}

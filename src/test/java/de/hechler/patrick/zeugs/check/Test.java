package de.hechler.patrick.zeugs.check;

import static de.hechler.patrick.zeugs.check.Assert.assertGreather;

import de.hechler.patrick.zeugs.check.objects.BigCheckResult;
import de.hechler.patrick.zeugs.check.objects.Checker;

public class Test {
	
	public void testname() throws Exception {
		main(new String[0]);
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println("[Test]: start all checks");
		BigCheckResult res = Checker.tryCheckAll(true, Test.class.getPackage().getName(), Test.class.getClassLoader(), true);
		assertGreather(res.checkedResultCount(), 0);
		System.out.println("[Test]: finished all checks");
		res.print();
		if (res.wentUnexpected()) {
			res.detailedPrintUnexpected(System.out);
			throw new Error("result is not expected!");
		}
	}
	
}

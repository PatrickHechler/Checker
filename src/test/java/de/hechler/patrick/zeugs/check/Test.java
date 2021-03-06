package de.hechler.patrick.zeugs.check;

import static de.hechler.patrick.zeugs.check.Assert.assertGreather;

import de.hechler.patrick.zeugs.check.objects.BigCheckResult;
import de.hechler.patrick.zeugs.check.objects.BigChecker;

public class Test {
	
	public void testname() throws Exception {
		main(new String[0]);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("[Test]: start all checks");
			BigCheckResult res = BigChecker.tryGenerateBigChecker(true, Test.class.getPackage().getName(), Test.class.getClassLoader(), true).get();
			assertGreather(res.checkedResultCount(), 0);
			System.out.println("[Test]: finished all checks");
			res.print();
			if (res.wentUnexpected()) {
				throw new Error("result is not expected!");
			}
		} catch (Throwable t) {
			System.out.println("[main]: end");
			t.printStackTrace();
		}
	}
	
}

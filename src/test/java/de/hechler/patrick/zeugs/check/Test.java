package de.hechler.patrick.zeugs.check;

import static de.hechler.patrick.zeugs.check.Assert.assertGreather;

import de.hechler.patrick.zeugs.check.objects.BigCheckResult;
import de.hechler.patrick.zeugs.check.objects.BigChecker;
import de.hechler.patrick.zeugs.check.objects.LogHandler;

public class Test {
	
	public void testname() { main(new String[0]); }
	
	public static void main(String[] args) {
		try {
			System.setProperty("de.hechler.patrick.check.log.level", "ALL");
			LogHandler.LOG.info("start all checks (module=" + Test.class.getModule() + ")");
			BigCheckResult res = BigChecker.tryGenerateBigChecker(true, Test.class.getPackage().getName(), Test.class.getClassLoader(), true).get();
			assertGreather(res.checkedResultCount(), 0);
			LogHandler.LOG.info("finished all checks");
			res.detailedPrint();
			if (res.wentUnexpected()) { throw new Error("result is not expected!"); }
		} catch (Throwable t) {
			LogHandler.LOG.info("failed:");
			t.printStackTrace();
		}
	}
	
}

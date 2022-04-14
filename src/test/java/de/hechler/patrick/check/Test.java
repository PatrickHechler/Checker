package de.hechler.patrick.check;

import de.hechler.patrick.check.cc.AssertionsChecker;
import de.hechler.patrick.check.cc.CheckerChecker;
import de.hechler.patrick.check.cc.CheckerCheckingChecker;
import de.hechler.patrick.check.cc.NotCheckerChecker;
import de.hechler.patrick.check.cc.PrivateAccesChecker;
import de.hechler.patrick.check.cc.ResultParamChecker;
import de.hechler.patrick.zeugs.check.BigCheckResult;
import de.hechler.patrick.zeugs.check.Checker;

public class Test {
	
	public void testname() throws Exception {
		main(new String[0]);
	}
	
	public static void main(String[] args) {
		// PrintStream o = System.out;
		// System.setOut(new PrintStream(new OutputStream() {
		//
		// @Override
		// public void write(int b) throws IOException {}
		//
		// @Override
		// public void write(byte[] b) throws IOException {}
		//
		// @Override
		// public void write(byte[] b, int off, int len) throws IOException {}
		//
		// }));
		System.out.println("[Test]: start all checks");
		BigCheckResult res = Checker.checkAll(true, AssertionsChecker.class, CheckerChecker.class, CheckerCheckingChecker.class, NotCheckerChecker.class, PrivateAccesChecker.class, ResultParamChecker.class);
		// System.setOut(o);
		System.out.println("[Test]: finished all checks");
		res.print();
		if (res.wentUnexpected()) {
			res.detailedPrintUnexpected(System.out);
			throw new Error("result is not expected!");
		}
	}
	
}

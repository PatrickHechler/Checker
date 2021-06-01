package de.hechler.patrick.check;

import de.hechler.patrick.check.cc.AssertionsChecker;
import de.hechler.patrick.check.cc.CheckerChecker;
import de.hechler.patrick.check.cc.CheckerCheckingChecker;
import de.hechler.patrick.check.cc.NotCheckerChecker;
import de.hechler.patrick.check.cc.PrivateAccesChecker;
import de.hechler.patrick.check.cc.ResultParamChecker;
import de.hechler.patrick.zeugs.check.BigCheckResult;
import de.hechler.patrick.zeugs.check.Checker;

public class StartChecks {
	
	public static void main(String[] args) {
		BigCheckResult res = Checker.checkAll(true, AssertionsChecker.class, CheckerChecker.class, CheckerCheckingChecker.class, NotCheckerChecker.class, PrivateAccesChecker.class, ResultParamChecker.class);
		System.out.println();
		System.out.flush();
		synchronized (System.out) {
			System.err.println("CHECK END");
			System.err.flush();
		}
		System.out.println();
		System.out.flush();
		res.print();
		System.out.println();
		res.forAllUnexpected((c, m, t) -> {
			System.err.println(c.getName() + '.' + m.getName() + "()");
			t.printStackTrace();
		});
	}
	
}

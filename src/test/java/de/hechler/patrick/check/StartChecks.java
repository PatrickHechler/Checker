package de.hechler.patrick.check;

import de.hechler.patrick.check.cc.AssertionsChecker;
import de.hechler.patrick.check.cc.CheckerChecker;
import de.hechler.patrick.check.cc.CheckerCheckingChecker;
import de.hechler.patrick.check.cc.NotCheckerChecker;
import de.hechler.patrick.zeugs.check.Checker;
import de.hechler.patrick.zeugs.check.Checker.BigCheckerResult;

public class StartChecks {
	
	public static void main(String[] args) {
		BigCheckerResult res = Checker.checkAll(true, AssertionsChecker.class, CheckerChecker.class, CheckerCheckingChecker.class, NotCheckerChecker.class);
		System.out.println();
		System.out.flush();
		synchronized (System.out) {
			System.err.println("CHECK END");
			System.err.flush();
		}
		System.out.println();
		res.print(System.out);
	}
	
}

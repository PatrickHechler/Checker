package de.hechler.patrick.check.cc;

import de.hechler.patrick.zeugs.check.Checker;
import de.hechler.patrick.zeugs.check.Checker.BigCheckerResult;

public class StartChecks {
	
	public static void main(String[] args) {
		BigCheckerResult res = Checker.checkAll(true, CheckerChecker.class, CheckerCheckingChecker.class, NotCheckerChecker.class);
		System.out.println();
		System.out.println("CHECK END");
		System.out.println();
		res.print(System.out);
	}
	
}

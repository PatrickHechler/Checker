package de.hechler.patrick.check;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

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
		PrintStream o = System.out;
		System.setOut(new PrintStream(new OutputStream() {
			
			@Override
			public void write(int b) throws IOException {}
			
			@Override
			public void write(byte[] b) throws IOException {}
			
			@Override
			public void write(byte[] b, int off, int len) throws IOException {}
			
		}));
		BigCheckResult res = Checker.checkAll(true, AssertionsChecker.class, CheckerChecker.class, CheckerCheckingChecker.class, NotCheckerChecker.class, PrivateAccesChecker.class, ResultParamChecker.class);
		System.setOut(o);
		res.print();
		res.forAllUnexpected((c, m, t) -> {
			System.err.println(c.getName() + '.' + m.getName() + "()");
			t.printStackTrace();
		});
	}
	
}

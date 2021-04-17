package de.hechler.patrick.zeugs.check.interfaces;


public interface ThrowingRunnable <T extends Throwable> {
	
	void run() throws T;
	
}

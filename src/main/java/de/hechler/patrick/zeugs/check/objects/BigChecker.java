package de.hechler.patrick.zeugs.check.objects;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

import de.hechler.patrick.zeugs.check.interfaces.TwoValues;

public final class BigChecker implements Runnable, Supplier <BigCheckResult>, Consumer <TwoValues <Class <?>, Checker>> {
	
	private final Iterator <TwoValues <Class <?>, Checker>> checkers;
	private volatile int maxWorkers;
	private volatile int currentWorkers;
	private BigCheckResult result;
	
	public BigChecker(Iterator <TwoValues <Class <?>, Checker>> checkers) {
		this(checkers, Runtime.getRuntime().availableProcessors());
	}
	
	public BigChecker(Iterator <TwoValues <Class <?>, Checker>> checkers, int maxWorkers) {
		this.checkers = checkers;
		this.maxWorkers = maxWorkers < -1 ? 0 : maxWorkers;
		this.currentWorkers = 0;
		this.result = null;
	}
	
	
	
	public int getMaxWorkers() {
		return maxWorkers;
	}
	
	public void setMaxWorkers(int maxWorkers) throws IllegalArgumentException {
		if (maxWorkers < -1) {
			throw new IllegalArgumentException("max workers can eiter be -1, 0 or any other value greather than zero");
		}
		this.maxWorkers = maxWorkers;
	}
	
	public boolean checkedAlready() {
		return this.result != null;
	}
	
	@Override
	public BigCheckResult get() {
		if (this.result == null) {
			run();
			while (this.currentWorkers > 0) {
				try {
					synchronized (this) {
						wait(100);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			assert this.currentWorkers == 0;
		}
		return this.result;
	}
	
	@Override
	public void run() {
		if (this.result != null) {
			this.result = new BigCheckResult();
		}
		while (this.checkers.hasNext()) {
			if (this.maxWorkers == -1 || this.currentWorkers < this.maxWorkers) {
				new Thread(() -> {
					this.currentWorkers ++ ;
					try {
						accept(this.checkers.next());
					} finally {
						this.currentWorkers -- ;
					}
				}).start();
			} else if (this.maxWorkers == 0) {
				accept(this.checkers.next());
			} else {
				try {
					synchronized (this) {
						wait(100);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		this.result.setEnd();
	}
	
	@Override
	public void accept(TwoValues <Class <?>, Checker> t) {
		Class <?> cls = t.getValueA();
		Checker checker = t.getValueB();
		CheckResult res = checker.result();
		synchronized (this) {
			this.result.put(cls, res);
			notifyAll();
		}
	}
	
}

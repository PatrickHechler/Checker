package de.hechler.patrick.zeugs.check;

import java.util.NoSuchElementException;

public final class Result {
	
	private final Object    result;
	private final Throwable err;
	
	
	public Result(Object e) {
		result = e;
		err = null;
	}
	
	public Result(Throwable t) {
		result = null;
		err = t;
	}
	
	
	
	public boolean goodResult() {
		return err == null;
	}
	
	public boolean badResult() {
		return err != null;
	}
	
	public Object getResult() throws NoSuchElementException {
		if (err != null) throw new NoSuchElementException("this is no good result!");
		else return result;
	}
	
	public Throwable getErr() throws NoSuchElementException {
		if (err == null) throw new NoSuchElementException("this is no bad result!");
		else return err;
	}
	
	@Override
	public String toString() {
		if (err != null) {
			return "error[" + err.getClass().getSimpleName() + ']';
		} else if (result != null) {
			return "result[" + result + ']';
		} else {
			return "resultd null or void";
		}
	}
	
}

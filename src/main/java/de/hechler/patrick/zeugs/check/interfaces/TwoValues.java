package de.hechler.patrick.zeugs.check.interfaces;


public interface TwoValues <A, B> {
	
	A getValueA();
	
	B getValueB();
	
	default void setValueA(A a) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("setValueA");
	}
	
	default void setValueB(B b) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("setValueA");
	}
	
}

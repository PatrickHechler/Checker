package de.hechler.patrick.zeugs.check;

import java.io.Serializable;

public interface IntInt extends Serializable, Cloneable{
	
	/**
	 * returns the {@link IntIntImpl} {@link #a} value
	 * 
	 * @return the {@link #a} value
	 */
	int getA();
	
	/**
	 * sets the {@link #a} of this {@link IntIntImpl} to the given value
	 * 
	 * @param a
	 *            the new {@link #a}
	 */
	void setA(int a);
	
	/**
	 * adds the given value to {@link #a}
	 * 
	 * @param val
	 *            add this to {@link #a}
	 * @implNote it works like <code>{int zw = {@link #getA()}; {@link #setA(int) setA(zw + val)};}</code> when {@code val} is the given value to be added
	 */
	void addA(int val);
	
	/**
	 * subtracts the given value from {@link #a}
	 * 
	 * @param val
	 *            subtract this from {@link #a}
	 * @implNote it works like <code>{int zw = {@link #getA()}; {@link #setA(int) setA(zw - val)};}</code> when {@code val} is the given value to be subtracted
	 */
	void subA(int val);
	
	/**
	 * increments the {@link #a} value by one
	 * 
	 * @apiNote it works like {@link #addA(int) addA(1)} or {@link #subA(int) subA(-1)}
	 */
	void incA();
	
	/**
	 * decrements the {@link #a} value by one
	 * 
	 *  @implNote it works like {@link #sbA(int) sub(1)} or {@link #addA(int) addA(-1)}
	 */
	void decA();
	
	int getB();
	
	void setB(int b);
	
	void addB(int b);
	
	void subB(int b);
	
	void incB();
	
	void decB();
	
	void setBoth(IntInt val);
	
	void setBoth(int a, int b);
	
	void setBoth(int val);
	
	void addBoth(IntInt val);
	
	void addBoth(int a, int b);
	
	void addBoth(int val);
	
	void subBoth(IntInt val);
	
	void subBoth(int a, int b);
	
	void subBoth(int val);
	
	void incBoth();
	
	void decBoth();
	
	int sum();
	
	boolean isAGreather(int c);
	
	boolean isAGreatherEqual(int c);
	
	boolean isASmaller(int c);
	
	boolean isASmallerEqual(int c);
	
	boolean isA(int c);
	
	boolean isAGreatherB();
	
	boolean isAGreatherEqualB();
	
	boolean isASmallerB();
	
	boolean isASmallerEqualB();
	
	boolean isANull();
	
	boolean isBGreather(int c);
	
	boolean isBGreatherEqual(int c);
	
	boolean isBSmaller(int c);
	
	boolean isBSmallerEqual(int c);
	
	boolean isB(int c);
	
	boolean isBGreatherA();
	
	boolean isBGreatherEqualA();
	
	boolean isBSmallerEqualA();
	
	boolean isBSmallerA();
	
	boolean isBNull();
	
	boolean bothNull();
	
	boolean bothSame();
	
	boolean same(int a, int b);
	
	boolean same(IntInt other);
	
	IntInt reverse();
	
	IntInt clone();
	
}

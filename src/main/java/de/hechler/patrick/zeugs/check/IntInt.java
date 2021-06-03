package de.hechler.patrick.zeugs.check;

import java.io.Serializable;
import java.util.Comparator;

/**
 * This interface contains methods to check and modify two {@code int} values
 * 
 * @author Patrick
 *
 */
public interface IntInt extends Serializable, Cloneable, Comparable <IntInt> {
	
	/**
	 * one of the two <code>null</code> acceptable {@link Comparator Comparator}&LT{@link IntInt}&GT}.<br>
	 * This {@link Comparator Comparator}&LT{@link IntInt}&GT uses the {@link #getFirst()} and {@link #getSecond()} methods to compare at fist the first value and then the second (if the first is
	 * equal)
	 * 
	 * @see #getFirst()
	 * @see #getSecond()
	 * @see #compareTo(IntInt)
	 * @see #compareWithA(int)
	 * @see #compareWithB(int)
	 * @see #CMP_DELEGATE
	 * @see #CMP_NO_NULL
	 * @see #CMP_DELEGATE_NO_NULL
	 */
	public static final Comparator <IntInt> CMP = (a, b) -> {
		if (a == null) {
			if (b == null) {
				return 1;
			} else {
				return 0;
			}
		} else if (b == null) {
			return -1;
		}
		int aa = a.getFirst();
		int bb = b.getFirst();
		if (aa > bb) {
			return 1;
		} else if (aa < bb) {
			return -1;
		} else {
			aa = a.getSecond();
			bb = b.getSecond();
			if (aa > bb) {
				return 1;
			} else if (aa < bb) {
				return -1;
			} else {
				return 0;
			}
		}
	};
	
	/**
	 * one of the two <code>null</code> acceptable {@link Comparator Comparator}&LT{@link IntInt}&GT}.<br>
	 * This {@link Comparator Comparator}&LT{@link IntInt}&GT just uses the {@link #compareTo(IntInt)} method to compare the two {@link IntInt}s.<br>
	 * 
	 * @see #getFirst()
	 * @see #getSecond()
	 * @see #compareTo(IntInt)
	 * @see #compareWithA(int)
	 * @see #compareWithB(int)
	 * @see #CMP
	 * @see #CMP_NO_NULL
	 * @see #CMP_DELEGATE_NO_NULL
	 */
	public static final Comparator <IntInt> CMP_DELEGATE = (a, b) -> {
		if (a == null) {
			if (b == null) {
				return 1;
			} else {
				return 0;
			}
		} else if (b == null) {
			return -1;
		}
		return a.compareTo(b);
	};
	
	/**
	 * one of the two non <code>null</code> {@link Comparator Comparator}&LT{@link IntInt}&GT}.<br>
	 * This {@link Comparator Comparator}&LT{@link IntInt}&GT uses the {@link #getFirst()} and {@link #getSecond()} methods to compare at fist the first value and then the second (if the first is
	 * equal)
	 * 
	 * @see #getFirst()
	 * @see #getSecond()
	 * @see #compareTo(IntInt)
	 * @see #compareWithA(int)
	 * @see #compareWithB(int)
	 * @see #CMP
	 * @see #CMP_DELEGATE
	 * @see #CMP_DELEGATE_NO_NULL
	 */
	public static final Comparator <IntInt> CMP_NO_NULL = (a, b) -> {
		int aa = a.getFirst();
		int bb = b.getFirst();
		if (aa > bb) {
			return 1;
		} else if (aa < bb) {
			return -1;
		} else {
			aa = a.getSecond();
			bb = b.getSecond();
			if (aa > bb) {
				return 1;
			} else if (aa < bb) {
				return -1;
			} else {
				return 0;
			}
		}
	};
	
	/**
	 * one of the two non <code>null</code> {@link Comparator Comparator}&LT{@link IntInt}&GT}.<br>
	 * This {@link Comparator Comparator}&LT{@link IntInt}&GT just uses the {@link #compareTo(IntInt)} method to compare the two {@link IntInt}s
	 * 
	 * @see #getFirst()
	 * @see #getSecond()
	 * @see #compareTo(IntInt)
	 * @see #compareWithA(int)
	 * @see #compareWithB(int)
	 * @see #CMP
	 * @see #CMP_DELEGATE
	 * @see #CMP_NO_NULL
	 */
	public static final Comparator <IntInt> CMP_DELEGATE_NO_NULL = (a, b) -> a.compareTo(b);
	
	/**
	 * returns the first value of this {@link IntInt}
	 * 
	 * @return the first value
	 * @see #setFirst()
	 * @see #isFirstGreather(int)
	 * @see #isFirstGreatherEqual(int)
	 * @see #isFirstSmaller(int)
	 * @see #isFirstSmallerEqual(int)
	 * @see #isFirstZero()
	 * @see #isAGreatherB()
	 * @see #isAGreatherEqualB()
	 * @see #isASmallerB()
	 * @see #isASmallerEqualB()
	 * @see #isFirstEqual(int)
	 */
	int getFirst();
	
	/**
	 * sets the first value of this {@link IntInt} to the given value
	 * 
	 * @param a
	 *            the new first value
	 * @see #getFirst()
	 * @see #addBoth(int)
	 * @see #subBoth(int)
	 * @see #incBoth()
	 * @see #decBoth()
	 */
	void setFirst(int a);
	
	/**
	 * adds the given value to the first value of this {@link IntInt}
	 * 
	 * @param val
	 *            add this to the first value
	 * @implNote it works like <code>{int zw = {@link #getFirst()}; {@link #setFirst(int) setA(zw + val)};}</code> when {@code val} is the given value to be added
	 * @see #subFirst(int)
	 * @see #setFirst(int)
	 * @see #getFirst()
	 */
	void addFirst(int val);
	
	/**
	 * subtracts the given value from the first value of this {@link IntInt}
	 * 
	 * @param val
	 *            subtract this from the first value
	 * @implNote it works like <code>{int zw = {@link #getFirst()}; {@link #setFirst(int) setA(zw - val)};}</code> when {@code val} is the given value to be subtracted
	 * @see #addFirst(int)
	 * @see #setFirst(int)
	 * @see #getFirst()
	 */
	void subFirst(int val);
	
	/**
	 * increments the first value by one
	 * 
	 * @apiNote it works like {@link #addFirst(int) addA(1)} or {@link #subFirst(int) subA(-1)}
	 * @see #addFirst(int)
	 * @see #getFirst()
	 * @see #setFirst(int)
	 * 
	 */
	void incFirst();
	
	/**
	 * decrements the first value by one
	 * 
	 * @implNote it works like {@link #sbA(int) sub(1)} or {@link #addFirst(int) addA(-1)}
	 * @see #subFirst(int)
	 * @see #getFirst()
	 * @see #setFirst(int)
	 */
	void decFirst();
	
	/**
	 * returns the second value of this {@link IntInt}
	 * 
	 * @return the second value of this {@link IntInt}
	 * @see #setSecond(int)
	 * @see #setBoth(IntInt)
	 */
	int getSecond();
	
	/**
	 * sets the second value of this {@link IntInt}
	 * 
	 * @param b
	 *            the new second value
	 * @see #setBoth(int)
	 * @see #setBoth(int, int)
	 * @see #setBoth(IntInt)
	 * @see #getSecond()
	 */
	void setSecond(int b);
	
	/**
	 * adds the given value {@code b} to the second value of this {@link IntInt}
	 * 
	 * @param b
	 *            the value to be added to the second value
	 * @implNote this works like <code>{int zw = {@link #getSecond()}; {@link #setSecond(int) setB(zw + b)};}</code> when b is the value to be added
	 * @see #addBoth(int)
	 * @see #addBoth(int, int)
	 * @see #addBoth(IntInt)
	 * @see #getSecond()
	 * @see #setSecond(int)
	 */
	void addSecond(int b);
	
	/**
	 * subtracts the given value {@code b} from the second value of this {@link IntInt}
	 * 
	 * @param b
	 *            the value to be subtracted from the second value
	 * @implNote this works like <code>{int zw = {@link #getSecond()}; {@link #setSecond(int) setB(zw - b)};}</code> when b is the value to be subtracted
	 * @see #subBoth(int)
	 * @see #subBoth(int, int)
	 * @see #subBoth(IntInt)
	 * @see #getSecond()
	 * @see #setSecond(int)
	 * @see #decSecond()
	 */
	void subSecond(int b);
	
	/**
	 * increments the second value of this {@link IntInt} by one
	 * 
	 * @implNote it works like {@link #addSecond(int) addB(1)} or {@link #subSecond(int) subB(-1)}
	 * @see #incBoth()
	 * @see #addSecond(int)
	 * @see #getSecond()
	 * @see #setSecond(int)
	 */
	void incSecond();
	
	/**
	 * decrements the second value of this {@link IntInt} by one
	 * 
	 * @implNote it works like {@link #subSecond(int) addB(1)} or {@link #addSecond(int) addB(-1)}
	 * @see #decBoth()
	 * @see #subSecond(int)
	 * @see #getSecond()
	 * @see #setSecond(int)
	 */
	void decSecond();
	
	/**
	 * sets both values of this {@link IntInt} to the values of the given {@link IntInt}.<br>
	 * This method can be used to copy to existing {@link IntInt}s or to switch between implementations of the {@link IntInt} interface
	 * 
	 * @param val
	 *            the container of the new first and new second values of this {@link IntInt}
	 * @implNote this works like <code>{int a = {@link #getFirst() val.getFirst()}; int b = {@link #getSecond() val.getSecond()}; {@link #setBoth(int, int) this.setBoth(a,b)};}</code> when {@code val}
	 *           is the given {@link IntInt}
	 * @see #setBoth(int)
	 * @see #setBoth(int, int)
	 * @see #setFirst(int)
	 * @see #setSecond(int)
	 */
	void setBoth(IntInt val);
	
	/**
	 * sets both values of this {@link IntInt} to the given two values.
	 * 
	 * @param first
	 *            the new first value of this {@link IntInt}
	 * @param second
	 *            the new second value of this {@link IntInt}
	 * @implNote it works like <code>{{@link #setFirst(int) this.setFirst(first)}; {@link #setSecond(int) this.setSecond(second)};}</code> when {@code first} and {@code second} are the given two
	 *           values
	 * @see #setBoth(int)
	 * @see #setBoth(IntInt)
	 * @see #setFirst(int)
	 * @see #setSecond(int)
	 */
	void setBoth(int first, int second);
	
	/**
	 * sets both values of this {@link IntInt} to the given value
	 * 
	 * @param val
	 *            the new first and new second value of this {@link IntInt}
	 * @implNote this works like {@link #setBoth(int, int) setBoth(val, val)}
	 * @see #setBoth(int, int)
	 * @see #setBoth(IntInt)
	 * @see #setFirst(int)
	 * @see #setSecond(int)
	 */
	void setBoth(int val);
	
	/**
	 * sets both values of this {@link IntInt} to the 0
	 * 
	 * @implNote this works like {@link #setBoth(int) setBoth(0)} or {@link #setBoth(int,int) setBoth(0,0)}
	 * @see #setBoth(int)
	 * @see #setBoth(int, int)
	 * @see #setBoth(IntInt)
	 * @see #setFirst(int)
	 * @see #setSecond(int)
	 */
	void setBothNull();
	
	/**
	 * adds both values of the given {@link IntInt} the the values of this {@link IntInt}.<br>
	 * the first value of the given {@link IntInt} will be added to the first value of this {@link IntInt}.<br>
	 * the second value of the given {@link IntInt} will be added to the second value of this {@link IntInt}.<br>
	 * 
	 * @param val
	 *            the {@link IntInt} to be added to this {@link IntInt}
	 * @implNote it works like <code>{int a = val.{@link #getFirst() getFirst()}; int b = val.{@link #getSecond() getSecond}; this.{@link #addBoth(int, int) addBoth(a,b)};}</code>
	 * @see #addFirst(int)
	 * @see #addSecond(int)
	 * @see #addBoth(int, int)
	 * @see #addBoth(int)
	 */
	void addBoth(IntInt val);
	
	/**
	 * adds the given {@code first} value to the first value to this {@link IntInt} and the given {@code second} value to the second value of this {@link IntInt}
	 * 
	 * @param first
	 *            add this to the first value of this {@link IntInt}
	 * @param second
	 *            add this to the second value of this {@link IntInt}
	 * @implNote it works like <code>{this.{@link #addFirst(int) addFirst(first)}; this.{@link #addSecond(int) addSecond(second)};}</code>
	 * @see #addFirst(int)
	 * @see #addSecond(int)
	 * @see #addBoth(IntInt)
	 * @see #addBoth(int)
	 */
	void addBoth(int first, int second);
	
	/**
	 * adds the given value to the first and the second value of this {@link IntInt}
	 * 
	 * @param val
	 *            the value to be added
	 * @implNote it works like {@link #addBoth(int, int) addBoth(val,val)}
	 * @see #addFirst(int)
	 * @see #addSecond(int)
	 * @see #addBoth(IntInt)
	 * @see #addBoth(int, int)
	 */
	void addBoth(int val);
	
	/**
	 * subtracts the values of the given {@link IntInt} from the corresponding values of this {@link IntInt}
	 * 
	 * @param val
	 *            the container for the two values to subtract
	 * @implNote it works like <code>{int a = val.{@link #getFirst()}; int b = val.{@link #getSecond()}; this.{@link #subBoth(int, int) subBoth(a,b)};}</code>
	 * @see #subFirst(int)
	 * @see #subSecond(int)
	 * @see #subBoth(int)
	 * @see #subBoth(int, int)
	 */
	void subBoth(IntInt val);
	
	/**
	 * subtracts {@code first} from the first value of this {@link IntInt} and {@code second} from the second value of this {@link IntInt}
	 * 
	 * @param first
	 *            to remove this from the first value of this {@link IntInt}
	 * @param second
	 *            to remove this from the second value of this {@link IntInt}
	 * @implNote it works like <code>{{@link #subFirst(int) subFirst(first)}; {@link #subSecond(int) subSecond(second)};}</code>
	 * @see #subFirst(int)
	 * @see #subSecond(int)
	 * @see #subBoth(IntInt)
	 * @see #subBoth(int)
	 */
	void subBoth(int first, int second);
	
	/**
	 * subtracts the given value from both values (the first and second) of this {@link IntInt}
	 * 
	 * @param val
	 *            subtract this from the both values
	 * @implNote it works like {@link #subBoth(int, int) subBoth(val,val)}
	 * @see #subFirst(int)
	 * @see #subSecond(int)
	 * @see #subBoth(int, int)
	 * @see #subBoth(IntInt)
	 */
	void subBoth(int val);
	
	/**
	 * increments both values of this {@link IntInt} by one
	 * 
	 * @implNote it works like {@link #addBoth(int) addBoth(1)} or {@link #subBoth(int) subBoth(-1)}
	 * @see #addBoth(int)
	 * @see #decBoth(int)
	 */
	void incBoth();
	
	/**
	 * decrements both values of this {@link IntInt} by one
	 * 
	 * @implNote it works like {@link #subBoth(int) subBoth(1)} or {@link #addBoth(int) addBoth(-1)}
	 * @see #subBoth(int)
	 * @see #incBoth()
	 */
	void decBoth();
	
	/**
	 * returns the sum of both values of this {@link IntInt}
	 * 
	 * @return the sum of both values of this {@link IntInt}
	 * @implNote it works like <code>({@link #getFirst()} + {@link #getSecond()})</code>
	 * @see #getFirst()
	 * @see #getSecond()
	 */
	int sum();
	
	/**
	 * checks if the first value of this {@link IntInt} is greater than the given value and returns <code>true</code> if it is so. If the given value is at least as high as the first value
	 * <code>false</code> will be returned
	 * 
	 * @param c
	 *            the value to compare with the first value
	 * @return <code>true</code> when the first value of this {@link IntInt} is greater than the given value, if not <code>false</code>
	 * @implNote it works like <code>({@link #getFirst()} &LT c)</code>
	 */
	boolean isFirstGreather(int c);
	
	/**
	 * checks if the first value of this {@link IntInt} is at least as high as the given value and returns <code>true</code> if it is so. If the given value is at greater as the first value
	 * <code>false</code> will be returned
	 * 
	 * @param c
	 *            the value to compare with the first value
	 * @return <code>true</code> when the first value of this {@link IntInt} is greater or equal than the given value, if not <code>false</code>
	 * @implNote it works like <code>({@link #getFirst()} &GT= c)</code>
	 */
	boolean isFirstGreatherEqual(int c);
	
	/**
	 * checks if the first value of this {@link IntInt} is smaller then the given value and returns <code>true</code> if it is so and <code>false</code> if not
	 * 
	 * @param c
	 *            the value to compare with the first value
	 * @return <code>true</code> if the first value is smaller then the given value and <code>false</code> if not
	 * @implNote it works like <code>({@link #getFirst()} &LT c)</code> or <code>(!{@link #isFirstGreatherEqual(int) isFirstGreatherEqual(c)})</code>
	 */
	boolean isFirstSmaller(int c);
	
	/**
	 * checks if the first value of this {@link IntInt} is smaller or equal than the given value {@code c}. If it is so <code>true</code> will be returned and <code>false</code> if not
	 * 
	 * @param c
	 *            the value to compare with the first value
	 * @return <code>true</code> if the first value of this {@link IntInt} is smaller or equal than the given value {@code c} and <code>false</code> if not
	 * @implNote it works like <code>({@link #getFirst()} &LT= c)</code> or <code>(!{@link #isFirstGreather(int) isFirstGreather(c)})</code>
	 */
	boolean isFirstSmallerEqual(int c);
	
	/**
	 * returns <code>true</code> when the given value {@code c} is the first value of this {@link IntInt}, if not <code>false</code> will be returned<br>
	 * this method never returns the same value as {@link #isFirstNotEqual(int)}
	 * 
	 * @param c
	 *            the value to be compared with the first value of this {@link IntInt}
	 * @return <code>true</code> when the given value {@code c} is the first value. if not <code>false</code>
	 * @implNote it works like <code>({@link #getFirst()} == c)</code> or
	 *           <code>{{@link #subFirst(int) subFirst(c)}; boolean bool = {@link #isFirstZero()}; {@link #addFirst(int) addFirst(c)}; return bool;}</code>
	 * @see #getFirst()
	 * @see #isFirstZero()
	 * @see #bothSame()
	 */
	boolean isFirstEqual(int c);
	
	/**
	 * returns <code>true</code> if the given value is not the first value of this {@link IntInt}, if they are equal <code>false</code> is returned<br>
	 * this method never returns the same value as {@link #isFirstEqual(int)}
	 * 
	 * @param c
	 *            the value to check with the first value
	 * @return <code>true</code> if the given value is not the first value of this {@link IntInt}, if they are equal <code>false</code>
	 * @implNote it works like <code>({@link #getFirst()} != c)</code> or <code>(!{@link #isFirstEqual(int) isFirstEqual(c)})</code>
	 * @see #isFirstEqual(int)
	 * @see #isFirstGreather(int)
	 * @see #isFirstGreatherEqual(int)
	 * @see #isFirstSmaller(int)
	 * @see #isFirstSmallerEqual(int)
	 * @see #isFirstNotZero()
	 */
	boolean isFirstNotEqual(int c);
	
	/**
	 * returns <code>true</code> if the first value of this {@link IntInt} is {@code 0}. if the first value is any other value it will return <code>false</code> This method returns never the same
	 * value as {@link #isFirstNotZero()}.
	 * 
	 * @return <code>true</code> if the first value is {@code 0} and <code>false</code> if not
	 * @implNote it works like <code>({@link #getFirst()} == 0)</code> or <code>({@link #isFirstEqual(int) isFirstEqual(0)}</code>
	 * @see #isFirstEqual(int)
	 * @see #isFirstNotZero()
	 * @see #isFirstPositive()
	 * @see #isFirstNegative()
	 * @see #isFirstNotPositive()
	 * @see #isFirstNotNegative()
	 * @see #aSignum()
	 */
	boolean isFirstZero();
	
	/**
	 * returns <code>true</code> if the first value of this {@link IntInt} is not {@code 0}. If the first value is {@code 0} <code>false</code> will be the return value.<br>
	 * This method returns never the same value as {@link #isFirstZero()}.
	 * 
	 * @return <code>true</code> if the first value of this {@link IntInt} is not {@code 0}. If the first value is {@code 0} <code>false</code>
	 * @implNote it works like <code>(!{@link #isFirstZero()})</code>, <code>({@link #getFirst()} != 0)</code>, <code>({@link #isFirstNotEqual(int) isFirstNotEqual(0)})</code>
	 * @see #isFirstNotEqual(int)
	 * @see #isFirstEqual(int)
	 * @see #isFirstZero()
	 * @see #isFirstPositive()
	 * @see #isFirstNegative()
	 * @see #isFirstNotPositive()
	 * @see #isFirstNotNegative()
	 * @see #aSignum()
	 */
	boolean isFirstNotZero();
	
	/**
	 * returns <code>true</code> if the first value of this {@link IntInt} is greater than {@code 0} (not equal) and returns <code>false</code> if the first value of this {@link IntInt} is smaller or
	 * equal {@code 0}.
	 * 
	 * @return <code>true</code> if the first value of this {@link IntInt} is greater than {@code 0} (not equal). if the first value of this {@link IntInt} is smaller or equal {@code 0}
	 *         <code>false</code>
	 * @implNote it works like <code>({@link #getFirst()} > 0)</code>, <code>({@link #isFirstGreather(int) isFirstGreather(0)})</code> or
	 *           <code>({@link #isFirstGreatherEqual(int) isFirstGreatherEqual(1)})</code>
	 * @see #aSignum()
	 * @see #isFirstNegative()
	 * @see #isFirstNotPositive()
	 * @see #isFirstNotNegative()
	 * @see #isFirstGreather(int)
	 */
	boolean isFirstPositive();
	//TODO more Javadoc
	boolean isFirstNotPositive();
	
	boolean isFirstNegative();
	
	boolean isFirstNotNegative();
	
	boolean isASignumEqual(int c);
	
	boolean isASignumNotEqual(int c);
	
	boolean isASignumEqualA();
	
	boolean isASignumNotEqualA();
	
	int aSignum();
	
	int compareWithA(int c);
	
	boolean isAGreatherB();
	
	boolean isAGreatherEqualB();
	
	boolean isASmallerB();
	
	boolean isASmallerEqualB();
	
	boolean isBGreather(int c);
	
	boolean isBGreatherEqual(int c);
	
	boolean isBSmaller(int c);
	
	boolean isBSmallerEqual(int c);
	
	boolean isB(int c);
	
	boolean isBNull();
	
	boolean isBNotNull();
	
	boolean isBPositive();
	
	boolean isBNotPositive();
	
	boolean isBNegative();
	
	boolean isBNotNegative();
	
	boolean isBSignumEqual(int c);
	
	boolean isBSignumNotEqual(int c);
	
	boolean isBSignumEqualB();
	
	boolean isBSignumNotEqualB();
	
	int bSignum();
	
	int compareWithB(int c);
	
	boolean isBGreatherA();
	
	boolean isBGreatherEqualA();
	
	boolean isBSmallerEqualA();
	
	boolean isBSmallerA();
	
	boolean bothNull();
	
	boolean bothNotNull();
	
	boolean bothPositive();
	
	boolean bothNegative();
	
	boolean bothSame();
	
	boolean bothSameSignums();
	
	boolean bothDiffrentSignums();
	
	boolean diffrentValues();
	
	boolean same(int a, int b);
	
	boolean same(IntInt other);
	
	IntInt reverse();
	
	IntInt clone();
	
	@Override
	int compareTo(IntInt o);
	
}

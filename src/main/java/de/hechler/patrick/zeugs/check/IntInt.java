package de.hechler.patrick.zeugs.check;

import java.io.Serializable;

/**
 * this class is an easy a wrapper about two {@code public} primitiv {@code int} values
 * 
 * @author Patrick
 */
public class IntInt implements Serializable, Cloneable {
	
	/** UID */
	private static final long serialVersionUID = 6961158138373138854L;
	
	
	
	/**
	 * the first {@code int} value
	 */
	public int a;
	/**
	 * the second {@code int} value
	 */
	public int b;
	
	/**
	 * creates an {@link IntInt} with both values set to {@code 0}
	 */
	public IntInt() {}
	
	/**
	 * creates an {@link IntInt} with the values set to the params
	 * 
	 * @param a
	 *            the init value of {@link #a}
	 * @param b
	 *            the init value of {@link #b}
	 */
	public IntInt(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public IntInt(IntInt copy) {
		this.a = copy.a;
		this.b = copy.b;
	}
	
	@Override
	public IntInt clone() {
		try {
			return (IntInt) super.clone();
		} catch (CloneNotSupportedException e) {
			return new IntInt(this);
		}
	}
	
}

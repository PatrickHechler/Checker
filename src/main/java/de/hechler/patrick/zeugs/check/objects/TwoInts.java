package de.hechler.patrick.zeugs.check.objects;

import de.hechler.patrick.zeugs.check.interfaces.TwoValues;

/**
 * a simple implementation for the {@link TwoValues} interface for two non <code>null</code>
 * {@link Integer} values.<br>
 * the two values are saved in public {@code int} fields.
 * modifications with the {@link #setValueA(Object)} and {@link #setValueB(Object)}
 * methods are allowed for non null values.
 * 
 * @author Patrick
 */
public final class TwoInts implements TwoValues <Integer, Integer>, Cloneable {
	
	/**
	 * the first value
	 */
	public int a;
	/**
	 * the second value
	 */
	public int b;
	
	
	public TwoInts() {
		this.a = this.b = 0;
	}
	
	public TwoInts(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public Integer getValueA() { return this.a; }
	
	@Override
	public Integer getValueB() { return this.b; }
	
	@Override
	public void setValueA(Integer a) throws NullPointerException {
		if (a == null) {
			throw new NullPointerException("the new first value is null");
		}
		this.a = a;
	}
	
	@Override
	public void setValueB(Integer b) throws NullPointerException {
		if (b == null) {
			throw new NullPointerException("the new second value is null");
		}
		this.b = b;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		TwoInts other = (TwoInts) obj;
		if (a != other.a) return false;
		if (b != other.b) return false;
		return true;
	}
	
	@Override
	protected TwoInts clone() {
		try {
			return (TwoInts) super.clone();
		} catch (CloneNotSupportedException e) {
			return new TwoInts(this.a, this.b);
		}
	}
	
}

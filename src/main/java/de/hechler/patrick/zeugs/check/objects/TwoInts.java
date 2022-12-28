package de.hechler.patrick.zeugs.check.objects;

import de.hechler.patrick.zeugs.check.interfaces.TwoVals;

/**
 * a simple implementation for the {@link TwoValues} interface for two non
 * <code>null</code>
 * {@link Integer} values.<br>
 * the two values are saved in public {@code int} fields.
 * modifications with the {@link #valA(Object)} and
 * {@link #valB(Object)} methods are allowed for non null values.
 * 
 * @author Patrick
 */
public final class TwoInts implements TwoVals<Integer, Integer>, Cloneable {
	
	/**
	 * the A value
	 */
	public int a;
	/**
	 * the B value
	 */
	public int b;
	
	/**
	 * creates a new {@link TwoValues} with the two values initially set to
	 * {@code 0}
	 */
	public TwoInts() { this.a = this.b = 0; }
	
	/**
	 * creates a new {@link TwoValues} with the given two initial values
	 * 
	 * @param a the initial A value
	 * @param b the initial B value
	 */
	public TwoInts(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public Integer valA() { return this.a; }
	
	@Override
	public Integer valB() { return this.b; }
	
	@Override
	public void valA(Integer a) throws NullPointerException {
		if (a == null) { throw new NullPointerException("the new first value is null"); }
		this.a = a;
	}
	
	@Override
	public void valB(Integer b) throws NullPointerException {
		if (b == null) { throw new NullPointerException("the new second value is null"); }
		this.b = b;
	}
	
	@Override
	public int hashCode() { return Integer.hashCode(a) ^ Integer.hashCode(b); }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof TwoInts ti)) {
			if (!(obj instanceof TwoVals<?, ?> tv)) return false;
			Object oa = tv.valA();
			if (oa == null || !(oa instanceof Integer ai)) return false;
			if (ai.intValue() != a) return false;
			Object ob = tv.valB();
			if (ob == null || !(ob instanceof Integer bi)) return false;
			if (bi.intValue() != b) return false;
		} else {
			if (a != ti.a) return false;
			if (b != ti.b) return false;
		}
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

package de.hechler.patrick.zeugs.check.objects;

import de.hechler.patrick.zeugs.check.interfaces.TwoValues;

/**
 * a simple implementation of the {@link TwoValues} interface.<br>
 * this implementation allows public access to the two values.<br>
 * modifications with the {@link #setValueA(Object)} and {@link #setValueB(Object)}
 * methods are allowed.
 * 
 * @author Patrick
 * @param <A>
 *            the type of the first value
 * @param <B>
 *            the type of the second value
 */
public class TwoValuesImpl <A, B> implements TwoValues <A, B>, Cloneable {
	
	/**
	 * the first value
	 */
	public A valueA;
	/**
	 * the second value
	 */
	public B valueB;
	
	public TwoValuesImpl(A valueA, B valueB) {
		this.valueA = valueA;
		this.valueB = valueB;
	}
	
	@Override
	public A getValueA() { return valueA; }
	
	@Override
	public B getValueB() { return valueB; }
	
	@Override
	public void setValueA(A valueA) { this.valueA = valueA; }
	
	@Override
	public void setValueB(B valueB) { this.valueB = valueB; }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( (valueA == null) ? 0 : valueA.hashCode());
		result = prime * result + ( (valueB == null) ? 0 : valueB.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		TwoValuesImpl <?, ?> other = (TwoValuesImpl <?, ?>) obj;
		if (valueA == null) {
			if (other.valueA != null) return false;
		} else if ( !valueA.equals(other.valueA)) return false;
		if (valueB == null) {
			if (other.valueB != null) return false;
		} else if ( !valueB.equals(other.valueB)) return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TwoValues [valueA=");
		builder.append(valueA);
		builder.append(", valueB=");
		builder.append(valueB);
		builder.append("]");
		return builder.toString();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected TwoValuesImpl <A, B> clone() {
		try {
			return (TwoValuesImpl <A, B>) super.clone();
		} catch (CloneNotSupportedException e) {
			return new TwoValuesImpl <A, B>(valueA, valueB);
		}
	}
	
}

package de.hechler.patrick.zeugs.check;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractIntInt implements IntInt, Cloneable {
	
	/** UID */
	private static final long serialVersionUID = -885415752003258701L;
	// the impl does not care, they only need to be different and not null
	private static final Object FALSE = Boolean.FALSE;
	// if someone hacked the Boolean class this check could be helpful
	private static final Object TRUE = Boolean.TRUE == FALSE ? new Object() : Boolean.TRUE;
	
	
	
	// memory to memorize, which IntInts are equal and which are not
	private transient Map <AbstractIntInt, Object> equals;
	// saves the hash
	private transient int h;
	
	
	
	
	public AbstractIntInt() {
		super();
	}
	
	
	
	
	@Override
	public IntInt reverse() {
		return new ReverseIntInt(this);
	}
	
	@Override
	public int hashCode() {
		if (equals == null) {
			equals = new HashMap <>();
			final int prime = 31;
			int hash = 1;
			hash = prime * hash + getFirst();
			hash = prime * hash + getSecond();
			h = hash;
		}
		return h;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		AbstractIntInt other = (AbstractIntInt) obj;
		if (hashCode() != other.hashCode()) {
			return false;// to not need to save, because hash code is unchangeable
		}
		Object bool = equals.get(other);
		if (bool != null) return bool == TRUE;
		bool = other.equals.get(this);
		if (bool != null) return bool == TRUE;
		int ma = getFirst(), oa = other.getFirst();
		if (ma != oa) {
			equals.put(other, FALSE);
			return false;
		} else {
			int mb = getSecond(), ob = other.getSecond();
			if (mb != ob) {
				equals.put(other, FALSE);
				return false;
			}
		}
		equals.put(other, TRUE);
		return true;
	}
	
	@Override
	public AbstractIntInt clone() {
		try {
			return (AbstractIntInt) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e);
		}
	}
	
	@Override
	public String toString() {
		return "IntInt[" + getFirst() + ", " + getSecond() + "]";
	}
	
}

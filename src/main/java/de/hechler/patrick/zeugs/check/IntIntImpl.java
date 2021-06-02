package de.hechler.patrick.zeugs.check;

import java.util.HashMap;
import java.util.Map;

/**
 * this class is an easy a wrapper about two primitiv {@code int} values.<br>
 * 
 * This class allows to make some simple checks and operations with the two {@code int} values (like {@link #addA(int)}, {@link #isBGreather(int)} or {@link #bothSame()}).
 * 
 * @author Patrick
 */
public class IntIntImpl implements IntInt {
	
	/** UID */
	private static final long serialVersionUID = 6961158138373138854L;
	
	
	
	/**
	 * the first {@code int} value
	 */
	private int a;
	/**
	 * the second {@code int} value
	 */
	private int b;
	
	/**
	 * creates an {@link IntIntImpl} with both values set to {@code 0}
	 */
	public IntIntImpl() {}
	
	/**
	 * creates an {@link IntIntImpl} with the values set to the params
	 * 
	 * @param a
	 *            the init value of {@link #a}
	 * @param b
	 *            the init value of {@link #b}
	 */
	public IntIntImpl(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	/**
	 * creates an copy {@link IntIntImpl} of the given {@link IntIntImpl}
	 * 
	 * @param copy
	 *            the {@link IntIntImpl} to be copied
	 * 			
	 */
	public IntIntImpl(IntIntImpl copy) {
		this.a = copy.a;
		this.b = copy.b;
	}
	//TODO more Javadoc!
	@Override
	public int getA() {
		return this.a;
	}
	
	@Override
	public void setA(int a) {
		this.a = a;
	}
	
	@Override
	public void addA(int val) {
		this.a += val;
	}
	
	@Override
	public void subA(int val) {
		this.a -= val;
	}
	
	@Override
	public void incA() {
		this.a ++ ;
	}
	
	
	@Override
	public void decA() {
		this.a -- ;
	}
	
	@Override
	public int getB() {
		return this.b;
	}
	
	@Override
	public void setB(int b) {
		this.b = b;
	}
	
	@Override
	public void addB(int b) {
		this.b += b;
	}
	
	@Override
	public void subB(int b) {
		this.b -= b;
	}
	
	@Override
	public void incB() {
		this.b ++ ;
	}
	
	@Override
	public void decB() {
		this.b -- ;
	}
	
	@Override
	public void setBoth(IntInt val) {
		this.a = val.getA();
		this.b = val.getB();
	}
	
	public void setBoth(IntIntImpl val) {
		this.a = val.a;
		this.b = val.b;
	}
	
	@Override
	public void setBoth(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public void setBoth(int val) {
		this.a = val;
		this.b = val;
	}
	
	@Override
	public void addBoth(IntInt val) {
		this.a += val.getA();
		this.b += val.getB();
	}
	
	public void addBoth(IntIntImpl val) {
		this.a += val.a;
		this.b += val.b;
	}
	
	@Override
	public void addBoth(int a, int b) {
		this.a += a;
		this.b += b;
	}
	
	@Override
	public void addBoth(int val) {
		this.a += val;
		this.b += val;
	}
	
	@Override
	public void subBoth(IntInt val) {
		this.a -= val.getA();
		this.b -= val.getB();
	}
	
	public void subBoth(IntIntImpl val) {
		this.a -= val.a;
		this.b -= val.b;
	}
	
	@Override
	public void subBoth(int a, int b) {
		this.a -= a;
		this.b -= b;
	}
	
	@Override
	public void subBoth(int val) {
		this.a -= val;
		this.b -= val;
	}
	
	@Override
	public void incBoth() {
		this.a ++ ;
		this.b ++ ;
	}
	
	@Override
	public void decBoth() {
		this.a -- ;
		this.b -- ;
	}
	
	@Override
	public int sum() {
		return this.a + this.b;
	}
	
	@Override
	public boolean isAGreather(int c) {
		return this.a > c;
	}
	
	@Override
	public boolean isAGreatherEqual(int c) {
		return this.a >= c;
	}
	
	@Override
	public boolean isASmaller(int c) {
		return this.a < c;
	}
	
	@Override
	public boolean isASmallerEqual(int c) {
		return this.a <= c;
	}
	
	@Override
	public boolean isA(int c) {
		return this.a == c;
	}
	
	@Override
	public boolean isAGreatherB() {
		return this.a > this.b;
	}
	
	@Override
	public boolean isAGreatherEqualB() {
		return this.a >= this.b;
	}
	
	@Override
	public boolean isASmallerB() {
		return this.a < this.b;
	}
	
	@Override
	public boolean isASmallerEqualB() {
		return this.a <= this.b;
	}
	
	@Override
	public boolean isANull() {
		return this.a == 0;
	}
	
	@Override
	public boolean isBGreather(int c) {
		return this.b > c;
	}
	
	@Override
	public boolean isBGreatherEqual(int c) {
		return this.b >= c;
	}
	
	@Override
	public boolean isBSmaller(int c) {
		return this.b < c;
	}
	
	@Override
	public boolean isBSmallerEqual(int c) {
		return this.b <= c;
	}
	
	@Override
	public boolean isB(int c) {
		return this.b == c;
	}
	
	@Override
	public boolean isBGreatherA() {
		return this.b > this.a;
	}
	
	@Override
	public boolean isBGreatherEqualA() {
		return this.b >= this.a;
	}
	
	@Override
	public boolean isBSmallerEqualA() {
		return this.b <= this.a;
	}
	
	@Override
	public boolean isBSmallerA() {
		return this.b < this.a;
	}
	
	@Override
	public boolean isBNull() {
		return this.b == 0;
	}
	
	@Override
	public boolean bothNull() {
		return this.a == 0 && this.b == 0;
	}
	
	@Override
	public boolean bothSame() {
		return this.a == this.b;
	}
	
	@Override
	public boolean same(int a, int b) {
		return this.a == a && this.b == b;
	}
	
	@Override
	public boolean same(IntInt other) {
		if (other == null) return false;
		return this.a == other.getA() && this.b == other.getB();
	}
	
	public boolean same(IntIntImpl other) {
		if (other == null) return false;
		return this.a == other.a && this.b == other.b;
	}
	
	private ReverseIntIntImpl reverse;
	
	@Override
	public ReverseIntIntImpl reverse() {
		if (reverse == null) {
			reverse = new ReverseIntIntImpl();
		}
		return reverse;
	}
	
	@Override
	public IntIntImpl clone() {
		try {
			return (IntIntImpl) super.clone();
		} catch (CloneNotSupportedException e) {
			return new IntIntImpl(this);
		}
	}
	
	// the impl does not care, they only need to be different and not null
	private static final Object FALSE = Boolean.FALSE;
	// if someone hacked the Boolean class this check could be helpful
	private static final Object TRUE = Boolean.TRUE == FALSE ? new Object() : Boolean.TRUE;
	// memory to memorize, which IntInts are equal and which are not
	private transient Map <IntIntImpl, Object> equals;
	// saves the hash
	private transient int h;
	
	@Override
	public int hashCode() {
		if (equals == null) {
			equals = new HashMap <>();
			final int prime = 31;
			int hash = 1;
			hash = prime * hash + a;
			hash = prime * hash + b;
			h = hash;
		}
		return h;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		IntIntImpl other = (IntIntImpl) obj;
		if (hashCode() != other.hashCode()) {
			return false;// to not need to save, because hash code is unchangeable
		}
		Object bool = equals.get(other);
		if (bool != null) return bool == TRUE;
		bool = other.equals.get(this);
		if (bool != null) return bool == TRUE;
		if (this.a != other.a || this.b != other.b) {
			equals.put(other, FALSE);
			return false;
		}
		equals.put(other, TRUE);
		return true;
		
	}
	
	@Override
	public String toString() {
		return "IntIntImpl[a=" + a + ", b=" + b + "]";
	}
	
	public class ReverseIntIntImpl implements IntInt {
		
		/** UID */
		private static final long serialVersionUID = -579760959814455169L;
		
		@Override
		public int getA() {
			return IntIntImpl.this.b;
		}
		
		@Override
		public void setA(int a) {
			IntIntImpl.this.b = a;
		}
		
		@Override
		public void addA(int val) {
			IntIntImpl.this.b += val;
		}
		
		@Override
		public void subA(int val) {
			IntIntImpl.this.b -= val;
		}
		
		@Override
		public void incA() {
			IntIntImpl.this.b ++ ;
		}
		
		@Override
		public void decA() {
			IntIntImpl.this.b ++ ;
		}
		
		@Override
		public int getB() {
			return IntIntImpl.this.a;
		}
		
		@Override
		public void setB(int b) {
			IntIntImpl.this.a = b;
		}
		
		@Override
		public void addB(int b) {
			IntIntImpl.this.a += b;
		}
		
		@Override
		public void subB(int b) {
			IntIntImpl.this.a -= b;
		}
		
		@Override
		public void incB() {
			IntIntImpl.this.a ++ ;
		}
		
		@Override
		public void decB() {
			IntIntImpl.this.a -- ;
		}
		
		@Override
		public void setBoth(IntInt val) {
			IntIntImpl.this.b = val.getA();
			IntIntImpl.this.a = val.getB();
		}
		
		public void setBoth(IntIntImpl val) {
			IntIntImpl.this.b = val.a;
			IntIntImpl.this.a = val.b;
		}
		
		@Override
		public void setBoth(int a, int b) {
			IntIntImpl.this.b = a;
			IntIntImpl.this.a = b;
		}
		
		@Override
		public void setBoth(int val) {
			IntIntImpl.this.b = val;
			IntIntImpl.this.a = val;
		}
		
		@Override
		public void addBoth(IntInt val) {
			IntIntImpl.this.b += val.getA();
			IntIntImpl.this.a += val.getB();
		}
		
		public void addBoth(IntIntImpl val) {
			IntIntImpl.this.b += val.a;
			IntIntImpl.this.a += val.b;
		}
		
		@Override
		public void addBoth(int a, int b) {
			IntIntImpl.this.b += a;
			IntIntImpl.this.a += b;
		}
		
		@Override
		public void addBoth(int val) {
			IntIntImpl.this.b += a;
			IntIntImpl.this.a += b;
		}
		
		@Override
		public void subBoth(IntInt val) {
			IntIntImpl.this.b -= val.getA();
			IntIntImpl.this.a -= val.getB();
		}
		
		public void subBoth(IntIntImpl val) {
			IntIntImpl.this.b -= val.a;
			IntIntImpl.this.a -= val.b;
		}
		
		@Override
		public void subBoth(int a, int b) {
			IntIntImpl.this.b -= a;
			IntIntImpl.this.a -= b;
		}
		
		@Override
		public void subBoth(int val) {
			IntIntImpl.this.b -= val;
			IntIntImpl.this.a -= val;
		}
		
		@Override
		public void incBoth() {
			IntIntImpl.this.b ++ ;
			IntIntImpl.this.a ++ ;
		}
		
		@Override
		public void decBoth() {
			IntIntImpl.this.b -- ;
			IntIntImpl.this.a -- ;
		}
		
		@Override
		public int sum() {
			return IntIntImpl.this.b + IntIntImpl.this.a;
		}
		
		@Override
		public boolean isAGreather(int c) {
			return IntIntImpl.this.b > c;
		}
		
		@Override
		public boolean isAGreatherEqual(int c) {
			return IntIntImpl.this.b >= c;
		}
		
		@Override
		public boolean isASmaller(int c) {
			return IntIntImpl.this.b < c;
		}
		
		@Override
		public boolean isASmallerEqual(int c) {
			return IntIntImpl.this.b <= c;
		}
		
		@Override
		public boolean isA(int c) {
			return IntIntImpl.this.b == c;
		}
		
		@Override
		public boolean isAGreatherB() {
			return IntIntImpl.this.b > IntIntImpl.this.a;
		}
		
		@Override
		public boolean isAGreatherEqualB() {
			return IntIntImpl.this.b >= IntIntImpl.this.a;
		}
		
		@Override
		public boolean isASmallerB() {
			return IntIntImpl.this.b < IntIntImpl.this.a;
		}
		
		@Override
		public boolean isASmallerEqualB() {
			return IntIntImpl.this.b <= IntIntImpl.this.a;
		}
		
		@Override
		public boolean isANull() {
			return IntIntImpl.this.b == 0;
		}
		
		@Override
		public boolean isBGreather(int c) {
			return IntIntImpl.this.a > c;
		}
		
		@Override
		public boolean isBGreatherEqual(int c) {
			return IntIntImpl.this.a >= c;
		}
		
		@Override
		public boolean isBSmaller(int c) {
			return IntIntImpl.this.a < c;
		}
		
		@Override
		public boolean isBSmallerEqual(int c) {
			return IntIntImpl.this.a <= c;
		}
		
		@Override
		public boolean isB(int c) {
			return IntIntImpl.this.a == c;
		}
		
		@Override
		public boolean isBGreatherA() {
			return IntIntImpl.this.a > IntIntImpl.this.b;
		}
		
		@Override
		public boolean isBGreatherEqualA() {
			return IntIntImpl.this.a >= IntIntImpl.this.b;
		}
		
		@Override
		public boolean isBSmallerA() {
			return IntIntImpl.this.a < IntIntImpl.this.b;
		}
		
		@Override
		public boolean isBSmallerEqualA() {
			return IntIntImpl.this.a <= IntIntImpl.this.b;
		}
		
		@Override
		public boolean isBNull() {
			return IntIntImpl.this.a == 0;
		}
		
		@Override
		public boolean bothNull() {
			return IntIntImpl.this.b == 0 && IntIntImpl.this.a == 0;
		}
		
		@Override
		public boolean bothSame() {
			return IntIntImpl.this.b == IntIntImpl.this.a;
		}
		
		@Override
		public boolean same(int a, int b) {
			return IntIntImpl.this.b == a && IntIntImpl.this.a == b;
		}
		
		@Override
		public boolean same(IntInt other) {
			return IntIntImpl.this.b == other.getA() && IntIntImpl.this.a == other.getB();
		}
		
		public boolean same(IntIntImpl other) {
			return IntIntImpl.this.b == other.a && IntIntImpl.this.a == other.b;
		}
		
		@Override
		public IntIntImpl reverse() {
			return IntIntImpl.this;
		}
		
		@Override
		public ReverseIntIntImpl clone() {
			return IntIntImpl.this.clone().reverse();
		}
		
		@Override
		public String toString() {
			return "IntIntRevI[a=" + IntIntImpl.this.b + ", b=" + IntIntImpl.this.a + "]";
		}
		
	}
	
}

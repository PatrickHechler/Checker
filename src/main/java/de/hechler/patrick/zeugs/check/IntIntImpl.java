package de.hechler.patrick.zeugs.check;

import java.util.HashMap;
import java.util.Map;

public class IntIntImpl implements IntInt {
	
	/** UID */
	private static final long serialVersionUID = 6961158138373138854L;
	
	
	
	/**
	 * the first {@code int} value
	 */
	private int                         a;
	/**
	 * the second {@code int} value
	 */
	private int                         b;
	/**
	 * the reversed {@link IntInt} of this {@link IntIntImpl}
	 */
	private transient ReverseIntIntImpl reverse;
	
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
	
	@Override
	public int getFirst() {
		return this.a;
	}
	
	@Override
	public void setFirst(int a) {
		this.a = a;
	}
	
	@Override
	public void addFirst(int val) {
		this.a += val;
	}
	
	@Override
	public void subFirst(int val) {
		this.a -= val;
	}
	
	@Override
	public void incFirst() {
		this.a ++ ;
	}
	
	
	@Override
	public void decFirst() {
		this.a -- ;
	}
	
	@Override
	public int getSecond() {
		return this.b;
	}
	
	@Override
	public void setSecond(int b) {
		this.b = b;
	}
	
	@Override
	public void addSecond(int b) {
		this.b += b;
	}
	
	@Override
	public void subSecond(int b) {
		this.b -= b;
	}
	
	@Override
	public void incSecond() {
		this.b ++ ;
	}
	
	@Override
	public void decSecond() {
		this.b -- ;
	}
	
	@Override
	public void setBoth(IntInt val) {
		this.a = val.getFirst();
		this.b = val.getSecond();
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
	public void setBothNull() {
		this.a = 0;
		this.b = 0;
	}
	
	@Override
	public void addBoth(IntInt val) {
		this.a += val.getFirst();
		this.b += val.getSecond();
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
		this.a -= val.getFirst();
		this.b -= val.getSecond();
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
	public boolean isFirstGreather(int c) {
		return this.a > c;
	}
	
	@Override
	public boolean isFirstGreatherEqual(int c) {
		return this.a >= c;
	}
	
	@Override
	public boolean isFirstSmaller(int c) {
		return this.a < c;
	}
	
	@Override
	public boolean isFirstSmallerEqual(int c) {
		return this.a <= c;
	}
	
	@Override
	public boolean isFirstEqual(int c) {
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
	public boolean isFirstZero() {
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
		return this.a == other.getFirst() && this.b == other.getSecond();
	}
	
	public boolean same(IntIntImpl other) {
		if (other == null) return false;
		return this.a == other.a && this.b == other.b;
	}
	
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
		} catch (CloneNotSupportedException | ClassCastException e) {
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
	
	@Override
	public int compareWithA(int c) {
		return this.a > c ? 1 : this.a == c ? 0 : -1;
	}
	
	@Override
	public int compareWithB(int c) {
		return this.b > c ? 1 : this.b == c ? 0 : -1;
	}
	
	@Override
	public int compareTo(IntInt o) {
		int zw = o.getFirst();
		if (this.a < zw) {
			return -1;
		} else if (this.a > zw) {
			return 1;
		} else {
			zw = o.getSecond();
			if (this.b < zw) {
				return -1;
			} else if (this.b > zw) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	public class ReverseIntIntImpl implements IntInt {
		
		/** UID */
		private static final long serialVersionUID = -579760959814455169L;
		
		/**
		 * the {@link ReverseIntIntImpl} is only accessible with the {@link #reverse()} method of the {@link IntIntImpl}
		 */
		protected ReverseIntIntImpl() {}
		
		@Override
		public int getFirst() {
			return IntIntImpl.this.b;
		}
		
		@Override
		public void setFirst(int a) {
			IntIntImpl.this.b = a;
		}
		
		@Override
		public void addFirst(int val) {
			IntIntImpl.this.b += val;
		}
		
		@Override
		public void subFirst(int val) {
			IntIntImpl.this.b -= val;
		}
		
		@Override
		public void incFirst() {
			IntIntImpl.this.b ++ ;
		}
		
		@Override
		public void decFirst() {
			IntIntImpl.this.b ++ ;
		}
		
		@Override
		public int getSecond() {
			return IntIntImpl.this.a;
		}
		
		@Override
		public void setSecond(int b) {
			IntIntImpl.this.a = b;
		}
		
		@Override
		public void addSecond(int b) {
			IntIntImpl.this.a += b;
		}
		
		@Override
		public void subSecond(int b) {
			IntIntImpl.this.a -= b;
		}
		
		@Override
		public void incSecond() {
			IntIntImpl.this.a ++ ;
		}
		
		@Override
		public void decSecond() {
			IntIntImpl.this.a -- ;
		}
		
		@Override
		public void setBoth(IntInt val) {
			IntIntImpl.this.b = val.getFirst();
			IntIntImpl.this.a = val.getSecond();
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
		public void setBothNull() {
			IntIntImpl.this.b = 0;
			IntIntImpl.this.a = 0;
		}
		
		@Override
		public void addBoth(IntInt val) {
			IntIntImpl.this.b += val.getFirst();
			IntIntImpl.this.a += val.getSecond();
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
			IntIntImpl.this.b -= val.getFirst();
			IntIntImpl.this.a -= val.getSecond();
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
		public boolean isFirstGreather(int c) {
			return IntIntImpl.this.b > c;
		}
		
		@Override
		public boolean isFirstGreatherEqual(int c) {
			return IntIntImpl.this.b >= c;
		}
		
		@Override
		public boolean isFirstSmaller(int c) {
			return IntIntImpl.this.b < c;
		}
		
		@Override
		public boolean isFirstSmallerEqual(int c) {
			return IntIntImpl.this.b <= c;
		}
		
		@Override
		public boolean isFirstEqual(int c) {
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
		public boolean isFirstZero() {
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
			return IntIntImpl.this.b == other.getFirst() && IntIntImpl.this.a == other.getSecond();
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
			try {
				return ((IntIntImpl) IntIntImpl.super.clone()).reverse();
			} catch (CloneNotSupportedException | ClassCastException e) {
				return new IntIntImpl(IntIntImpl.this).reverse();
			}
		}
		
		@Override
		public String toString() {
			return "IntIntRevI[a=" + IntIntImpl.this.b + ", b=" + IntIntImpl.this.a + "]";
		}
		
		@Override
		public int compareWithA(int c) {
			return IntIntImpl.this.b > c ? 1 : IntIntImpl.this.b == c ? 0 : -1;
		}
		
		@Override
		public int compareWithB(int c) {
			return IntIntImpl.this.a > c ? 1 : IntIntImpl.this.a == c ? 0 : -1;
		}
		
		@Override
		public int compareTo(IntInt o) {
			int zw = o.getFirst();
			if (IntIntImpl.this.b < zw) {
				return -1;
			} else if (IntIntImpl.this.b > zw) {
				return 1;
			} else {
				zw = o.getSecond();
				if (IntIntImpl.this.a < zw) {
					return -1;
				} else if (IntIntImpl.this.a > zw) {
					return 1;
				} else {
					return 0;
				}
			}
		}

		@Override
		public boolean isFirstNotEqual(int c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isFirstNotZero() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isFirstPositive() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isFirstNotPositive() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isFirstNegative() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isFirstNotNegative() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isASignumEqual(int c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isASignumNotEqual(int c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isASignumEqualA() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isASignumNotEqualA() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int aSignum() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isBNotNull() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isBPositive() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isBNotPositive() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isBNegative() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isBNotNegative() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isBSignumEqual(int c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isBSignumNotEqual(int c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isBSignumEqualB() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isBSignumNotEqualB() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int bSignum() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean bothNotNull() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean bothPositive() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean bothNegative() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean bothSameSignums() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean bothDiffrentSignums() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean diffrentValues() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

	@Override
	public boolean isFirstNotEqual(int c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFirstNotZero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFirstPositive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFirstNotPositive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFirstNegative() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFirstNotNegative() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isASignumEqual(int c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isASignumNotEqual(int c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isASignumEqualA() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isASignumNotEqualA() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int aSignum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isBNotNull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBPositive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBNotPositive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBNegative() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBNotNegative() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBSignumEqual(int c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBSignumNotEqual(int c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBSignumEqualB() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBSignumNotEqualB() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int bSignum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean bothNotNull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bothPositive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bothNegative() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bothSameSignums() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bothDiffrentSignums() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean diffrentValues() {
		// TODO Auto-generated method stub
		return false;
	}
	
}

package de.hechler.patrick.zeugs.check;


public class ReverseIntInt extends AbstractIntInt {
	
	/** UID */
	private static final long serialVersionUID = 1516325567062312472L;
	
	
	
	private IntInt reverse;
	
	
	
	public ReverseIntInt(IntInt reverse) {
		this.reverse = reverse;
	}
	
	@Override
	public int getFirst() {
		return reverse.getSecond();
	}
	
	@Override
	public void setFirst(int a) {
		reverse.setSecond(a);
	}
	
	@Override
	public void addFirst(int val) {
		reverse.addSecond(val);
	}
	
	@Override
	public void subFirst(int val) {
		reverse.subSecond(val);
	}
	
	@Override
	public void incFirst() {
		reverse.incSecond();
	}
	
	@Override
	public void decFirst() {
		reverse.decSecond();
	}
	
	@Override
	public void setFirstWithSecond() {
		reverse.setSecondWithFirst();
	}
	
	@Override
	public void addFirstWithSecond() {
		reverse.addSecondWithFirst();
	}
	
	@Override
	public void subFirstWithSecond() {
		reverse.subSecondWithFirst();
	}
	
	@Override
	public int getSecond() {
		return reverse.getFirst();
	}
	
	@Override
	public void setSecond(int b) {
		reverse.setFirst(b);
	}
	
	@Override
	public void addSecond(int b) {
		reverse.addFirst(b);
	}
	
	@Override
	public void subSecond(int b) {
		reverse.subFirst(b);
	}
	
	@Override
	public void incSecond() {
		reverse.incFirst();
	}
	
	@Override
	public void decSecond() {
		reverse.decFirst();
	}
	
	@Override
	public void setSecondWithFirst() {
		reverse.setFirstWithSecond();
	}
	
	@Override
	public void addSecondWithFirst() {
		reverse.addFirstWithSecond();
	}
	
	@Override
	public void subSecondWithFirst() {
		reverse.subFirstWithSecond();
	}
	
	@Override
	public void setBoth(IntInt val) {
		reverse.setBoth(val.reverse());
	}
	
	@Override
	public void setBoth(int first, int second) {
		reverse.setBoth(second, first);
	}
	
	@Override
	public void setBoth(int val) {
		reverse.setBoth(val);
	}
	
	@Override
	public void setBothNull() {
		reverse.setBothNull();
	}
	
	@Override
	public void addBoth(IntInt val) {
		reverse.addBoth(val.reverse());
	}
	
	@Override
	public void addBoth(int first, int second) {
		reverse.addBoth(second, first);
	}
	
	@Override
	public void addBoth(int val) {
		reverse.addBoth(val);
	}
	
	@Override
	public void subBoth(IntInt val) {
		reverse.setBoth(val.reverse());
	}
	
	@Override
	public void subBoth(int first, int second) {
		reverse.subBoth(second, first);
	}
	
	@Override
	public void subBoth(int val) {
		reverse.subBoth(val);
	}
	
	@Override
	public void incBoth() {
		reverse.incBoth();
	}
	
	@Override
	public void decBoth() {
		reverse.decBoth();
	}
	
	@Override
	public int sum() {
		return reverse.sum();
	}
	
	@Override
	public int dif() {
		return reverse.dif();
	}
	
	@Override
	public boolean isFirstGreather(int c) {
		return reverse.isBGreather(c);
	}
	
	@Override
	public boolean isFirstGreatherEqual(int c) {
		return reverse.isBGreatherEqual(c);
	}
	
	@Override
	public boolean isFirstSmaller(int c) {
		return reverse.isBSmaller(c);
	}
	
	@Override
	public boolean isFirstSmallerEqual(int c) {
		return reverse.isBSmallerEqual(c);
	}
	
	@Override
	public boolean isFirstEqual(int c) {
		return reverse.isB(c);
	}
	
	@Override
	public boolean isFirstNotEqual(int c) {
		return reverse.isBNot(c);
	}
	
	@Override
	public boolean isFirstZero() {
		return reverse.isBNull();
	}
	
	@Override
	public boolean isFirstNotZero() {
		return reverse.isBNotNull();
	}
	
	@Override
	public boolean isFirstPositive() {
		return reverse.isBPositive();
	}
	
	@Override
	public boolean isFirstNotPositive() {
		return reverse.isBNotPositive();
	}
	
	@Override
	public boolean isFirstNegative() {
		return reverse.isBNegative();
	}
	
	@Override
	public boolean isFirstNotNegative() {
		return reverse.isBNotNegative();
	}
	
	@Override
	public boolean isASignumEqual(int c) {
		return reverse.isBSignumEqual(c);
	}
	
	@Override
	public boolean isASignumNotEqual(int c) {
		return reverse.isBSignumNotEqual(c);
	}
	
	@Override
	public boolean isASignumEqualA() {
		return reverse.isBSignumEqualB();
	}
	
	@Override
	public boolean isASignumNotEqualA() {
		return reverse.isBSignumNotEqualB();
	}
	
	@Override
	public int aSignum() {
		return reverse.bSignum();
	}
	
	@Override
	public int compareWithA(int c) {
		return reverse.compareWithB(c);
	}
	
	@Override
	public boolean isAGreatherB() {
		return reverse.isBGreatherA();
	}
	
	@Override
	public boolean isAGreatherEqualB() {
		return reverse.isBGreatherEqualA();
	}
	
	@Override
	public boolean isASmallerB() {
		return reverse.isBSmallerA();
	}
	
	@Override
	public boolean isASmallerEqualB() {
		return reverse.isBSmallerEqualA();
	}
	
	@Override
	public boolean isBGreather(int c) {
		return reverse.isFirstGreather(c);
	}
	
	@Override
	public boolean isBGreatherEqual(int c) {
		return reverse.isFirstGreatherEqual(c);
	}
	
	@Override
	public boolean isBSmaller(int c) {
		return reverse.isFirstSmaller(c);
	}
	
	@Override
	public boolean isBSmallerEqual(int c) {
		return reverse.isFirstSmallerEqual(c);
	}
	
	@Override
	public boolean isB(int c) {
		return reverse.isFirstEqual(c);
	}
	
	@Override
	public boolean isBNot(int c) {
		return reverse.isFirstNotEqual(c);
	}
	
	@Override
	public boolean isBNull() {
		return reverse.isFirstZero();
	}
	
	@Override
	public boolean isBNotNull() {
		return reverse.isFirstNotZero();
	}
	
	@Override
	public boolean isBPositive() {
		return reverse.isFirstPositive();
	}
	
	@Override
	public boolean isBNotPositive() {
		return reverse.isFirstNotPositive();
	}
	
	@Override
	public boolean isBNegative() {
		return reverse.isFirstNegative();
	}
	
	@Override
	public boolean isBNotNegative() {
		return reverse.isFirstNotNegative();
	}
	
	@Override
	public boolean isBSignumEqual(int c) {
		return reverse.isASignumEqual(c);
	}
	
	@Override
	public boolean isBSignumNotEqual(int c) {
		return reverse.isASignumNotEqual(c);
	}
	
	@Override
	public boolean isBSignumEqualB() {
		return reverse.isASignumEqualA();
	}
	
	@Override
	public boolean isBSignumNotEqualB() {
		return reverse.isASignumNotEqualA();
	}
	
	@Override
	public int bSignum() {
		return reverse.aSignum();
	}
	
	@Override
	public int compareWithB(int c) {
		return reverse.compareWithA(c);
	}
	
	@Override
	public boolean isBGreatherA() {
		return reverse.isAGreatherB();
	}
	
	@Override
	public boolean isBGreatherEqualA() {
		return reverse.isAGreatherEqualB();
	}
	
	@Override
	public boolean isBSmallerEqualA() {
		return reverse.isASmallerEqualB();
	}
	
	@Override
	public boolean isBSmallerA() {
		return reverse.isASmallerB();
	}
	
	@Override
	public boolean bothNull() {
		return reverse.isFirstZero();
	}
	
	@Override
	public boolean bothNotNull() {
		return reverse.isFirstNotZero();
	}
	
	@Override
	public boolean bothPositive() {
		return reverse.bothPositive();
	}
	
	@Override
	public boolean bothNegative() {
		return reverse.bothNegative();
	}
	
	@Override
	public boolean bothSame() {
		return reverse.bothSame();
	}
	
	@Override
	public boolean bothSameSignums() {
		return reverse.bothSameSignums();
	}
	
	@Override
	public boolean bothDiffrentSignums() {
		return reverse.bothDiffrentSignums();
	}
	
	@Override
	public boolean diffrentValues() {
		return reverse.diffrentValues();
	}
	
	@Override
	public boolean same(int val) {
		return reverse.same(val);
	}
	
	@Override
	public boolean same(int a, int b) {
		return reverse.same(b, a);
	}
	
	@Override
	public boolean same(IntInt other) {
		return reverse.same(other.reverse());
	}
	
	@Override
	public int compareTo(IntInt o) {
		return reverse.compareTo(o.reverse());
	}
	
}

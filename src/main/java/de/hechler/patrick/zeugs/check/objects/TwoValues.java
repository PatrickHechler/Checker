package de.hechler.patrick.zeugs.check.objects;

import java.util.Objects;

import de.hechler.patrick.zeugs.check.interfaces.TwoVals;

public record TwoValues<A, B> (A valA, B valB) implements TwoVals<A, B> {
	
	@Override
	public int hashCode() { return TwoVals.hashCode(valA) ^ TwoVals.hashCode(valB); }
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null) return false;
		if (!(obj instanceof TwoVals<?, ?> ce)) return false;
		return Objects.deepEquals(valA, ce.valA()) && Objects.deepEquals(valB, ce.valB());
	}
	
}

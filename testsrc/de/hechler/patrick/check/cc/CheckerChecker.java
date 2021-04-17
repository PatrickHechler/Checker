package de.hechler.patrick.check.cc;

import de.hechler.patrick.zeugs.check.Checker;
import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;
import de.hechler.patrick.zeugs.check.anotations.End;
import de.hechler.patrick.zeugs.check.anotations.Start;
import de.hechler.patrick.zeugs.check.exceptions.CheckerException;
import de.hechler.patrick.zeugs.check.exceptions.CheckerFailException;

@CheckClass
public class CheckerChecker extends Checker {
	
	@Start(onlyOnce = true)
	public void start2() {
		System.out.println("init");
	}
	
	@Start(onlyOnce = true)
	public void start3() {
		System.out.println("other init");
	}
	
	@Start
	public void start1() {
		System.out.println("  check starts");
	}
	
	@Check
	public void check1() {
		System.out.println("    hello world");
	}
	
	@Check(disabled = true)
	public void check2() {
		System.out.println("not called");
	}
	
	@Check(disabled = false)
	public void check3() {
		System.out.println("    check3");
	}
	
	@Check
	public void check4() {
		System.out.println("    check4");
	}
	
	@Check
	public void checkAssertEquals() {
		System.out.println("    checkAssertEquals");
		assertEquals((byte) 5, (byte) 5);
		assertEquals((short) 5, (short) 5);
		assertEquals(5, 5);
		assertEquals(5l, 5l);
		assertEquals(5.0f, 5.0f);
		assertEquals(5.0d, 5.0d);
		Object a, b;
		a = b = new Object();
		assertEquals(a, b);
		final Object wrong = new Object();
		assertThrowsAny(() -> assertEquals(a, wrong));
		assertThrows(CheckerException.class, () -> assertEquals(a, wrong));
	}
	
	@Check
	public void checkAssertArrayEquals() {
		System.out.println("    checkAssertArrayEquals");
		byte[] bytesA = new byte[] {4, 5, 89 };
		byte[] bytesB = new byte[] {4, 5, 89 };
		byte[] bytesC = new byte[] {4, 89 };
		byte[] bytesD = new byte[] {4, 89 };
		assertArrayEquals(bytesA, bytesA);
		assertArrayEquals(bytesB, bytesB);
		assertArrayEquals(bytesC, bytesC);
		assertArrayEquals(bytesD, bytesD);
		assertArrayEquals(bytesA, bytesB);
		assertArrayEquals(bytesC, bytesD);
		assertThrowsAny(() -> assertArrayEquals(bytesA, bytesD));
		assertThrowsAny(() -> assertArrayEquals(bytesA, bytesC));
		assertThrowsAny(() -> assertArrayEquals(bytesB, bytesC));
		assertThrowsAny(() -> assertArrayEquals(bytesB, bytesD));
		short[] shortsA = new short[] {4, 5, 89 };
		short[] shortsB = new short[] {4, 5, 89 };
		short[] shortsC = new short[] {4, 89 };
		short[] shortsD = new short[] {4, 89 };
		assertArrayEquals(shortsA, shortsA);
		assertArrayEquals(shortsB, shortsB);
		assertArrayEquals(shortsC, shortsC);
		assertArrayEquals(shortsD, shortsD);
		assertArrayEquals(shortsA, shortsB);
		assertArrayEquals(shortsC, shortsD);
		assertThrowsAny(() -> assertArrayEquals(shortsA, shortsD));
		assertThrowsAny(() -> assertArrayEquals(shortsA, shortsC));
		assertThrowsAny(() -> assertArrayEquals(shortsB, shortsC));
		assertThrowsAny(() -> assertArrayEquals(shortsB, shortsD));
		int[] intsA = new int[] {4, 5, 89 };
		int[] intsB = new int[] {4, 5, 89 };
		int[] intsC = new int[] {4, 89 };
		int[] intsD = new int[] {4, 89 };
		assertArrayEquals(intsA, intsA);
		assertArrayEquals(intsB, intsB);
		assertArrayEquals(intsC, intsC);
		assertArrayEquals(intsD, intsD);
		assertArrayEquals(intsA, intsB);
		assertArrayEquals(intsC, intsD);
		assertThrowsAny(() -> assertArrayEquals(intsA, intsD));
		assertThrowsAny(() -> assertArrayEquals(intsA, intsC));
		assertThrowsAny(() -> assertArrayEquals(intsB, intsC));
		assertThrowsAny(() -> assertArrayEquals(intsB, intsD));
		long[] longsA = new long[] {4, 5, 89 };
		long[] longsB = new long[] {4, 5, 89 };
		long[] longsC = new long[] {4, 89 };
		long[] longsD = new long[] {4, 89 };
		assertArrayEquals(longsA, longsA);
		assertArrayEquals(longsB, longsB);
		assertArrayEquals(longsC, longsC);
		assertArrayEquals(longsD, longsD);
		assertArrayEquals(longsA, longsB);
		assertArrayEquals(longsC, longsD);
		assertThrowsAny(() -> assertArrayEquals(longsA, longsD));
		assertThrowsAny(() -> assertArrayEquals(longsA, longsC));
		assertThrowsAny(() -> assertArrayEquals(longsB, longsC));
		assertThrowsAny(() -> assertArrayEquals(longsB, longsD));
		float[] floatsA = new float[] {4, 5, 89 };
		float[] floatsaB = new float[] {4, 5, 89 };
		float[] floatsC = new float[] {4, 89 };
		float[] floatsD = new float[] {4, 89 };
		assertArrayEquals(floatsA, floatsA);
		assertArrayEquals(floatsaB, floatsaB);
		assertArrayEquals(floatsC, floatsC);
		assertArrayEquals(floatsD, floatsD);
		assertArrayEquals(floatsA, floatsaB);
		assertArrayEquals(floatsC, floatsD);
		assertThrowsAny(() -> assertArrayEquals(floatsA, floatsD));
		assertThrowsAny(() -> assertArrayEquals(floatsA, floatsC));
		assertThrowsAny(() -> assertArrayEquals(floatsaB, floatsC));
		assertThrowsAny(() -> assertArrayEquals(floatsaB, floatsD));
		double[] doualbesA = new double[] {4, 5, 89 };
		double[] doualbesB = new double[] {4, 5, 89 };
		double[] douablesC = new double[] {4, 89 };
		double[] douablesD = new double[] {4, 89 };
		assertArrayEquals(doualbesA, doualbesA);
		assertArrayEquals(doualbesB, doualbesB);
		assertArrayEquals(douablesC, douablesC);
		assertArrayEquals(douablesD, douablesD);
		assertArrayEquals(doualbesA, doualbesB);
		assertArrayEquals(douablesC, douablesD);
		assertThrowsAny(() -> assertArrayEquals(doualbesA, douablesD));
		assertThrowsAny(() -> assertArrayEquals(doualbesA, douablesC));
		assertThrowsAny(() -> assertArrayEquals(doualbesB, douablesC));
		assertThrowsAny(() -> assertArrayEquals(doualbesB, douablesD));
	}
	
	@Check()
	public void checkFail() {
		System.out.println("    checkFail");
		assertThrows(CheckerFailException.class, () -> fail(null));
		assertThrows(CheckerFailException.class, () -> fail(""));
		assertThrows(CheckerFailException.class, () -> fail("fail"));
	}
	
	@End
	public void end1() {
		System.out.println("  check ends");
	}
	
	@End(onlyOnce = true)
	public void end2() {
		System.out.println("finalization");
	}
	
	@End
	public void end3() {
		System.out.println("  other check ends");
	}
	
	public void nothing() {
		System.out.println("also not called");
	}
	
}

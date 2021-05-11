package de.hechler.patrick.check.cc;

import de.hechler.patrick.zeugs.check.Checker;
import de.hechler.patrick.zeugs.check.anotations.*;

@CheckClass
public class ResultParamChecker {
	
	@Start
	public ResultParamChecker(@ParamCreater(method = {"construct" }) String sting) {
		Checker.assertEquals("CALL", sting);
	}
	
	@SuppressWarnings("unused")
	private static Object construct() {
		return "CALL";
	}
	
	@Check
	private void nothing() {
	}
	
	@Check
	private void nothing(@ParamCreater(method = {"nothing", "java.lang.String" }) String str, Object nix) {
		Checker.assertNull(nix);
		Checker.assertEquals("stingsting", str);
	}
	
	@Check
	private void nothing(@ParamCreater(method = {"nothing", "java.lang.String" }) String str, int... is) {
		Checker.assertEquals("stingsting", str);
		Checker.assertArrayEquals(new int[0], is);
	}
	
	@Check
	private String nothing(@ParamCreater(method = {"str" }) String str) {
		Checker.assertEquals("sting", str);
		return str + str;
	}
	
	@SuppressWarnings("unused")
	private String str() {
		return "sting";
	}
	
}

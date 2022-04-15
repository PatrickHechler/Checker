package de.hechler.patrick.check.cc;

import de.hechler.patrick.zeugs.check.Assert;
import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;
import de.hechler.patrick.zeugs.check.anotations.ParamCreater;
import de.hechler.patrick.zeugs.check.anotations.Start;

@CheckClass
public class ResultParamChecker {
	
	@Start
	public ResultParamChecker(@ParamCreater(method = "construct") String sting) {
		Assert.assertEquals("CALL", sting);
	}
	
	@SuppressWarnings("unused")
	private static Object construct() {
		return "CALL";
	}
	
	@Check
	private void nothing() {
	}
	
	@Check
	private Object nothing(@ParamCreater(method = "nothing", methodParams = "java.lang.String") String str, Object nix) {
		Assert.assertNull(nix);
		Assert.assertEquals("stingsting", str);
		return null;
	}
	
	@Check
	private void nothing(@ParamCreater(method = "nothing", methodParams = {"java.lang.String" }) String str, int... is) {
		Assert.assertEquals("stingsting", str);
		Assert.assertArrayEquals(new int[0], is);
	}
	
	@Check
	private String nothing(@ParamCreater(method = "str", methodParams = {}) String str) {
		Assert.assertEquals("sting", str);
		return str + str;
	}
	
	@SuppressWarnings("unused")
	private String str() {
		return "sting";
	}
	
}

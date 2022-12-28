package de.hechler.patrick.zeugs.check.objects;

import de.hechler.patrick.zeugs.check.Assert;
import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;
import de.hechler.patrick.zeugs.check.anotations.ParamCreater;
import de.hechler.patrick.zeugs.check.anotations.Start;

@CheckClass
@SuppressWarnings({ "static-method", "unused" })
public class ResultParamChecker {
	
	@Start
	public ResultParamChecker(@ParamCreater(method = "construct") String sting) { Assert.assertEquals("CALL", sting); }
	
	private static Object construct() { return "CALL"; }
	
	@Check
	private void nothing() {}
	
	@Check
	private Object nothing(@ParamCreater(method = "nothing", params = String.class) String str, @ParamCreater(method = "nothing") Object nix) {
		Assert.assertNull(nix);
		Assert.assertEquals("stingsting", str);
		return null;
	}
	
	@Check
	private void nothing(@ParamCreater(method = "nothing", params = { String.class }) String str, @ParamCreater(method = "nothing") int... is) {
		Assert.assertEquals("stingsting", str);
		Assert.assertNull(is);
	}
	
	@Check
	private String nothing(@ParamCreater(method = "str", params = {}) String str) {
		Assert.assertEquals("sting", str);
		return str + str;
	}
	
	private String str() { return "sting"; }
	
}

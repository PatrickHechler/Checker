package de.hechler.patrick.zeugs.check.objects;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hechler.patrick.zeugs.check.Assert;
import de.hechler.patrick.zeugs.check.ParamCreaterHelp;
import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;
import de.hechler.patrick.zeugs.check.anotations.ParamCreater;
import de.hechler.patrick.zeugs.check.anotations.ParamInfo;
import de.hechler.patrick.zeugs.check.anotations.Start;

@CheckClass
@SuppressWarnings({ "static-method", "unused" })
public class ParamChecker {
	
	@Start
	public ParamChecker(@ParamCreater(method = "construct") String sting) { Assert.assertEquals("CALL", sting); }
	
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
	
	@Check
	private void checkParamCreaterWithArray() {
		WithArray   i = new WithArray();
		CheckResult r = new Checker(i).get();
		Assert.assertTrue(r.wentExpected());
		String[] vs = i.values.toArray(new String[i.values.size()]);
		Assert.assertArrayEquals((String[]) i.addValues(), vs);
	}
	
	private class WithArray {
		
		private List<String> values = new ArrayList<>();
		
		@Check
		private void append(@ParamCreater(method = "addValues") String value) { values.add(value); }
		
		private Object addValues() { return new String[] { "hello", "world", "", "this is nice", "!" }; }
		
	}
	
	@Check
	private void checkParamCreaterWithIterable() {
		WithList    i = new WithList();
		CheckResult r = new Checker(i).get();
		Assert.assertTrue(r.wentExpected());
		String[] vs = i.values.toArray(new String[i.values.size()]);
		Assert.assertArrayEquals(i.addValues().toArray(l -> new String[l]), vs);
	}
	
	private class WithList {
		
		private List<String> values = new ArrayList<>();
		
		@Check
		private void append(@ParamCreater(method = "addValues") String value) { values.add(value); }
		
		private List<String> addValues() { return Arrays.asList(new String[] { "hello", "world", "", "this is nice", "!" }); }
		
	}
	
	private static final String[] FILE_RES_TEST_LINES = new String[] { "hello world", "this is a nice", "  test file",
			"  here is some whitespace:        ", "  ", "", "", "and some empty lines  ", " " };
	
	@Check
	private void checkParamCreaterWithFile() {
		WithFile    i = new WithFile();
		CheckResult r = new Checker(i).get();
		Assert.assertTrue(r.wentExpected());
		String[] vs = i.values.toArray(new String[i.values.size()]);
		Assert.assertArrayEquals(FILE_RES_TEST_LINES, vs);
	}
	
	@Check
	private void checkParamCreaterWithRes() {
		WithRes     i = new WithRes();
		CheckResult r = new Checker(i).get();
		Assert.assertTrue(r.wentExpected());
		String[] vs = i.values.toArray(new String[i.values.size()]);
		Assert.assertArrayEquals(FILE_RES_TEST_LINES, vs);
	}
	
	private class WithFile {
		
		private List<String> values = new ArrayList<>();
		
		@Check
		private void append(
				@ParamCreater(method = "linesOfFile", clas = ParamCreaterHelp.class, params = Parameter.class) @ParamInfo("./testin/testfile.txt") String value) {
			values.add(value);
		}
		
	}
	
	private class WithRes {
		
		private List<String> values = new ArrayList<>();
		
		@Check
		private void append(@ParamCreater(method = "linesOfResource", clas = ParamCreaterHelp.class, params = {
				Parameter.class }) @ParamInfo("/testfolder/testfile.txt") String value) {
			values.add(value);
		}
		
	}
	
}

/*
[
hello world
this is a nice
  test file
  here is some whitespace:        
  


and some empty lines  
]
not equals
[
hello world
this is a nice
  test file
  here is some whitespace:        
  


and some empty lines  
]
 */
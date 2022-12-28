module de.hechler.patrick.zeugs.check {
	
	requires jdk.unsupported;
	requires transitive java.logging;
	
	exports de.hechler.patrick.zeugs.check.anotations;
	exports de.hechler.patrick.zeugs.check.exceptions;
	exports de.hechler.patrick.zeugs.check.interfaces;
	exports de.hechler.patrick.zeugs.check.objects;
	
}

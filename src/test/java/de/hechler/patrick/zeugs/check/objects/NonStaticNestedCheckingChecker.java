package de.hechler.patrick.zeugs.check.objects;

import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;

public class NonStaticNestedCheckingChecker {
	
	@CheckClass
	private class NonStaticNestedChecker {
		
		@Check
		private void check() {}
		
	}
	
	@SuppressWarnings("unused")
	private class NonStaticNestedClass {
		
		@CheckClass
		private class NonStaticDoubleNestedChecker {
			
			@Check
			private void check() {}
			
			@CheckClass
			private class NonStaticTribleNestedCheckerA {
				
				@Check
				private void check() {}
				
			}
			
			@CheckClass
			private class NonStaticTribleNestedCheckerB {
				
			}
			
		}
		
	}
	
}

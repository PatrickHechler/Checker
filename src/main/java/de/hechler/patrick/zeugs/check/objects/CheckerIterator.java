package de.hechler.patrick.zeugs.check.objects;

import java.util.Iterator;
import java.util.NoSuchElementException;

import de.hechler.patrick.zeugs.check.anotations.CheckClass;
import de.hechler.patrick.zeugs.check.interfaces.TwoValues;

/**
 * the {@link CheckerIterator} uses an {@link Iterator} which iterates over {@link Class} objects,
 * and generates Checkers from the classes.<br>
 * if a {@link Class} has no {@link CheckClass} annotation or the annotation is
 * {@link CheckClass#disabled()} the class will skipped.<br>
 * 
 * @author Patrick
 */
public class CheckerIterator implements Iterator <TwoValues <Class <?>, Checker>> {
	
	/**
	 * the iterator used to get {@link Class} objects from which the {@link Checker} objects will be
	 * generated.<br>
	 * all classes which are not annotated with {@link CheckClass} will be ignored.
	 */
	private final Iterator <Class <?>> classes;
	/**
	 * the next {@link Class} object from which the {@link Checker} should be generated.
	 */
	private Class <?> next;
	
	/**
	 * creates a new {@link CheckerIterator} which uses the given {@link Iterator} {@code classes}.
	 * 
	 * @param classes
	 *            the {@link Class} {@link Iterator} from which the {@link Checker} objects should be
	 *            generated
	 */
	public CheckerIterator(Iterator <Class <?>> classes) {
		this.classes = classes;
		this.next = null;
	}
	
	@Override
	public boolean hasNext() {
		if (this.next != null) {
			return true;
		}
		while (classes.hasNext()) {
			Class <?> n = this.classes.next();
			CheckClass checkClass = n.getAnnotation(CheckClass.class);
			if (checkClass == null) {
				continue;
			}
			if (checkClass.disabled()) {
				continue;
			}
			this.next = n;
			return true;
		}
		return false;
	}
	
	@Override
	public TwoValues <Class <?>, Checker> next() throws NoSuchElementException {
		if (hasNext()) {
			Class <?> cls = this.next;
			this.next = null;
			return new TwoValuesImpl <>(cls, Checker.generateChecker(cls));
		} else {
			throw new NoSuchElementException("the iterator has no more elements");
		}
	}
	
}

package org.javaee7.cdi.alternatives;

import org.javaee7.cdi.Digit;
import org.javaee7.cdi.NumberGenerator;
import org.javaee7.cdi.annotations.NumberOfDigits;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

@Alternative
@Decorator
public class MockEightToThirteen implements NumberGenerator {
	@Inject
	@Delegate
	@NumberOfDigits(value = Digit.EIGHT_DIGITS,odd = false)
	private NumberGenerator numberGenerator ;

	@Override
	public String generate() {
		final String issn = numberGenerator.generate();
		return issn ;
	}
}

package org.javaee7.cdi.decorators;

import org.javaee7.cdi.Digit;
import org.javaee7.cdi.NumberGenerator;
import org.javaee7.cdi.annotations.Loggable;
import org.javaee7.cdi.annotations.NumberOfDigits;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import java.util.logging.Logger;

@Decorator
@Loggable
public class FromEightToThirteen implements NumberGenerator {

	@Inject
	private Logger logger ;

	@Inject
	@Delegate
	@NumberOfDigits(value = Digit.EIGHT_DIGITS,odd = false)
	private NumberGenerator numberGenerator ;

	@Override
	public String generate() {
		final String issn = numberGenerator.generate() ;
		final String isbn = "13-8456-" + issn ;
		logger.info("Converted issn <-> isbn : " + isbn);
		return isbn ;
	}
}

package org.javaee7.cdi;

import org.javaee7.cdi.annotations.Loggable;
import org.javaee7.cdi.annotations.NumberOfDigits;

import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

@NumberOfDigits(value = Digit.EIGHT_DIGITS, odd = false)
public class IssnGenerator implements NumberGenerator {
	@Inject
	private Logger logger;

	@Override
	@Loggable
	public String generate() {
		String issn = "8-1238-" + Math.abs(new Random().nextInt());
		logger.info("Generated ISSN number: " + issn);
		return issn;
	}
}

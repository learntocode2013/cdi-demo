package org.javaee7.cdi;

import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

@EightDigits
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

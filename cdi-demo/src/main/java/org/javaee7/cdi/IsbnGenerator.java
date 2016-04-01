package org.javaee7.cdi;

import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

@ThirteenDigits
public class IsbnGenerator implements NumberGenerator {
	@Inject
	private Logger logger;

	@Override
	@Loggable
	public String generate() {
		String isbn = "13-87345-" + Math.abs(new Random().nextInt()) ;
		logger.info("Generated ISBN number : " + isbn);
		return isbn;
	}
}

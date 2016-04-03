package org.javaee7.cdi;

import org.javaee7.cdi.annotations.Loggable;
import org.javaee7.cdi.annotations.NumberOfDigits;
import org.javaee7.cdi.annotations.ThirteenDigits;

import javax.inject.Inject;
import java.util.logging.Logger;

@NumberOfDigits(value = Digit.THIRTEEN_DIGITS, odd = true)
public class IsbnGenerator implements NumberGenerator {
	@Inject
	private Logger logger;

	@Inject @ThirteenDigits
	private String prefix;

	@Inject @ThirteenDigits
	private int editorNum;

	@Inject @org.javaee7.cdi.annotations.Random
	private double postfix;

	@Override
	@Loggable
	public String generate() {
		String isbn = prefix + editorNum + "-" + postfix ;
		logger.info("Generated ISBN number : " + isbn);
		return isbn;
	}
}

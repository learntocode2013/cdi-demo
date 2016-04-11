package org.javaee7.cdi;

import org.javaee7.cdi.annotations.Loggable;
import org.javaee7.cdi.annotations.NumberOfDigits;

import javax.inject.Inject;

@Loggable
public class BookService {
	//----- If any decorator is present, it will be invoked
	@Inject
	@NumberOfDigits(value = Digit.EIGHT_DIGITS, odd = false)
	private NumberGenerator numberGenerator;


	//----- Once we start using an injection container, setting by hand
	// will have to traverse all dependencies and create them (just as we
	// would have without a DI container.
	public BookService setGenerator(NumberGenerator i_generator) {
		numberGenerator = i_generator;
		return this;
	}

	@Loggable
	public Book create(String title, String desc, Float price) {
		Book newBook = new Book();
		newBook.setTitle(title); newBook.setDescription(desc);
		newBook.setNumber(numberGenerator.generate());
		newBook.setPrice(price);
		return newBook;
	}
}

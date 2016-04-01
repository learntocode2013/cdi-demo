package org.javaee7.cdi;

import javax.inject.Inject;

public class BookService {
	@Inject @ThirteenDigits
	private NumberGenerator numberGenerator;

	public Book create(String title, String desc, Float price) {
		Book newBook = new Book();
		newBook.setTitle(title); newBook.setDescription(desc);
		newBook.setNumber(numberGenerator.generate());
		newBook.setPrice(price);
		return newBook;
	}
}

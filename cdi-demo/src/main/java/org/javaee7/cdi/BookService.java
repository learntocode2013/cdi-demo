package org.javaee7.cdi;

import org.javaee7.cdi.annotations.*;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

@Loggable
public class BookService {
	//----- If any decorator is present, it will be invoked
	@Inject
	@NumberOfDigits(value = Digit.EIGHT_DIGITS, odd = false)
	private NumberGenerator numberGenerator;

	@Inject
	@Added
	private Event<Book> bookAddedEvent ;

	@Inject
	@Removed
	private Event<Book> bookRemovedEvent ;

	@Inject
	@Violated
	private Event<Set<ConstraintViolation<Book>>> violationEvent ;

	@Inject
	private ValidatorFactory validatorFactory ;

	@Inject
	private Validator validator ;

	//----- Once we start using an injection container, setting by hand
	// will have to traverse all dependencies and create them (just as we
	// would have without a DI container.
	public BookService setGenerator(NumberGenerator i_generator) {
		numberGenerator = i_generator;
		return this;
	}

	@Loggable
	public Optional<Book> create(String title, String desc, Float price) {
		Book newBook = new Book();
		newBook.setTitle(title); newBook.setDescription(desc);
		newBook.setNumber(numberGenerator.generate());
		newBook.setPrice(price);
		final Set<ConstraintViolation<Book>> constraintViolations = validator
				.validate(newBook);
		if( constraintViolations.isEmpty() ) {
			bookAddedEvent.fire(newBook);
			return Optional.of(newBook);
		}
		violationEvent.fire(constraintViolations);
		return Optional.empty();
	}

	@Loggable
	public Book remove(Book oldBook) {
		bookRemovedEvent.fire(oldBook);
		return oldBook ;
	}
}

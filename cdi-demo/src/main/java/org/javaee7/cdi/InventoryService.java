package org.javaee7.cdi;

import org.javaee7.cdi.annotations.Added;
import org.javaee7.cdi.annotations.Loggable;
import org.javaee7.cdi.annotations.Removed;
import org.javaee7.cdi.annotations.Violated;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Loggable
@ApplicationScoped
public class InventoryService {
	@Inject
	private Logger logger ;
	private final List<Book> books = new ArrayList<>();

	public void addBook(@Observes @Added Book newBook) {
		if( books.add(newBook) ) {
			logger.info("#----- Inventory was updated with book <" + newBook
					.getTitle() + ">");
		}
	}

	public void removeBook(@Observes @Removed Book oldBook) {
		if( books.remove(oldBook) ) {
			logger.warning("#---- Removing book with name <" + oldBook
					.getTitle() + ">");
		}
	}

	public List<Book> fetchAllBooks() {
		return books ;
	}

	public void takeActionForViolations(
			@Observes
			@Violated
					Set<ConstraintViolation<Book>> violations) {
		String msg = "#----- Book attribute: " ;
		for (ConstraintViolation<Book> each : violations ) {
			logger.warning(msg + each.getMessage());
		}
	}
}

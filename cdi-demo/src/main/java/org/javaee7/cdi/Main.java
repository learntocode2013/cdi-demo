package org.javaee7.cdi;

import org.javaee7.cdi.decorators.FromEightToThirteen;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.Optional;

public class Main {
	public static void main(String[] args) {
		Weld weld = new Weld();
		final WeldContainer weldContainer = weld.initialize();
		//----- Create a book from BookService
		//useNumberDecorator(weldContainer);
		//createBook(weldContainer);

		//---- Count lines in a file
		//countLinesFromFile(weldContainer);

		//---- Demonstrate conversation scope context usage
		//exerciseConversation(weldContainer);

		//--- Demonstrate method parameter validation
		exerciseParamValidation(weldContainer);
		// ---- Tear down CDI infrastructure
		weld.shutdown();
	}

	private static void useNumberDecorator(WeldContainer weldContainer) {
		final FromEightToThirteen fromEightToThirteen = weldContainer.instance()
				.select(FromEightToThirteen.class)
				.get();
		fromEightToThirteen.generate();
	}

	private static void createBook(WeldContainer weldContainer) {
		final BookService bookService = weldContainer.instance()
				.select(BookService.class)
				.get();

		final Optional<Book> bookOptional = bookService
				.create("Beginning-JavaEE-7", "Best book by Antonio Goncalves", 20.34f);
		System.out.println(bookOptional.get());
		//System.out.println(inventoryService.fetchAllBooks());
		bookService.remove(bookOptional.get());
	}

	private static void countLinesFromFile(WeldContainer weldContainer) {
		final FileService fileService = weldContainer.instance().select(FileService.class).get();
		fileService.countLines();
	}

	private static void exerciseConversation(WeldContainer weldContainer) {
		final CustomerCreatorWizard customerCreatorWizard = weldContainer.instance()
				.select(CustomerCreatorWizard.class)
				.get();
		customerCreatorWizard
				.saveLogin("john_doe","password123")
				.saveAccount("John","Doe","johndoe@msdn.com")
				.createCustomer();
	}

//--- Method which demonstrates how validation can be trigerred via interceptors
	private static void exerciseParamValidation(WeldContainer weldContainer) {
		final CardValidator cardValidator = weldContainer.instance()
				.select(CardValidator.class)
				.get();
		cardValidator.validate(null);
	}
}

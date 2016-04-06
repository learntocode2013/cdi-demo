package org.javaee7.cdi;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Main {
	public static void main(String[] args) {
		Weld weld = new Weld();
		final WeldContainer weldContainer = weld.initialize();

		//----- Create a book from BookService
		final BookService bookService = weldContainer.instance().select(BookService.class).get();
		final Book book = bookService.create("Beginning-JavaEE-7", "Best book by Antonio Goncalves", 20.34f);
		System.out.println(book);

		//---- Count lines in a file
		final FileService fileService = weldContainer.instance().select(FileService.class).get();
		System.out.println(fileService.countLines());

		// ---- Tear down CDI infrastructure
		weld.shutdown();
	}
}

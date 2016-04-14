package org.javaee7.cdi;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.Is.is;

@FixMethodOrder
public class BookServiceApiTest {
	private static Weld cdi_handle;
	private static WeldContainer weldContainer ;

	@BeforeClass
	public static void beforeClass() {
		cdi_handle = new Weld();
		weldContainer = cdi_handle.initialize();
	}

	@AfterClass
	public static void afterClass() {
		if ( weldContainer.isRunning() ) {
			cdi_handle.shutdown();
		}
	}

	@Test
	public void bookServiceCanCreateNewBook() {
		final BookService bookService = weldContainer.instance()
				.select(BookService.class)
				.get();
		final Book book = bookService.create("Mastering Lambdas", "java-8 api(s)", 32.6f);
		assertThat(book.getNumber(), equalTo("MOCK"));
	}

	@Test
	public void booksCanBeAddedAndRemoved() {
		final BookService bookService = weldContainer.instance()
				.select(BookService.class)
				.get();
		final InventoryService inventoryService = weldContainer.instance()
				.select(InventoryService.class)
				.get();
		final Book book = bookService.create("Mastering Lambdas", "java-8 api(s)", 32.6f);
		assertThat(inventoryService.fetchAllBooks(),hasItem(book));
		bookService.remove(book);
		assertThat(inventoryService.fetchAllBooks().size(),is(0));
	}
}

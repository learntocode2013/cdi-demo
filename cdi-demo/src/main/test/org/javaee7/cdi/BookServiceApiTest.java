package org.javaee7.cdi;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BookServiceApiTest {
	private static Weld cdi_handle;

	@BeforeClass
	public static void beforeClass() {
		cdi_handle = new Weld();
	}

	@AfterClass
	public static void afterClass() {
		cdi_handle.shutdown();
	}

	@Test
	public void bookCreatedWithMockNumber() {
		cdi_handle.addAlternative(org.javaee7.cdi.alternatives.MockNumberGenerator.class);

		final WeldContainer weldContainer = cdi_handle.initialize();
		final BookService bookService = weldContainer.instance().select(BookService.class).get();
		final Book book = bookService.create("Mastering Lambdas", "java-8 api(s)", 32.6f);
		assertTrue("Expected book number MOCK | Got: " + book.getNumber(),book.getNumber().equals("MOCK"));
	}
}

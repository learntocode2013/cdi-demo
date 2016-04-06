package org.javaee7.cdi;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.*;

public class FileServiceApiTest {
	private static WeldContainer cdi_container = null;

	@BeforeClass
	public static void beforeClass() {
		Weld weld = new Weld();
		cdi_container = weld.initialize();
	}

	@AfterClass
	public static void afterClass() {
		cdi_container.shutdown();
	}

	//------------ Sunny day scenario
	@Test
	public void testFileServiceReadWithValidFile() {
		final FileService fileService = cdi_container.select(FileService.class).get();
		assertThat(fileService.countLines(), is(not(equals(0))));
	}
}

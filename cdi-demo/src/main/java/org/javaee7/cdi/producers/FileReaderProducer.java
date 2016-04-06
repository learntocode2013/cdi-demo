package org.javaee7.cdi.producers;

import org.javaee7.cdi.annotations.Readable;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

// --------- Consolidate resource creation and tear down at one place.
public class FileReaderProducer {
	@Inject private Logger logger;

	@Produces
	@Readable
	public FileReader createForSource(InjectionPoint injectionPoint) {
		try {
			Readable readable = injectionPoint.getAnnotated().getAnnotation(Readable.class);
			final Path toFile = Paths.get(readable.src());
			return new FileReader(toFile.toFile()) ;
		} catch (FileNotFoundException cause) {
			cause.printStackTrace();
		}
		logger.warning("#----- File reader instance creation failed | returning nothing ");
		return null;
	}

	public void disposeReader(@Disposes @Readable FileReader fileReader) {
		if( null != fileReader ) {
			try {
				fileReader.close();
				logger.info("#------ File reader instance was successfully disposed");
			} catch (IOException cause) {
				cause.printStackTrace();
			}
		}
		return;
	}
}

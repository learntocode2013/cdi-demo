package org.javaee7.cdi.alternatives;

import org.javaee7.cdi.annotations.Readable;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Alternative
public class MockFileProducer {
	@Produces @Readable
	public FileReader createForSource(InjectionPoint injectionPoint) {
		try {
			final Stream<Path> fileList = Files.list(Paths.get("."));

			final Path target = fileList.filter(path -> Files.isRegularFile(path))
					.filter(path -> !Files.isDirectory(path))
					.findFirst()
					.get();

			return new FileReader(target.toFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}

	public void reclaimFileHandle(@Disposes @Readable FileReader fileReader) {
		if( null != fileReader ) {
			try {
				fileReader.close();
			} catch (IOException ignore) {

			}
		}
	}
}

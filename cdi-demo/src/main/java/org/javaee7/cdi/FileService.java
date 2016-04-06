package org.javaee7.cdi;

import org.javaee7.cdi.annotations.Readable;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class FileService {
	@Inject
	private Logger logger;

	@Inject @Readable(src = "/Users/disen/Desktop/Docs/bank-details.txt")
	private FileReader fileReader;

	public int countLines() {
		int line_count = 0 ;
		try(BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			while( null != bufferedReader.readLine() ) {
				line_count++ ;
			}
		} catch (IOException cause) {
			logger.warning("Failed to read from file !!!");
		}

		logger.info("#-- Line count in file :" + line_count);
		return line_count;
	}
}

package net.thomaspreis.apps.pdffc.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

public class ContentReader {

	Logger logger = Logger.getLogger(ContentReader.class);

	public List<String> readToList(String filePath) {

		List<String> list = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {

			list = br.lines().collect(Collectors.toList());

		} catch (IOException e) {
			logger.error("Error reading file: " + filePath, e);
			list = null;
		}

		return list;
	}
}

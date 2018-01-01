package net.thomaspreis.apps.pdffc.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.thomaspreis.apps.pdffc.model.FlashcardModel;

public class ContentParser {

	Logger logger = Logger.getLogger(ContentParser.class);

	public List<FlashcardModel> parse(List<String> contentList) {
		List<FlashcardModel> parsedList = null;
		if (null == contentList || contentList.isEmpty()) {
			logger.warn("There is no content to be parsed.");
		} else {
			for (String content : contentList) {
				if (null == parsedList) {
					parsedList = new ArrayList<>();
				}
				FlashcardModel fc = new FlashcardModel(content);
				parsedList.add(fc);
			}
			logger.info("Sentences to be generated: " + parsedList.size());
		}
		return parsedList;
	}
}

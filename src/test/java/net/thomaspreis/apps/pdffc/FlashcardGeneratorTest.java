package net.thomaspreis.apps.pdffc;

import org.junit.Test;

import junit.framework.TestCase;

public class FlashcardGeneratorTest extends TestCase {

	@Test
	public void testGenerate() {
		String basePath = "/";
		String inputFilePath = basePath + "/sample-content.txt";
		String outputFilePath = "/Users/Temp";
		FlashcardGenerator fg = new FlashcardGenerator();
		fg.generate(inputFilePath, outputFilePath);
		assertTrue("oh yes", Boolean.TRUE);
	}
}

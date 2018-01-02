package net.thomaspreis.apps.pdffc;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.thomaspreis.apps.pdffc.domain.PDFFCEnum;
import net.thomaspreis.apps.pdffc.helper.ContentParser;
import net.thomaspreis.apps.pdffc.helper.ContentReader;
import net.thomaspreis.apps.pdffc.model.FlashcardModel;

public class FlashcardGenerator {

	Logger logger = Logger.getLogger(FlashcardGenerator.class);
	ContentReader contentReader = new ContentReader();
	ContentParser contentParser = new ContentParser();

	static Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	static Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.NORMAL);

	public void generate(String inputFilePath, String outputFilePath) {
		long t0 = System.currentTimeMillis();

		try {
			if (StringUtils.isBlank(outputFilePath)) {
				outputFilePath = getDefaultOutputFilePath();
			} else if (isDirectory(outputFilePath)) {
				outputFilePath = getDefaultOutputFilePath(outputFilePath);
			}
			List<FlashcardModel> contentList = getContentList(inputFilePath);
			logger.debug("Starting to generate PDF: " + outputFilePath);
			generatePDF(contentList, outputFilePath);
			Desktop.getDesktop().open(new File(outputFilePath));
			logger.debug(MessageFormat.format("PDF: {0} generated, time spent: {1} ms", outputFilePath, System.currentTimeMillis() - t0));
		} catch (FileNotFoundException | DocumentException e) {
			logger.error(MessageFormat.format("Error generating PDF, inputFile: {0}, outputFile: {1}", inputFilePath, outputFilePath), e);
		} catch (IOException e) {
			logger.error("Error opening file/folder: " + outputFilePath);
		}

	}

	void generatePDF(List<FlashcardModel> contentList, String outputFilePath) throws FileNotFoundException, DocumentException {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));

		document.open();

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 100, 100 });
		for (FlashcardModel fc : contentList) {
			addRows(table, fc.getFrontPhrase(), fc.getVersePhrase());
		}

		document.add(table);
		document.close();
	}

	void addRows(PdfPTable table, String content1, String content2) {
		PdfPCell pdfWordCell1 = new PdfPCell();
		pdfWordCell1.setFixedHeight(40f);
		
		Paragraph p1 = new Paragraph();
		for (Phrase phrase : getPhrases(content1)) {
			p1.add(phrase);
		}

		pdfWordCell1.addElement(p1);
		
		PdfPCell pdfWordCell2 = new PdfPCell();
		pdfWordCell2.setFixedHeight(40f);

		Paragraph p2 = new Paragraph();
		for (Phrase phrase : getPhrases(content2)) {
			p2.add(phrase);
		}

		pdfWordCell2.addElement(p2);

		table.addCell(pdfWordCell1);
		table.addCell(pdfWordCell2);
	}

	List<Phrase> getPhrases(String content) {
		List<Phrase> phrasesList = new ArrayList<>();
		String[] str = content.replaceAll("\\*", "").split(" ");
		for (String s : str) {
			Phrase phrase = new Phrase(s + " ", normalFont);
			if (content.contains("*" + s + "*")) {
				phrase = new Phrase(s + " ", boldFont);
			}
			phrasesList.add(phrase);
		}
		return phrasesList;
	}

	List<FlashcardModel> getContentList(String inputFilePath) {
		logger.debug("Reading content: " + inputFilePath);
		List<String> contentListAsString = contentReader.readToList(inputFilePath);
		logger.debug("Parsing content, number of lines: " + contentListAsString.size());
		List<FlashcardModel> contentList = contentParser.parse(contentListAsString);
		logger.debug("Content parsed");
		return contentList;
	}

	String getDefaultOutputFilePath() {
		String tempDir = System.getProperty(PDFFCEnum.OS_TEMP_DIR_PROPERTY.getValue());
		String fileName = PDFFCEnum.OUTPUT_DEFAULT_FILE_NAME.getValue();
		return tempDir + File.separatorChar + MessageFormat.format(fileName, getCurrentTimestamp());
	}

	String getDefaultOutputFilePath(String outputPath) {
		String fileName = PDFFCEnum.OUTPUT_DEFAULT_FILE_NAME.getValue();
		return outputPath + File.separatorChar + MessageFormat.format(fileName, getCurrentTimestamp());
	}

	boolean isDirectory(String outputFilePath) {
		return new File(outputFilePath).isDirectory();
	}

	String getCurrentTimestamp() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
}

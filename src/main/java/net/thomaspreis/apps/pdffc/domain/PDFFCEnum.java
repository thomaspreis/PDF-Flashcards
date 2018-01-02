package net.thomaspreis.apps.pdffc.domain;

public enum PDFFCEnum {
	PHRASE_SEPARATOR(";"),
	PHRASE_IGNORE("--"),
	OS_TEMP_DIR_PROPERTY("java.io.tmpdir"),
	OUTPUT_DEFAULT_FILE_NAME("PDF-Flashcard.{0}.pdf");

	private String value;

	private PDFFCEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

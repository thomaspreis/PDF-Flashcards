package net.thomaspreis.apps.pdffc.model;

import net.thomaspreis.apps.pdffc.domain.PDFFCEnum;

public class FlashcardModel {
	
	private String frontPhrase;
	private String versePhrase;

	public FlashcardModel(String phrase) {
		String[] content = phrase.split(PDFFCEnum.PHRASE_SEPARATOR.getValue());
		this.frontPhrase = content[0];
		this.versePhrase = content[1];
	}

	public final String getFrontPhrase() {
		return frontPhrase;
	}

	public final String getVersePhrase() {
		return versePhrase;
	}

	@Override
	public String toString() {
		return "FlashcardModel [frontPhrase=" + frontPhrase + ", versePhrase=" + versePhrase + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((frontPhrase == null) ? 0 : frontPhrase.hashCode());
		result = prime * result + ((versePhrase == null) ? 0 : versePhrase.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlashcardModel other = (FlashcardModel) obj;
		if (frontPhrase == null) {
			if (other.frontPhrase != null)
				return false;
		} else if (!frontPhrase.equals(other.frontPhrase))
			return false;
		if (versePhrase == null) {
			if (other.versePhrase != null)
				return false;
		} else if (!versePhrase.equals(other.versePhrase))
			return false;
		return true;
	}

}

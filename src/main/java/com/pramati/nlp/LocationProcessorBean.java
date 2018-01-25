package com.pramati.nlp;

import java.io.FileInputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

public class LocationProcessorBean {

	private Logger log = LoggerFactory.getLogger(LocationProcessorBean.class);

	public void findLocation(String message) throws Exception {
		if (message == null) {
			throw new Exception("Please enter some input");
		} else {
			InputStream is = new FileInputStream("en-ner-location.bin");
			TokenNameFinderModel model = new TokenNameFinderModel(is);
			is.close();
			NameFinderME nameFinder = new NameFinderME(model);
			/*
			 * String[] sentence = new String[]{ "John", "Smith", "is", "from",
			 * "Atlanta", "." };
			 */
			String sentence[] = WhitespaceTokenizer.INSTANCE.tokenize(message);
			Span nameSpans[] = nameFinder.find(sentence);
			for (Span s : nameSpans) {
				System.out.print(s.toString());
				System.out.print("  :  ");
				for (int index = s.getStart(); index < s.getEnd(); index++) {
					System.out.print(sentence[index] + " ");
				}
				System.out.println();
			}
		}
	}
}

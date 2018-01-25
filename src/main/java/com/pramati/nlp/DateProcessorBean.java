package com.pramati.nlp;

import java.io.FileInputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

public class DateProcessorBean {
	
	private Logger log = LoggerFactory.getLogger(DateProcessorBean.class);
	
	public void findDate(String message) throws Exception {
		if (message == null) {
			throw new Exception("Please enter some input");
		} else {
			InputStream is = new FileInputStream("en-ner-date.bin");
			TokenNameFinderModel model = new TokenNameFinderModel(is);
			is.close();
			NameFinderME nameFinder = new NameFinderME(model);
			String sentence[] = WhitespaceTokenizer.INSTANCE.tokenize(message);
			/*String[] sentence = new String[] { "3/12/2018", "June 28, 2015", "is", "yesterday", "next", "when", "i",
					"Tuesday", "waiting" };*/
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

package application.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class DefaultDataParser<T> implements IDataParser<T> {
	private Document doc;

	public void init(String url) throws IOException {
		doc = Jsoup.connect(url).get();
	}
	
	protected Document getDoc() {
		return doc;
	}

}

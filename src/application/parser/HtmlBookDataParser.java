package application.parser;

import java.io.IOException;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;

public class HtmlBookDataParser extends DefaultDataParser<Book> {

	Book book;

	public HtmlBookDataParser() {
	}
	
	public void init(String url) throws IOException{
		super.init(url);
		book = new Book();
		System.out.println(getDoc().title());
	}

	public void parse() {
		book.setTytul(parseTitle());
		book.setAutor(parseAuthName());
		book.setISBN(parseISBN());
		book.setLiczbaStron(parseLStron());
		book.setWydawnictwo(parseWydawnictwo());
		book.setJezyk(parseJezyk());
		book.setTlumaczenie(parseTlumaczenie());
		book.setData(parseData());
		book.setKategoria(parseKategoria());

		// DateFormat df = new SimpleDateFormat();
		// df.parse("asdsad")

		System.out.println(book);
	}

	private String parseISBN() {
		Element isbn = getDoc().getElementsByAttributeValue("itemprop", "isbn").first();
		return isbn.text();
	}

	private int parseLStron() {
		int lStron = 0;
		try {
			Element lstron = getDoc().getElementsByClass("profil-desc-book").first().getElementsMatchingText("liczba stron")
					.last().lastElementSibling();
			lStron = Integer.parseInt(lstron.text());
		} catch (Exception e) {

		}
		return lStron;
	}

	private String parseWydawnictwo() {
		String wydStr = null;
			try {
				Pattern pattern = Pattern.compile("^http://lubimyczytac.pl/wydawnictwo");
				Element wyd = getDoc().getElementsByAttributeValueMatching("href", pattern).first();
				wydStr = wyd.text();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return wydStr;
	}

	private String parseJezyk() {
		Element jezyk = getDoc().getElementsByAttributeValue("itemprop", "inLanguage").first();
		return jezyk.text();
	}

	private String parseTlumaczenie() {
		try {
			Element translatorCont = getDoc().getElementsByAttributeValue("itemprop", "translator").first();
			Element translator = translatorCont.getAllElements().first();
			return translator.text();
		} catch (NullPointerException e) {
			return null;
		}
	}

	private String parseData() {
		Element date = getDoc().getElementsByAttributeValue("itemprop", "datePublished").first();
		return date.text();
	}

	private String parseKategoria() {
		Element kategoria = getDoc().getElementsByAttributeValue("itemprop", "genre").first();
		return kategoria.text();
	}

	private String parseTitle() {
		Element titleContainer = getDoc().select(".grid_5.alpha.omega").first();
		Element title = titleContainer.getElementsByAttributeValue("itemprop", "name").first();
		return title.text();
	}

	private String parseAuthName() {
		Element authorContainer = getDoc().select(".author-info-container").first();
		Element author = authorContainer.getElementsByAttributeValue("itemprop", "author").first();
		String authName = author.getElementsByAttributeValue("itemprop", "name").first().text();
		return authName;
	}

	public Book getBook() {
		return book;
	}

	@Override
	public Book getParsedObject() {
		return book;
	}
	
}

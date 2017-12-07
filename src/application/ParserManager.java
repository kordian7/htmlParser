package application;

import java.io.IOException;

import application.output.OutputGeneratorFactory;
import application.output.OutputGeneratorFactory.OutputTypes;
import application.output.Outputable;
import application.parser.IDataParser;

public class ParserManager<T extends IDataParser> {
	Object object;
	
	T dataParser;

	private Outputable outputGenerator;
	
	public ParserManager(T ob) {
		
		dataParser = ob;
	}
	
	private void createOutputGenerator(OutputTypes type) {
		outputGenerator = OutputGeneratorFactory.getOutputGenerator(type);
	}

	public void parseHtml() throws IOException {
		
		dataParser.parse();
		object = dataParser.getParsedObject();
	}
	
	public String parse(String url, OutputTypes type) throws IOException  {
		dataParser.init(url);
		createOutputGenerator(type);
		parseHtml();
		outputGenerator.init(object);
		return outputGenerator.generateOutput();
	}

}

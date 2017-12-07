package application.parser;

import java.io.IOException;

public interface IDataParser<T> {

	public void init(String str)  throws IOException;
	
	public void parse();
	
	public T getParsedObject();
	
}

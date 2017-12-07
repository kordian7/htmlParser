package application.output;

import application.output.sql.SQLOutputGenerator;
import application.output.xml.XMLOutputGenerator;

public class OutputGeneratorFactory {
	public enum OutputTypes {
		SQL, XML;
	}
	
	public static Outputable getOutputGenerator(OutputTypes type) {
		if (OutputTypes.SQL.equals(type))
			return new SQLOutputGenerator();
		else 
			return new XMLOutputGenerator();
	}
}

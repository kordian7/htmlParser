package application.output.xml;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import application.output.Outputable;

public class XMLOutputGenerator implements Outputable {

	Object object;

	@Override
	public void init(Object object) {
		this.object = object;
	}

	@Override
	public String generateOutput() {
		String str = "";
		JAXBContext ctx;
		try {
			ctx = JAXBContext.newInstance(object.getClass());

	        Marshaller marshaller = ctx.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-2" );
	        
	        StringWriter strWriter = new StringWriter();
	        
	        marshaller.marshal(object, strWriter);
	        str = strWriter.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return str;
	}

}

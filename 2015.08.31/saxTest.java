package leibniz.hu;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class saxTest {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		DefaultHandler dh = new MyDefaultandler();
		parser.parse("Book.jaxp.xml", dh);
	}

}

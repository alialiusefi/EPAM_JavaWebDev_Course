package by.training.task4.runner;

import by.training.task4.handler.GemHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class SAXRunner {

    public static final Logger LOGGER =
            LogManager.getLogger(SAXRunner.class);

    public static void main(String[] args) {
        try{
            XMLReader reader = XMLReaderFactory.createXMLReader();
            GemHandler handler = new GemHandler();
            reader.setContentHandler(handler);
            reader.parse("data/gems.xml");
        }
        catch (SAXException r)
        {
            LOGGER.warn(r.getMessage(),r);
        }
        catch (IOException r)
        {
            LOGGER.warn(r.getMessage(),r);
        }
    }
}

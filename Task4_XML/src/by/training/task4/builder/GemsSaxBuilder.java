package by.training.task4.builder;

import by.training.task4.entity.AbstractGem;
import by.training.task4.handler.GemHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class GemsSaxBuilder {

    public static final Logger LOGGER =
            LogManager.getLogger(GemsSaxBuilder.class);

    private Set<AbstractGem> gems;
    private GemHandler gemHandler;
    private XMLReader xmlReader;

    public GemsSaxBuilder() {
        gemHandler = new GemHandler();
        try {
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(gemHandler);
        } catch (SAXException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public Set<AbstractGem> getGems() {
        return gems;
    }

    public void buildSetStudents(String filename) {
        try {
            xmlReader.parse(filename);
        } catch (SAXException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        gems = gemHandler.getGems();
    }
}

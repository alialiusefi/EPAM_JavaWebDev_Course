package by.training.task4.builder;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;

import static org.testng.Assert.*;

public class GemDomBuilderTest {

    final String xmlFilePath = "data/gems.xml";
    GemDomBuilder domBuilder;

    @BeforeMethod
    public void setUp() {
        try {
            domBuilder = new GemDomBuilder();
        } catch (ParserConfigurationException e) {
            GemDomBuilder.LOGGER.warn(e.getMessage(),e);
        }
    }

    @Test
    public void testGemDomBuilder()
    {
        try {
            domBuilder.buildSetStudents(xmlFilePath);
        } catch (IOException |SAXException e) {
            GemDomBuilder.LOGGER.warn(e.getMessage(), e);
        }
        domBuilder.getGems().forEach(System.out::println);

    }

}
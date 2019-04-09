package by.training.task4.builder;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.*;

public class GemsStaxBuilderTest {

    private GemsStaxBuilder gemsStaxBuilder;
    private static final String xmlFilePath = "data" + File.separator + "gems.xml";

    @BeforeMethod
    public void setUp() {
        gemsStaxBuilder = new GemsStaxBuilder();
    }

    @Test
    public void testStaxBuilder() {
        gemsStaxBuilder.buildSetGems(xmlFilePath);
        gemsStaxBuilder.getGems().forEach(System.out::println);
    }
}
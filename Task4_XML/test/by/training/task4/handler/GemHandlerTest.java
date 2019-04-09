package by.training.task4.handler;

import by.training.task4.builder.GemsSaxBuilder;
import by.training.task4.entity.AbstractGem;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GemHandlerTest {

    private GemsSaxBuilder saxBuilder;
    private String xmlPath;


    @BeforeMethod
    public void setUp() {
        saxBuilder = new GemsSaxBuilder();
        xmlPath = "data\\gems.xml";
    }

    @Test
    public void testGemHandler()
    {
        saxBuilder.buildSetGems(xmlPath);
        for(AbstractGem i : saxBuilder.getGems())
        {
            System.out.println(i);
        }
    }

}
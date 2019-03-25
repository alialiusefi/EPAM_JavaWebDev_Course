package by.training.task3.entity;

import by.training.task3.reader.DataReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TextTest {
    public static final String FILE_PATH = "data/input.txt";
    public static final String FILE_PATH2 = "data/input2.txt";

    public String text;
    public String text2;
    @BeforeClass
    public void setUp()
    {
        try {
            text = new DataReader().readData(new File(FILE_PATH));
        } catch (IOException e) {
            DataReader.logger.warn(e.getMessage(),e);
        }
    }

    @Test
    public void testToString() {
        Text textObj = new Text(text);
        System.out.println(textObj.toString());
    }
}
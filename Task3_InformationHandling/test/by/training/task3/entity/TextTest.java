package by.training.task3.entity;

import by.training.task3.reader.DataReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.*;

public class TextTest {
    public static final String FILE_PATH = "data/input.txt";
    public String text;
    public String text2;
    @BeforeClass
    public void setUp()
    {
        try {
            text = new DataReader().readData(new File(FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testToString() {
        Text textObj = new Text(text);
        System.out.println(textObj.toString());
    }
}
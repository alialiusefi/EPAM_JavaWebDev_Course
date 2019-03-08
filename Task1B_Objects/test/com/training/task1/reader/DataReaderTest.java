package com.training.task1.reader;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class DataReaderTest {

    private DataReader dataReader = new DataReader();
    private List<String> expected;

    @BeforeClass
    public void setUp() {
        expected = new ArrayList<>();
        expected.add("0.0,0.0 0.0,2.0 2.0,2.0 2.0,0.0");
        expected.add("-1.0,0.0 1.0,2.0 5.0,2.0 3.0,0.0");
        expected.add("4.0,0.0 0sdfs.0,2.0 d2.0,2.0 2.0,0.0");
        expected.add("-9.0,0.0 6.0,2.0 2.0,2.0 2.0,0.0");
        expected.add("asdad");
        expected.add("1.2 5.4 3.0");
    }

    @Test
    public void testReadData() {
        try
        {
            List<String> actual = dataReader.readData(new File( "Data" + File.separator + "in.txt"));
            assertEquals(actual,expected);
        }
        catch(IOException e)
        {
            DataReader.logger.error(e.getMessage(),e);
        }
    }
}
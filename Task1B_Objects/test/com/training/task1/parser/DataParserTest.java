package com.training.task1.parser;

import com.training.task1.exception.InvalidDataException;
import javafx.util.Pair;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class DataParserTest {

    private DataParser dataParser = new DataParser();
    private List<List<Pair<String,String>>> expectedStrings;
    private List<String> stringsPointsList;
    @DataProvider
    public Object[][] testParsePointsData()
    {
        stringsPointsList = new ArrayList<>();
        expectedStrings = new ArrayList<>();
        List<Pair<String,String>> listPoints1 = new ArrayList<>();
        listPoints1.add(new Pair<String,String>("0.0","0.0"));
        listPoints1.add(new Pair<String,String>("0.0","2.0"));
        listPoints1.add(new Pair<String,String>("2.0","2.0"));
        listPoints1.add(new Pair<String,String>("2.0","0.0"));
        List<Pair<String,String>> listPoints2 = new ArrayList<>();
        listPoints2.add(new Pair<String,String>("-2.0","-2.0"));
        listPoints2.add(new Pair<String,String>("-2.0","0.0"));
        listPoints2.add(new Pair<String,String>("0.0","0.0"));
        listPoints2.add(new Pair<String,String>("0.0","-2.0"));
        stringsPointsList.add("0.0,0.0 0.0,2.0 2.0,2.0 2.0,0.0");
        stringsPointsList.add("-2.0,-2.0 -2.0,0.0 0.0,0.0 0.0,-2.0");

        return new Object[][]
                {
                        {stringsPointsList.get(0),listPoints1},
                        {stringsPointsList.get(1),listPoints2}
                };
    }

    @Test(dataProvider = "testParsePointsData")
    public void testParsePoints(String lineOfPoints, List<Pair<String,String>> expectedListPoints) {
        try {
            List<Pair<String,String>> actual = dataParser.parsePoints(lineOfPoints);
            assertEquals(actual,expectedListPoints);
        } catch (InvalidDataException e) {
            DataParser.LOGGER.warn(e.getMessage(),e);
        }

    }
}
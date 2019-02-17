package com.training.task1.parser;

import com.training.task1.entity.Point;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DataParserTest {

    DataParser dataParser = new DataParser();
    String[] actualCoordinates;
    Point expectedPoint;

    @BeforeMethod
    public void setUp() {
        actualCoordinates = new String[]
                {
                        "1.0",
                        "-3.7",
                };
        expectedPoint = new Point(1.0,-3.7);
    }

    @Test
    public void testToPoint() {
        Point actual = dataParser.toPoint(actualCoordinates);
        assertEquals(actual,expectedPoint);
    }
}
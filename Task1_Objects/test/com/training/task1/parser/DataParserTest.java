package com.training.task1.parser;

import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DataParserTest {

    DataParser dataParser = new DataParser();
    String[] actualCoordinates;
    Point expectedPoint;
    String[][] quadStrPoints;
    Quadrilateral quadExpectedData;

    @BeforeMethod
    public void setUp() {
        actualCoordinates = new String[]
                {
                        "1.0",
                        "-3.7",
                };
        expectedPoint = new Point(1.0,-3.7);
        quadStrPoints = new String[4][2];
        quadStrPoints[0] = new String[] {"0.0","0.0"};
        quadStrPoints[1] = new String[] {"0.0","2.0"};
        quadStrPoints[2] = new String[] {"2.0","2.0"};
        quadStrPoints[3] = new String[] {"2.0","0.0"};
        quadExpectedData = new Quadrilateral(new Point[]{
                new Point(0.0,0.0),
                new Point(0.0,2.0),
                new Point(2.0,2.0),
                new Point(2.0,0.0) });
    }

    @Test
    public void testToPoint() {
        Point actual = dataParser.toPoint(actualCoordinates);
        assertEquals(actual,expectedPoint);
    }
    @Test
    public void testToQuadrilateral() {
        Quadrilateral actual = dataParser.toQuadrilateral(quadStrPoints);
        assertEquals(actual,quadExpectedData);
    }
}
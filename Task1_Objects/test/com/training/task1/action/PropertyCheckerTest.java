package com.training.task1.action;

import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;
import com.training.task1.exception.InvalidDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PropertyCheckerTest {

    private Quadrilateral[] quadrilateral;
    private Point[][] points;
    private PropertyChecker propertyChecker = new PropertyChecker();
    public Logger LOGGER = LogManager.getLogger(PropertyCheckerTest.class);
    @BeforeClass
    public void setUp() {
        points = new Point[4][4];
        points[0] = new Point[]{
                new Point(0.0, 0.0),
                new Point(0.0, 2.0),
                new Point(2.0, 2.0),
                new Point(2.0, 0.0)
        };
        points[1] = new Point[]{
                new Point(5.0, 1.0),
                new Point(4.0, 3.0),
                new Point(5.0, 5.0),
                new Point(6.0, 3.0)
        };
        points[2] = new Point[]{
                new Point(0.0, -4.4),
                new Point(0.0, -4.4),
                new Point(0.0, -4.4),
                new Point(0.0, -5.5)
        };
        points[3] = new Point[]{
                new Point(-5.0, -5.0),
                new Point(-5.0, 5.0),
                new Point(6.0, 5.0),
                new Point(20.0, -5.0)
        };
        try {
            quadrilateral = new Quadrilateral[]
                    {
                            new Quadrilateral(points[0]),
                            new Quadrilateral(points[1]),
                            new Quadrilateral(points[2]),
                            new Quadrilateral(points[3])
                    };
        }
        catch(InvalidDataException e)
        {
            LOGGER.warn(e.getMessage(),e);
        }
    }

    @DataProvider
    public Object[][] testIsQuadrilateralData()
    {
        return new Object[][]
                {
                        {points[0],true},
                        {points[1],true},
                        {points[2],false},
                        {points[3],true}

                };
    }
    @DataProvider
    public Object[][] testIsConcaveData() {
        Quadrilateral quadrilateral = null;
        try {
            quadrilateral = new Quadrilateral(new Point[]{
                    new Point(1.5, 1.5),
                    new Point(2.5, 5.5),
                    new Point(5.5, 5.5),
                    new Point(5.5, 2.5)
            });
        }
        catch (InvalidDataException e)
        {
            LOGGER.warn(e.getMessage(),e);
        }
            return new Object[][]{
                    {quadrilateral,false}
            };
    }

    @Test(dataProvider = "testIsQuadrilateralData")
    public void testIsQuadrilateral(Point[] points, boolean expected) {
        boolean actual = propertyChecker.isQuadrilateral(points);
        assertEquals(actual,expected);
    }

    @Test
    public void testIsSquare() {
        boolean actual = propertyChecker.isSquare(quadrilateral[0]);
        assertTrue(actual);
    }
    @Test
    public void testIsRhombus() {
        boolean actual = propertyChecker.isRhombus(quadrilateral[1]);
        assertTrue(actual);
    }

    @Test
    public void testIsTrapezoid() {
        boolean actual = propertyChecker.isTrapezoid(quadrilateral[3]);
        assertTrue(actual);
    }

    @Test(dataProvider = "testIsConcaveData")
    public void testIsConcave(Quadrilateral quadrilateral, boolean expected) {
        boolean actual = propertyChecker.isConcave(quadrilateral);
        assertEquals(actual,expected);
    }
}
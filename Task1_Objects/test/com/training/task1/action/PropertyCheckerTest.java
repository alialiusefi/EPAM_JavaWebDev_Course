package com.training.task1.action;

import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PropertyCheckerTest {

    private Quadrilateral[] quadrilateral;
    private Point[][] points;
    private PropertyChecker propertyChecker = new PropertyChecker();

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
        quadrilateral = new Quadrilateral[]
                {
                        new Quadrilateral(points[0]),
                        new Quadrilateral(points[1]),
                        new Quadrilateral(points[2]),
                        new Quadrilateral(points[3])
                };
    }

    @DataProvider
    public Object[][] testIsQuadrilateralData()
    {
         return new Object[][]
        {
                {quadrilateral[0],true},
                {quadrilateral[1],true},
                {quadrilateral[2],false},
                {quadrilateral[3],true}

        };
    }

    @Test(dataProvider = "testIsQuadrilateralData")
    public void testIsQuadrilateral(Quadrilateral quadrilateral, boolean expected) {
        boolean actual = propertyChecker.isQuadrilateral(quadrilateral);
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
}
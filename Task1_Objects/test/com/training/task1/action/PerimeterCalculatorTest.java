package com.training.task1.action;

import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PerimeterCalculatorTest {

    private Quadrilateral[] quadrilateral;
    private Point[][] points;
    private PerimeterCalculator perimeterCalculator = new PerimeterCalculator();

    @BeforeClass
    public void setUp()
    {
        points = new Point[3][4];
        points[0] = new Point[]{
                new Point(0.0,0.0),
                new Point(0.0,2.0),
                new Point(2.0,2.0),
                new Point(2.0,0.0)
        };
        points[1] = new Point[]{
                new Point(2.0,2.0),
                new Point(4.0,7.0),
                new Point(6.0,4.0),
                new Point(4.0,1.0)
        };
        points[2] = new Point[]{
                new Point(-2.0,-4.0),
                new Point(0.0,5.5),
                new Point(0.0,4.7),
                new Point(0.0,-5.5)
        };
        quadrilateral = new Quadrilateral[]
                {
                        new Quadrilateral(points[0]),
                        new Quadrilateral(points[1]),
                        new Quadrilateral(points[2])
                };
    }

    @DataProvider
    public Object[][] calculatePerimeterData()
    {
        return new Object[][]
                {
                        {quadrilateral[0],8},
                        {quadrilateral[1],21.5406},
                        {quadrilateral[2],38.8329}
                };
    }

    @Test(dataProvider = "calculatePerimeterData")
    public void testCalculatePerimeter(Quadrilateral quadrilateral, double expected) {
        double res = perimeterCalculator.calculatePerimeter(quadrilateral);
        assertEquals(res,expected,0.0001);
    }
}
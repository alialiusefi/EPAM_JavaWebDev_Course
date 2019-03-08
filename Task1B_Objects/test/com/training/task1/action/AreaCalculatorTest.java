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

public class AreaCalculatorTest {

    private Quadrilateral[] quadrilateral;

    private AreaCalculator areaCalculator = new AreaCalculator();
    public static Logger LOGGER = LogManager.getLogger(AreaCalculatorTest.class);
    @BeforeClass
    public void setUp()
    {
        Point[][] points = new Point[3][4];
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
        try {
            quadrilateral = new Quadrilateral[]
                    {
                            new Quadrilateral(1,points[0]),
                            new Quadrilateral(2,points[1]),
                            new Quadrilateral(3,points[2])
                    };
        }
        catch (InvalidDataException e)
        {
           LOGGER.warn(e.getMessage(),e);
        }
    }
    @DataProvider
    public Object[][] calculateAreaData()
    {
        return new Object[][]
                {
                        {quadrilateral[0],4},
                        {quadrilateral[1],12},
                        {quadrilateral[2],11}
                };
    }
    @Test(dataProvider = "calculateAreaData")
    public void testCalculateArea(Quadrilateral quadrilateral,double expected) {
        final double actual = areaCalculator.calculateArea(quadrilateral);
        assertEquals(actual,expected);
    }
}
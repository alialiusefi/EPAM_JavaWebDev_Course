package com.training.task1.creator;

import com.training.task1.entity.Point;
import com.training.task1.exception.InvalidDataException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PointFactoryTest {

    private PointFactory pointFactory = new PointFactory();
    @DataProvider
    public Object[][] testCreatePointData()
    {
        return new Object[][]
                {
                        {new double[]{1.0,1.0},new Point(1.0,1.0)},
                        {new double[]{2.0,-0.1},new Point(2.0,-0.1)},
                        {new double[]{122,1}, new Point(122,1)}
                };
    }

    @Test(dataProvider = "testCreatePointData")
    public void testCreatePoint(double[] coord, Point expected) {
        try
        {
            Point actual = pointFactory.createPoint(coord);
            assertEquals(actual,expected);
        }
        catch (InvalidDataException e)
        {
            PointFactory.LOGGER.warn(e.getMessage(),e);
        }
    }
}
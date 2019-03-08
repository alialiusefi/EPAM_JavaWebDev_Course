package com.training.task1.creator;

import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;
import com.training.task1.exception.InvalidDataException;
import javafx.util.Pair;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;


public class QuadrilateralFactoryTest {

    private  Quadrilateral[] expectedQuadrilateral;
    private Point[][] points;
    private QuadrilateralFactory quadrilateralFactory = new QuadrilateralFactory();
    private List<Pair<String, String>> listPoints;

    @DataProvider
    public Object[][] createQuadrilateralTestData() {
        points = new Point[3][4];
        points[0] = new Point[]{
                new Point(0.0, 0.0),
                new Point(0.0, 2.0),
                new Point(2.0, 2.0),
                new Point(2.0, 0.0)
        };
        points[1] = new Point[]{
                new Point(2.0, 2.0),
                new Point(4.0, 7.0),
                new Point(6.0, 4.0),
                new Point(4.0, 1.0)
        };
        points[2] = new Point[]{
                new Point(-2.0, -4.0),
                new Point(0.0, 5.5),
                new Point(0.0, 4.7),
                new Point(0.0, -5.5)
        };
        try {
            expectedQuadrilateral = new Quadrilateral[]
                    {
                            new Quadrilateral(points[0]),
                            new Quadrilateral(points[1]),
                            new Quadrilateral(points[2])
                    };
        } catch (InvalidDataException e) {
            QuadrilateralFactory.LOGGER.warn(e.getMessage(), e);
        }


        return new Object[][]{
                {points[0], expectedQuadrilateral[0]},
                {points[1], expectedQuadrilateral[1]},
                {points[2], expectedQuadrilateral[2]},

        };

    }

    @DataProvider
    public Object[][] createQuadrilateral2TestData() throws InvalidDataException {
        expectedQuadrilateral = new Quadrilateral[1];
        expectedQuadrilateral[0] = new Quadrilateral(new Point[]{
                new Point(0.0, 0.0),
                new Point(0.0, 2.0),
                new Point(2.0, 2.0),
                new Point(2.0, 0.0)
        });
        listPoints = new ArrayList<>();
        listPoints.add(new Pair<>("0.0", "0.0"));
        listPoints.add(new Pair<>("0.0", "2.0"));
        listPoints.add(new Pair<>("2.0", "2.0"));
        listPoints.add(new Pair<>("2.0", "0.0"));
        return new Object[][]{
                {listPoints, expectedQuadrilateral[0]}
        };
    }

    @Test(dataProvider = "createQuadrilateralTestData")
    public void testCreateQuadrilateral(Point[] points, Quadrilateral expectedQuad) {
        try {
            Quadrilateral actual = quadrilateralFactory.createQuadrilateral(points);
            assertEquals(actual, expectedQuad);
        } catch (InvalidDataException e) {
            QuadrilateralFactory.LOGGER.warn(e.getMessage(), e);
        }

    }

    @Test(dataProvider = "createQuadrilateralTestData")
    public void testCreateQuadrilateral1(Point[] points, Quadrilateral expectedQuad) {
        try {

            Quadrilateral actual = quadrilateralFactory.createQuadrilateral(
                    points[0], points[1], points[2], points[3]);
            assertEquals(actual, expectedQuad);
        } catch (InvalidDataException e) {
            QuadrilateralFactory.LOGGER.warn(e.getMessage(), e);
        }
    }

    @Test(dataProvider = "createQuadrilateral2TestData")
    public void testCreateQuadrilateral2(List<Pair<String, String>> listPoints, Quadrilateral quadrilateral) {
        try {
            Quadrilateral actual = quadrilateralFactory.createQuadrilateral(listPoints);
            assertEquals(actual, quadrilateral);
        } catch (InvalidDataException e) {
            QuadrilateralFactory.LOGGER.warn(e.getMessage(), e);
        }


    }
}
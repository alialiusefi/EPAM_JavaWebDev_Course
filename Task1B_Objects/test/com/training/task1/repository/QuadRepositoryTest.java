package com.training.task1.repository;

import com.training.task1.creator.PointFactory;
import com.training.task1.creator.QuadrilateralFactory;
import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;
import com.training.task1.exception.InvalidDataException;
import com.training.task1.registrator.QuadRegisterator;
import com.training.task1.repository.specification.FindByIDQuadRegistratorSpecification;
import com.training.task1.repository.specification.FindByIDQuadrilateralSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class QuadRepositoryTest {

    QuadrilateralFactory quadrilateralFactory = new QuadrilateralFactory();
    PointFactory pointFactory = new PointFactory();
    Quadrilateral[] quadrilaterals;
    Point[] points;
    @BeforeClass
    public void setUp() {
        quadrilaterals = new Quadrilateral[2];
        points = new Point[4];
        points[0] = pointFactory.createPoint(0.0, 0.0);
        points[1] = pointFactory.createPoint(0.0, 2.0);
        points[2] = pointFactory.createPoint(2.0, 2.0);
        points[3] = pointFactory.createPoint(2.0, 0.0);

    }

    @Test
    public void testAddQuadrilateral() {

        try {
            quadrilaterals[0] = quadrilateralFactory.createQuadrilateral(1,points);
        } catch (InvalidDataException e) {
            QuadrilateralFactory.logger.warn(e.getMessage(),e);
        }
        List<Quadrilateral> quadrilateralsList =
                QuadRepository.getInstance().queryQuadrilaterals(
                        new FindByIDQuadrilateralSpecification(1));
        assertEquals(quadrilateralsList.get(0),quadrilaterals[0]);
    }


    @Test
    public void testAddQuadRegisterator() {
        try {
            quadrilaterals[0] = quadrilateralFactory.createQuadrilateral(2,points);
        } catch (InvalidDataException e) {
            QuadrilateralFactory.logger.warn(e.getMessage(),e);
        }
        List<QuadRegisterator> quadRegistList =
                QuadRepository.getInstance().queryQuadRegistrators(
                        new FindByIDQuadRegistratorSpecification(2));
        assertTrue(quadRegistList.size() == 1);
    }

    @Test
    public void testRemoveQuadrilateral() {
    }

    @Test
    public void testRemoveQuadRegisterator() {
    }

    @Test
    public void testRemoveQuadRegistrator() {
    }

    @Test
    public void testRemoveQuadrilateral1() {
    }

    @Test
    public void testQueryQuadRegistrators() {
    }

    @Test
    public void testQueryQuadrilaterals() {
    }

    @Test
    public void testReplaceQuadrilateral() {
    }

    @Test
    public void testReplaceQuadRegisterator() {
    }
}
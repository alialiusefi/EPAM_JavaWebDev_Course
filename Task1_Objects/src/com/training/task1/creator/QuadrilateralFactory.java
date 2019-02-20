package com.training.task1.creator;

import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;
import com.training.task1.action.PropertyChecker;

/**
 * Class that creates quadrilateral.
 */
public final class QuadrilateralFactory {

    public Quadrilateral createQuadrilateral(Point[] points) {
        if (points != null && new PropertyChecker().isQuadrilateral(points)) {
            return new Quadrilateral(points);
        }
        return null;
    }

    public Quadrilateral createQuadrilateral(final Point x1, final Point x2,
                                             final Point x3, final Point x4) {
        if (x1 != null && x2 != null && x3 != null && x4 != null) {
            Point[] points = new Point[]{x1, x2, x3, x4};
            if (new PropertyChecker().isQuadrilateral(points)) {
                return new Quadrilateral(points);
            }
        }
        return null;
    }
}

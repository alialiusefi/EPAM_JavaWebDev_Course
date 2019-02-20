package com.training.task1.action;

import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;

/**
 * Class that checks for properties of a quadrilateral
 */
public final class PropertyChecker {
    /**
     * @param points array of points in a coordinate plane
     * @return returns true if points form a quadrilateral, otherwise false
     */
    public boolean isQuadrilateral(final Point[] points) {
        int pointsLength = points.length;
        for (int i = 0; i < pointsLength - 1; i++) {
            if (points[i].equals(points[i + 1])) {
                return false;
            }
        }
        return true;
    }
    /**
     * @param quadrilateral
     * @return returns true if a quadrilateral is concave, otherwise false
     */
    public boolean isConcave(final Quadrilateral quadrilateral) {
        int pointsLength = quadrilateral.getPointsLength();
        final double OFFSET = 20.00;
        double[] xCoord = getXCoordArray(quadrilateral);
        double[] yCoord = getYCoordArray(quadrilateral);
        double testX = xCoord[0] - OFFSET;
        double testy = yCoord[0] + OFFSET;
        boolean c = false;
        for (int i = 0, j = pointsLength - 1; i < pointsLength; j = i++) {
            if (((yCoord[i] > testy) != (yCoord[j] > testy))
                    && (testX < (xCoord[j] - xCoord[i]) * (testy - yCoord[i])
                    / (yCoord[j] - yCoord[i]) + xCoord[i])) {
                c = !c;
            }
        }
        return c;
    }
   /**
     * @param obj
     * @return returns true if the quadrilateral is a square, otherwise false
     */
    public boolean isSquare(final Quadrilateral obj) {
        return isSidesEqual(obj);
    }

    /**
     * @param obj
     * @return returns true if the quadrilateral is a rhombus, otherwise false
     */
    public boolean isRhombus(final Quadrilateral obj) {
        return isSidesEqual(obj) &&
                isParallel(obj.getPoint(0), obj.getPoint(1),
                        obj.getPoint(2), obj.getPoint(3));
    }

    /**
     * @param obj
     * @return returns true if the quadrilateral is a trapezoid, otherwise false
     */
    public boolean isTrapezoid(final Quadrilateral obj) {
        return isParallel(obj.getPoint(0), obj.getPoint(3),
                obj.getPoint(1), obj.getPoint(2));
    }

    /**
     * @param obj
     * @return returns true if the sides of a quadrilateral are equal in length, otherwise false
     */
    private boolean isSidesEqual(final Quadrilateral obj) {
        double[] xCoord = getXCoordArray(obj);
        double[] yCoord = getYCoordArray(obj);
        double[] sides = new PerimeterCalculator().getSides(xCoord, yCoord);
        for (int i = 0; i < sides.length - 1; i++) {
            Double prev = sides[i];
            Double next = sides[i + 1];
            if (prev.compareTo(next) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param obj
     * @return returns true if the sides of a quadrilateral are parallel, otherwise false
     */
    private boolean isParallel(final Point pointA, final Point pointB,
                               final Point pointC, final Point pointD) {
        Double slope1 = (pointB.getY() - pointA.getY())
                / (pointB.getX() - pointA.getX());
        Double slope2 = (pointD.getY() - pointC.getY())
                / (pointD.getX() - pointC.getX());
        return slope1.compareTo(slope2) == 0;
    }

    /**
     * @param quadrilateral
     * @return returns an array of coordinates in x-axis
     */
    private double[] getXCoordArray(final Quadrilateral quadrilateral) {
        int pointsLength = quadrilateral.getPointsLength();
        double[] xCoord = new double[pointsLength];
        for (int i = 0; i < pointsLength; i++) {
            xCoord[i] = quadrilateral.getPoint(i).getX();
        }
        return xCoord;
    }

    /**
     * @param quadrilateral
     * @return returns an array of coordinates in y-axis
     */
    private double[] getYCoordArray(final Quadrilateral quadrilateral) {
        int pointsLength = quadrilateral.getPointsLength();
        double[] yCoord = new double[pointsLength];
        for (int i = 0; i < pointsLength; i++) {
            yCoord[i] = quadrilateral.getPoint(i).getY();
        }
        return yCoord;
    }
}

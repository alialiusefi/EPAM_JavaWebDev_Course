package com.training.task1.action;

import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;

/**
 * Class that checks for properties of a quadrilateral.
 */
public final class PropertyChecker {
    /**
     * @param points array of points in a coordinate plane
     * @return returns true if points form a quadrilateral, otherwise false
     */
    private static final int QUADRILATERAL_AMOUNT_OF_SIDES = 4;

    /**
     * Checks if array of points forms a quadrilateral.
     *
     * @param points array of points
     * @return true if points form a quadrilateral otherwise false.
     */
    public boolean isQuadrilateral(final Point[] points) {
        if (points == null || points.length != QUADRILATERAL_AMOUNT_OF_SIDES) {
            return false;
        }
        int pointsLength = points.length;
        for (int i = 0; i < pointsLength - 1; i++) {
            if (points[i].equals(points[i + 1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param quadrilateral Quadrilateral Shape Object
     * @return returns true if a quadrilateral is concave, otherwise false
     */
    public boolean isConcave(final Quadrilateral quadrilateral) {
        int pointsLength = quadrilateral.getPointsLength();
        final double offset = 20.00;
        double[] xCoord = findXCoordinates(quadrilateral);
        double[] yCoord = findYCoordinates(quadrilateral);
        double testX = xCoord[0] - offset;
        double testy = yCoord[0] + offset;
        boolean flag = false;
        for (int i = 0, j = pointsLength - 1; i < pointsLength; j = i++) {
            if (((yCoord[i] > testy) != (yCoord[j] > testy))
                    && (testX < (xCoord[j] - xCoord[i]) * (testy - yCoord[i])
                    / (yCoord[j] - yCoord[i]) + xCoord[i])) {
                flag = !flag;
            }
        }
        return flag;
    }

    /**
     * @param quadrilateral Quadrilateral Shape Object
     * @return returns true if the quadrilateral is a square, otherwise false
     */
    public boolean isSquare(final Quadrilateral quadrilateral) {
        return isSidesEqual(quadrilateral);
    }

    /**
     * @param quadrilateral Quadrilateral Shape Object
     * @return returns true if the quadrilateral is a rhombus, otherwise false
     */
    public boolean isRhombus(final Quadrilateral quadrilateral) {
        return isSidesEqual(quadrilateral)
                && isParallel(quadrilateral.getPoint(0),
                quadrilateral.getPoint(1),
                quadrilateral.getPoint(2),
                quadrilateral.getPoint(3));
    }

    /**
     * @param quadrilateral Quadrilateral Shape Object
     * @return returns true if the quadrilateral is a trapezoid, otherwise false
     */
    public boolean isTrapezoid(final Quadrilateral quadrilateral) {
        return isParallel(quadrilateral.getPoint(0),
                quadrilateral.getPoint(3),
                quadrilateral.getPoint(1),
                quadrilateral.getPoint(2));
    }

    /**
     * @param quadrilateral Quadrilateral Shape Object
     * @return returns true if the sides of a quadrilateral are
     * equal in length, otherwise false
     */
    private boolean isSidesEqual(final Quadrilateral quadrilateral) {
        double[] xCoord = findXCoordinates(quadrilateral);
        double[] yCoord = findYCoordinates(quadrilateral);
        double[] sides = findSides(xCoord, yCoord);
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
     * @param pointA 1st point in a quad
     * @param pointB 2nd point in a quad
     * @param pointC 3rd point in a quad
     * @param pointD 4th point in a quad
     * @return returns true if the opposing line of a figure are parallel.
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
     * @param quadrilateral Quadrilateral Shape Object
     * @return returns an array of points that beling in x-axis
     */
    private double[] findXCoordinates(final Quadrilateral quadrilateral) {
        int pointsLength = quadrilateral.getPointsLength();
        double[] xCoord = new double[pointsLength];
        for (int i = 0; i < pointsLength; i++) {
            xCoord[i] = quadrilateral.getPoint(i).getX();
        }
        return xCoord;
    }

    private double[] findYCoordinates(final Quadrilateral quadrilateral) {
        int pointsLength = quadrilateral.getPointsLength();
        double[] yCoord = new double[pointsLength];
        for (int i = 0; i < pointsLength; i++) {
            yCoord[i] = quadrilateral.getPoint(i).getY();
        }
        return yCoord;
    }

    private double[] findSides(final double[] xCoord, final double[] yCoord) {
        double[] sides = new double[xCoord.length];
        for (int i = 0; i < sides.length; i++) {
            if (i == sides.length - 1) {
                sides[sides.length - 1] = Math.hypot(xCoord[0]
                        - xCoord[sides.length - 1], yCoord[0]
                        - yCoord[sides.length - 1]);
                continue;
            }
            sides[i] = Math.hypot(xCoord[i + 1] - xCoord[i],
                    yCoord[i + 1] - yCoord[i]);

        }
        return sides;
    }
}

package com.training.task1.action;

import com.training.task1.entity.Quadrilateral;

/**
 * This Class allows us to calculate perimeter.
 */
public final class PerimeterCalculator {

    /**
     * @param obj a Quadrilateral to calculate its perimeter
     * @return returns a double value that is the perimeter of the Quadrilateral
     */
    public double calculatePerimeter(final Quadrilateral obj) {
        double[] xCoord = findXCoordinates(obj);
        double[] yCoord = findYCoordinates(obj);
        double[] sides = findSides(xCoord, yCoord);
        double res = 0;
        for (int i = 0; i < sides.length; i++) {
            res += sides[0];
        }
        return res;
    }

    /**
     * @param xCoord an array of all coordinates in x-axis
     * @param yCoord an array of all coordinates in y-axis
     * @return returns an array of double values that
     * are the sides of a quadrilateral
     */
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

    /**
     * @param quadrilateral Quadrilateral Shape Object
     * @return returns an array of coordinates in x-axis
     */
    private double[] findXCoordinates(final Quadrilateral quadrilateral) {
        final int pointsLength = quadrilateral.getPointsLength();
        double[] xCoord = new double[pointsLength];
        for (int i = 0; i < pointsLength; i++) {
            xCoord[i] = quadrilateral.getPoint(i).getX();
        }
        return xCoord;
    }

    /**
     * @param quadrilateral Quadrilateral Shape Object
     * @return returns an array of coordinates in y-axis
     */
    private double[] findYCoordinates(final Quadrilateral quadrilateral) {
        final int pointsLength = quadrilateral.getPointsLength();
        double[] yCoord = new double[pointsLength];
        for (int i = 0; i < pointsLength; i++) {
            yCoord[i] = quadrilateral.getPoint(i).getY();
        }
        return yCoord;
    }

}

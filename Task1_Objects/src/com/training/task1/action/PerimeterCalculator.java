package com.training.task1.action;

import com.training.task1.entity.Quadrilateral;

/**
 *  This Class allows us to calculate perimeter
 */
public final class PerimeterCalculator {

    /**
     * @param obj a Quadrilateral to calculate its perimeter
     * @return returns a double value that is the perimeter of the Quadrilateral
     */
    public double calculatePerimeter(final Quadrilateral obj) {
        double[] xCoord = getXCoordArray(obj);
        double[] yCoord = getYCoordArray(obj);
        double[] sides = getSides(xCoord, yCoord);
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
    double[] getSides(final double[] xCoord, final double[] yCoord) {
        final int amountOfSides = 4;
        double[] sides = new double[amountOfSides];
        for (int i = 0; i < sides.length; i++) {
            if (i == sides.length - 1) {
                sides[sides.length - 1] = Math.sqrt(Math.pow(xCoord[0]
                        - xCoord[sides.length - 1], 2) + Math.pow(yCoord[0]
                        - yCoord[sides.length - 1], 2));
                continue;
            }
            sides[i] = Math.sqrt(Math.pow(xCoord[i + 1] - xCoord[i], 2)
                    + Math.pow(yCoord[i + 1] - yCoord[i], 2));

        }
        return sides;
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
     * @return returns an array of coordinates in x-axis
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

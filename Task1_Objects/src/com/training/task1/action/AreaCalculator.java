package com.training.task1.action;

import com.training.task1.entity.Quadrilateral;

/**
 * This class allows us to calculate any quadrilateral in a coordinate plane.
 */

public final class AreaCalculator {

    /**
     * @param obj a Quadrilateral to calculate its area
     * @return returns a double value that is the area of the Quadrilateral
     */
    public double calculateArea(final Quadrilateral obj) {
        double res = 0;
        final int amountsOfCoordinates = obj.getPointsLength();
        double[] xCoord = getXCoordArray(obj);
        double[] yCoord = getYCoordArray(obj);
        int j = amountsOfCoordinates - 1;
        for (int i = 0; i < amountsOfCoordinates; i++) {
            res += (xCoord[j] + xCoord[i]) * (yCoord[j] - yCoord[i]);
            j = i;
        }
        return Math.abs(res / 2);
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

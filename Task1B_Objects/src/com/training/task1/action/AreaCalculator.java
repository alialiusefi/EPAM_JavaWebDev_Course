package com.training.task1.action;

import com.training.task1.entity.Quadrilateral;

/**
 * This class allows us to calculate any quadrilateral in a coordinate plane.
 */

public final class AreaCalculator {

    /**
     * @param quadrilateral Quadrilateral Shape Object
     * @return returns a double value that is the area of the Quadrilateral
     */
    public double calculateArea(final Quadrilateral quadrilateral) {
        double res = 0;
        final int amountsOfCoordinates = quadrilateral.getPointsLength();
        double[] xCoord = findXCoordArray(quadrilateral);
        double[] yCoord = findYCoordArray(quadrilateral);
        int j = amountsOfCoordinates - 1;
        for (int i = 0; i < amountsOfCoordinates; i++) {
            res += (xCoord[j] + xCoord[i]) * (yCoord[j] - yCoord[i]);
            j = i;
        }
        return Math.abs(res / 2);
    }

    /**
     * @param quadrilateral Quadrilateral Shape Object Quadrilateral Shape Object
     * @return returns an array of coordinates in x-axis
     */
    private double[] findXCoordArray(final Quadrilateral quadrilateral) {
        int pointsLength = quadrilateral.getPointsLength();
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
    private double[] findYCoordArray(final Quadrilateral quadrilateral) {
        int pointsLength = quadrilateral.getPointsLength();
        double[] yCoord = new double[pointsLength];
        for (int i = 0; i < pointsLength; i++) {
            yCoord[i] = quadrilateral.getPoint(i).getY();
        }
        return yCoord;
    }


}

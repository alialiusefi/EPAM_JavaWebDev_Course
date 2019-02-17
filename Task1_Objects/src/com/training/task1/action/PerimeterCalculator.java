package com.training.task1.action;

import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;

public final class PerimeterCalculator {

    public double calculatePerimeter(final Quadrilateral obj) {
        Point[] points = obj.getPoints();
        int amountOfCoordinates = 4;
        double[] xCoord = new double[amountOfCoordinates];
        double[] yCoord = new double[amountOfCoordinates];
        for (int i = 0; i < points.length; i++) {
            xCoord[i] = points[i].getX();
            yCoord[i] = points[i].getY();
        }
        double[] sides = getSides(xCoord, yCoord);
        double res = 0;
        for (int i = 0; i < sides.length; i++) {
            res += sides[0];
        }
        return res;
    }

    double[] getSides(final double[] xCoord, final double[] yCoord) {
        double[] sides = new double[4];
        for (int i = 0; i < sides.length; i++) {
            if (i == 3) {
                sides[3] = Math.sqrt(Math.pow(xCoord[0] - xCoord[3], 2)
                        + Math.pow(yCoord[0] - yCoord[3], 2));
                continue;
            }
            sides[i] = Math.sqrt(Math.pow(xCoord[i + 1] - xCoord[i], 2)
                    + Math.pow(yCoord[i + 1] - yCoord[i], 2));

        }
        return sides;
    }

}

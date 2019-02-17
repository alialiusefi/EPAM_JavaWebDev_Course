package com.training.task1.action;

import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;

public final class AreaCalculator {

    public double calculateArea(final Quadrilateral obj) {
        double res = 0;
        int amountOfCoordinates = 4;
        Point[] points = obj.getPoints();
        double[] xCoord = new double[amountOfCoordinates];
        double[] yCoord = new double[amountOfCoordinates];
        for (int i = 0; i < points.length; i++) {
            xCoord[i] = points[i].getX();
            yCoord[i] = points[i].getY();
        }
        int j = points.length - 1;
        for (int i = 0; i < points.length; i++) {
            res += (xCoord[j] + xCoord[i]) * (yCoord[j] - yCoord[i]);
            j = i;
        }
        return Math.abs(res / 2);
    }


}

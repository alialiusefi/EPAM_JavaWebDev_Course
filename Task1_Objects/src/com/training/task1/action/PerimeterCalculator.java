package com.training.task1.action;

import com.training.task1.entity.Quadrilateral;

public class PerimeterCalculator {

    public double calculatePerimeter(Quadrilateral obj) {
        double[] xCoord = new double[4];
        double[] yCoord = new double[4];
        xCoord[0] = obj.getX1().getX();
        yCoord[0] = obj.getX1().getY();
        xCoord[1] = obj.getX2().getX();
        yCoord[1] = obj.getX2().getY();
        xCoord[2] = obj.getX3().getX();
        yCoord[2] = obj.getX3().getY();
        xCoord[3] = obj.getX4().getX();
        yCoord[3] = obj.getX4().getY();
        double[] sides = new double[4];
        sides[0] = Math.sqrt( Math.pow(xCoord[1] - xCoord[0],2) + Math.pow(yCoord[1] - yCoord[0],2));
        sides[1] = Math.sqrt( Math.pow(xCoord[2] - xCoord[1],2) + Math.pow(yCoord[2] - yCoord[1],2));
        sides[2] = Math.sqrt( Math.pow(xCoord[3] - xCoord[2],2) + Math.pow(yCoord[3] - yCoord[2],2));
        sides[3] = Math.sqrt( Math.pow(xCoord[0] - xCoord[3],2) + Math.pow(yCoord[0] - yCoord[3],2));
        double res = 0;
        for (int i = 0; i < sides.length; i++)
        {
            res += sides[0];
        }
        return res;
    }
}

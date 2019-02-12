package com.training.task1.action;

import com.training.task1.entity.Quadrilateral;

public class AreaCalculator {

    public double calculateArea(Quadrilateral obj)
    {
        double res = (obj.getX1().getX() - obj.getX2().getX()) * (obj.getX1().getY() + obj.getX2().getY());
        res = res + (obj.getX2().getX() - obj.getX3().getX()) * (obj.getX2().getY() + obj.getX3().getY());
        res = res + (obj.getX3().getX() - obj.getX4().getX()) * (obj.getX3().getY() + obj.getX4().getY());
        res = res + (obj.getX4().getX() - obj.getX1().getX()) * (obj.getX4().getY() + obj.getX1().getY());
        return res / 2;
    }


}

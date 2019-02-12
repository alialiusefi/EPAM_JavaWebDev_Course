package com.training.task1.action;

import com.training.task1.entity.Quadrilateral;

public class StatusChecker {

    public boolean isQuadrilateral(Quadrilateral obj)
    {
        if(obj.getX1().equals(obj.getX2()) && obj.getX2().equals(obj.getX3())) {
            return false;
        }
        if(obj.getX1().equals(obj.getX3()) && obj.getX3().equals(obj.getX4())) {
            return false;
        }
        if(obj.getX1().equals(obj.getX2()) && obj.getX2().equals(obj.getX4())) {
            return false;
        } else {
            return true;
        }
    }
    public boolean isConcave(Quadrilateral quadrilateral)
    {
        return false;
    }
    public boolean isSquare(Quadrilateral quadrilateral)
    {
        return false;
    }
    public boolean isRhombus(Quadrilateral quadrilateral)
    {
        return false;
    }
    public boolean isTrapezoid(Quadrilateral quadrilateral)
    {
        return false;

    }

}

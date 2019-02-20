package com.training.task1.creator;

import com.training.task1.entity.Point;

/**
 * Class that creates points
 */
public final class PointFactory {

    public Point createPoint(double x, double y)
    {
        return new Point(x,y);
    }
    public Point createPoint(double[] x)
    {
        return new Point(x[0],x[1]);
    }


}

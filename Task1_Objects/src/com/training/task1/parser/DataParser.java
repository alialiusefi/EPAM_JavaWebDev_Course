package com.training.task1.parser;

import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;
import com.training.task1.creator.*;

public final class DataParser {

    public Point toPoint(final String[] stringCoordinates) {
        double x = Double.parseDouble(stringCoordinates[0]);
        double y = Double.parseDouble(stringCoordinates[1]);
        return new PointFactory().createPoint(x,y) ;
    }
    public Quadrilateral toQuadrilateral(final String[][] stringCoordinates)
    {
        Point[] points = new Point[stringCoordinates.length];
        for(int i = 0; i < stringCoordinates.length; i++)
        {
            points[i] = toPoint(stringCoordinates[i]);
        }
        return new QuadrilateralFactory().createQuadrilateral(points);
    }


}

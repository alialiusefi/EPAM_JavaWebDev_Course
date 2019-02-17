package com.training.task1.parser;

import com.training.task1.entity.Point;

public final class DataParser {

    public Point toPoint(final String[] stringCoordinates) {
        double x = Double.parseDouble(stringCoordinates[0]);
        double y = Double.parseDouble(stringCoordinates[1]);
        return new Point(x, y);
    }

}

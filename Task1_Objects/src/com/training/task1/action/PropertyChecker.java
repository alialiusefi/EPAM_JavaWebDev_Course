package com.training.task1.action;

import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;

public final class PropertyChecker {

    public boolean isQuadrilateral(final Quadrilateral obj) {
        Point[] points = obj.getPoints();
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].equals(points[i + 1])) {
                return false;
            }
        }
        return true;
    }

    //public boolean isConcave(Quadrilateral quadrilateral)

    public boolean isSquare(final Quadrilateral obj) {
        return isSidesEqual(obj);
    }

    public boolean isRhombus(final Quadrilateral obj) {
        Point[] points = obj.getPoints();
        return isSidesEqual(obj) && isParallel(points[0], points[1], points[2], points[3]);
    }

    public boolean isTrapezoid(final Quadrilateral obj) {
        Point[] points = obj.getPoints();
        return isParallel(points[0], points[3], points[1], points[2]);
    }

    private boolean isSidesEqual(final Quadrilateral obj) {
        Point[] points = obj.getPoints();
        double[] xCoord = new double[4];
        double[] yCoord = new double[4];
        for (int i = 0; i < points.length; i++) {
            xCoord[i] = points[i].getX();
            yCoord[i] = points[i].getY();
        }
        double[] sides = new PerimeterCalculator().getSides(xCoord, yCoord);
        for (int i = 0; i < sides.length - 1; i++) {
            Double prev = sides[i];
            Double next = sides[i + 1];
            if (prev.compareTo(next) != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isParallel(final Point pointA, final Point pointB,
                               final Point pointC, final Point pointD) {
        Double slope1 = (pointB.getY() - pointA.getY())
                / (pointB.getX() - pointA.getX());
        Double slope2 = (pointD.getY() - pointC.getY())
                / (pointD.getX() - pointC.getX());
        return slope1.compareTo(slope2) == 0;
    }
}

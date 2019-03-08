package com.training.task1.entity;

import com.training.task1.exception.InvalidDataException;

import java.util.Arrays;

/**
 * Represents a shape that is a quadrilateral.
 */
public final class Quadrilateral {

    private Point[] points;

    public Quadrilateral(Point[] points) throws InvalidDataException {
        if (points.length == 4) {
            this.points = points;
        } else {
            throw new InvalidDataException("Point array length is not equal to 4");
        }
    }

    private Point[] getPoints() {
        return points;
    }

    public Point getPoint(final int index) {
        if (index < points.length && index >= 0) {
            return points[index];
        }
        return null;
    }

    public void setPoints(final int index, final Point point) {
        if (index < points.length && index >= 0 && point != null) {
            this.points[index] = point;
        }
    }

    public int getPointsLength() {
        return points.length;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quadrilateral that = (Quadrilateral) o;
        return Arrays.equals(getPoints(), that.getPoints());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getPoints());
    }

    @Override
    public String toString() {
        return "QuadrilateralFactory{"
                + "points=" + Arrays.toString(points)
                + '}';
    }
}

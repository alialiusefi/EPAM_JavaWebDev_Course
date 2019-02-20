package com.training.task1.entity;

import java.util.Arrays;

/**
 * Represents a shape that is a quadrilateral.
 */
public final class Quadrilateral {

    private Point[] points;

    public Quadrilateral(Point[] points) {
        this.points = points;
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
        if (index < points.length && index >= 0) {
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

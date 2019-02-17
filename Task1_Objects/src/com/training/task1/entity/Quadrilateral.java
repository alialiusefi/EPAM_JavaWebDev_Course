package com.training.task1.entity;
import java.util.Arrays;
public final class Quadrilateral {

    private Point[] points;

    public Quadrilateral(Point[] points) {
        this.points = points;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(final Point[] points) {
        this.points = points;
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
        return "Quadrilateral{"
                + "points=" + Arrays.toString(points)
                + '}';
    }
}
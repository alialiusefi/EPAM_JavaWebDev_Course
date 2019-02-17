package com.training.task1.entity;

public final class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(final double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.getX(), getX()) == 0
                && Double.compare(point.getY(), getY()) == 0;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) x;
        hash = 31 * hash + (int) y;
        return hash;
    }

    @Override
    public String toString() {
        return "Point{"
                + "x=" + x
                + ", y=" + y
                + '}';
    }
}

package com.training.task1.entity;

import com.training.task1.exception.InvalidDataException;
import com.training.task1.observer.Observable;
import com.training.task1.observer.Observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a shape that is a quadrilateral.
 */
public final class Quadrilateral implements Observable {

    private int ID;
    public List<Observer> observers;
    private Point[] points;

    public Quadrilateral(int ID, Point[] points) throws InvalidDataException {
        if (points.length == 4 && points[0] != null && points[1] != null
                && points[2] != null
                && points[3] != null
        ) {
            this.ID = ID;
            this.points = points;
            this.observers = new ArrayList<>();
        } else {
            throw new InvalidDataException("Point array length is not equal to 4");
        }
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public int getID()
    {
        return ID;
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
            notifyObeservers();
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

    @Override
    public void attach(Observer observer) {
        if(observer != null)
        {
            observers.add(observer);
        }
    }

    @Override
    public void detach(Observer observer) {
        if(observer != null)
        {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObeservers() {
        for(Observer i : observers)
        {
            i.update();
        }
    }
}

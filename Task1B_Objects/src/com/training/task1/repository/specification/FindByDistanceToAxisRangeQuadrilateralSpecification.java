package com.training.task1.repository.specification;

import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;

import java.util.ArrayList;
import java.util.List;

public class FindByDistanceToAxisRangeQuadrilateralSpecification extends FindSpecification<Quadrilateral> {

    private double from;
    private double to;

    public FindByDistanceToAxisRangeQuadrilateralSpecification(double from, double to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public List<Quadrilateral> find(List<Quadrilateral> list) {
        List<Quadrilateral> result = new ArrayList<>();
        for(Quadrilateral i : list)
        {
            Point[] points = new Point[]{
                    i.getPoint(0),
                    i.getPoint(1),
                    i.getPoint(2),
                    i.getPoint(3)
            };
            double midX = (points[0].getX() + points[1].getX() + points[2].getX() + points[3].getX()) / 4;
            double midY = (points[0].getY() + points[1].getY() + points[2].getY() + points[3].getY()) / 4;
            double distance = Math.hypot(midX,midY);
           if(distance <= to && distance >= from)
           {
               result.add(i);
           }
        }
        return result;
    }


    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }
}

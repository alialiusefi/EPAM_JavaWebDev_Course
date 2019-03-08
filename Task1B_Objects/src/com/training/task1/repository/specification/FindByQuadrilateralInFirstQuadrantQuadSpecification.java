package com.training.task1.repository.specification;

import com.training.task1.entity.Point;
import com.training.task1.entity.Quadrilateral;

import java.util.ArrayList;
import java.util.List;

public class FindByQuadrilateralInFirstQuadrantQuadSpecification extends FindSpecification<Quadrilateral>{

    @Override
    public List<Quadrilateral> find(List<Quadrilateral> list) {
        List<Quadrilateral> result = new ArrayList<>();
        for(Quadrilateral i : list)
        {
            if(isLocatedInFirstQuadrant(i))
            {
                result.add(i);
            }
        }
        return result;
    }

    private boolean isLocatedInFirstQuadrant(Quadrilateral quadrilateral)
    {
        Point[] points = new Point[]
                {
                        quadrilateral.getPoint(0),
                        quadrilateral.getPoint(1),
                        quadrilateral.getPoint(2),
                        quadrilateral.getPoint(3)
                };
        for(Point q : points)
        {
            if(!(q.getX() > 0 && q.getY() > 0 ))
            {
                return false;
            }

        }
        return true;
    }
}

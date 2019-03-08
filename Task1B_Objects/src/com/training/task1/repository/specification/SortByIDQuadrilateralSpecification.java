package com.training.task1.repository.specification;

import com.training.task1.entity.Quadrilateral;

import java.util.List;

public class SortByIDQuadrilateralSpecification extends SortSpecification<Quadrilateral> {


    public SortByIDQuadrilateralSpecification()
    {
        super.comparator = (Quadrilateral o1, Quadrilateral o2)
                -> ((Integer)o1.getID()).compareTo(o2.getPointsLength());
    }

    @Override
    public List<Quadrilateral> sort(List<Quadrilateral> list) {
        list.sort(this.getComparator());
        return list;
    }
}

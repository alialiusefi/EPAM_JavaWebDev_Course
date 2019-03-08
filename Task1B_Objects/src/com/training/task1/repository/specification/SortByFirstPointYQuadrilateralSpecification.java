package com.training.task1.repository.specification;

import com.training.task1.entity.Quadrilateral;

import java.util.List;

public class SortByFirstPointYQuadrilateralSpecification extends SortSpecification<Quadrilateral> {


    public SortByFirstPointYQuadrilateralSpecification()
    {
        super.comparator = (Quadrilateral o1, Quadrilateral o2)
                -> ((Double)o1.getPoint(0).getY()).compareTo(o2.getPoint(0).getY());
    }

    @Override
    public List<Quadrilateral> sort(List<Quadrilateral> list) {
        list.sort(this.getComparator());
        return list;
    }
}
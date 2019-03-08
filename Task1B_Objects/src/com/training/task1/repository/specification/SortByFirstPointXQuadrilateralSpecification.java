package com.training.task1.repository.specification;

import com.training.task1.entity.Quadrilateral;

import java.util.List;

public class SortByFirstPointXQuadrilateralSpecification extends SortSpecification<Quadrilateral> {


    public SortByFirstPointXQuadrilateralSpecification()
    {
        super.comparator = (Quadrilateral o1, Quadrilateral o2)
                -> ((Double)o1.getPoint(0).getX()).compareTo(o2.getPoint(0).getX());
    }

    @Override
    public List<Quadrilateral> sort(List<Quadrilateral> list) {
        list.sort(this.getComparator());
        return list;
    }
}

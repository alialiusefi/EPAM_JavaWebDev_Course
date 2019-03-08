package com.training.task1.repository.specification;

import java.util.Comparator;
import java.util.List;

public abstract class SortSpecification<T> {

    protected Comparator<T> comparator;

    public Comparator getComparator() {
        return comparator;
    }

    public void setComparator(Comparator specificationComparator) {
        this.comparator = specificationComparator;
    }


    public abstract List<T> sort(List<T> list);

}

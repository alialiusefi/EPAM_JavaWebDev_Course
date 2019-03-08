package com.training.task1.repository.specification;

import java.util.List;

public abstract class FindSpecification<T> extends Specification<T>{

    abstract public List<T> find(List<T> list);


}

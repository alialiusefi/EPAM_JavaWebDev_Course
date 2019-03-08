package com.training.task1.repository.specification;

import com.training.task1.registrator.QuadRegisterator;

import java.util.ArrayList;
import java.util.List;

public class FindByAreaRangeQuadRegistrator extends FindSpecification<QuadRegisterator>{

    private double from;
    private double to;

    public FindByAreaRangeQuadRegistrator(double from,double to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public List<QuadRegisterator> find(List<QuadRegisterator> list) {
        List<QuadRegisterator> result = new ArrayList<>();
        for(QuadRegisterator i : list)
        {
            double quadrilateralArea = i.getQuadrilateralArea();
            if(quadrilateralArea <= to && quadrilateralArea >= from)
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

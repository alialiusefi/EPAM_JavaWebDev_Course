package com.training.task1.repository.specification;

import com.training.task1.entity.Quadrilateral;

import java.util.ArrayList;
import java.util.List;

public class FindByIDQuadrilateralSpecification extends FindSpecification<Quadrilateral> {

    private int ID;

    public FindByIDQuadrilateralSpecification(int ID) {
        this.ID = ID;
    }

    @Override
    public List<Quadrilateral> find(List<Quadrilateral> list){
        List<Quadrilateral> result = new ArrayList<>();
        for(Quadrilateral i: list)
        {
            if(i.getID() == this.ID)
            {
                result.add(i);
            }
        }
        return result;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}

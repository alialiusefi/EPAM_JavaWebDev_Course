package com.training.task1.repository.specification;

import com.training.task1.registrator.QuadRegisterator;
import java.util.ArrayList;
import java.util.List;

public class FindByIDQuadRegistratorSpecification extends FindSpecification<QuadRegisterator> {

    private int ID;

    public FindByIDQuadRegistratorSpecification(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<QuadRegisterator> find(List<QuadRegisterator> list)
    {
        List<QuadRegisterator> result = new ArrayList<>();
        for(QuadRegisterator i : list)
        {
            if(i.getID() == this.ID)
            {
                result.add(i);
            }
        }
        return result;
    }
}

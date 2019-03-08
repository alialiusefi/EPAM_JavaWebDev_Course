package com.training.task1.repository;

import com.training.task1.entity.Quadrilateral;
import com.training.task1.registrator.QuadRegisterator;
import com.training.task1.repository.specification.FindByIDQuadRegistratorSpecification;
import com.training.task1.repository.specification.FindByIDQuadrilateralSpecification;
import com.training.task1.repository.specification.FindSpecification;
import com.training.task1.repository.specification.Specification;

import java.util.ArrayList;
import java.util.List;

public class QuadRepository {

    public static final QuadRepository INSTANCE = new QuadRepository();

    private List<Quadrilateral> quadrilaterals = new ArrayList<>();
    private List<QuadRegisterator> quadRegisterators = new ArrayList<>();

    private QuadRepository() { }

    public static QuadRepository getInstance() {
        return INSTANCE;
    }

    public void addQuadrilateral(Quadrilateral quadrilateral)
    {
        if(quadrilateral != null)
        {
            quadrilaterals.add(quadrilateral);
        }
    }

    public void addQuadRegisterator(QuadRegisterator quadRegisterator)
    {
        if(quadRegisterator != null)
        {
            quadRegisterators.add(quadRegisterator);
        }
    }

    public void removeQuadrilateral(Quadrilateral quadrilateral)
    {
        if(quadrilateral != null)
        {
            quadrilaterals.remove(quadrilateral);
        }
    }
    public void removeQuadRegisterator(QuadRegisterator quadRegisterator)
    {
        if(quadRegisterator != null)
        {
            quadRegisterators.remove(quadRegisterator);
        }
    }

    public void removeQuadRegistrator(Specification specification)
    {
        if(specification instanceof FindSpecification)
        {
            List<QuadRegisterator> foundQuadregistrators = queryQuadRegistrators(specification);
            quadRegisterators.removeAll(foundQuadregistrators);
        }
    }
    public void removeQuadrilateral(Specification specification)
    {
        if(specification instanceof FindSpecification)
        {
            List<Quadrilateral> foundQuadrilaterals = queryQuadrilaterals(specification);
            quadrilaterals.removeAll(foundQuadrilaterals);
        }
    }

    //query sort
    public List<QuadRegisterator> queryQuadRegistrators(Specification<QuadRegisterator> specification)
    {
        List<QuadRegisterator> result = new ArrayList<>();
        if(specification instanceof FindSpecification)
        {
            FindSpecification<QuadRegisterator> findSpecification = (FindSpecification) specification;
            result = findSpecification.find(quadRegisterators);
        }
        return result;
    }
    public List<Quadrilateral> queryQuadrilaterals(Specification specification)
    {
        List<Quadrilateral> result = new ArrayList<>();
        if(specification instanceof FindSpecification)
        {
            FindSpecification<Quadrilateral> findSpecification = (FindSpecification) specification;
            result = findSpecification.find(quadrilaterals);
        }
        return result;
    }

    public void replaceQuadrilateral(Quadrilateral quadrilateral)
    {
        if (quadrilateral != null)
        {
            int ID = quadrilateral.getID();
            List<Quadrilateral> quadFound = queryQuadrilaterals(new FindByIDQuadrilateralSpecification(ID));
            quadrilaterals.removeAll(quadFound);
            quadrilaterals.add(quadrilateral);
        }
    }

    public void replaceQuadRegisterator(QuadRegisterator quadRegisterator)
    {
        if (quadRegisterator != null)
        {
            int ID = quadRegisterator.getID();
            List<QuadRegisterator> quadRegFound = queryQuadRegistrators(new FindByIDQuadRegistratorSpecification(ID));
            quadRegisterators.removeAll(quadRegFound);
            quadRegisterators.add(quadRegisterator);
        }
    }

}

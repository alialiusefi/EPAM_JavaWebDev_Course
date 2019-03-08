package com.training.task1.registrator;

import com.training.task1.action.AreaCalculator;
import com.training.task1.action.PerimeterCalculator;
import com.training.task1.entity.Quadrilateral;
import com.training.task1.observer.Observer;
import com.training.task1.repository.QuadRepository;
import com.training.task1.repository.specification.FindByIDQuadrilateralSpecification;

import java.util.List;
import java.util.Objects;

/**
 * Class that contains the area & perimeter of a quadrilateral and realizes Observer pattern
 */
public class QuadRegisterator implements Observer {

    private int ID;
    private double quadrilateralArea;
    private double quadrilateralPerimeter;

    public QuadRegisterator(int ID)
    {
        this.ID = ID;
        List<Quadrilateral> quadrilaterals = QuadRepository.
                getInstance().
                queryQuadrilaterals(
                        new FindByIDQuadrilateralSpecification(this.ID));
        Quadrilateral quadrilateral = quadrilaterals.get(0);
        quadrilateralArea = new AreaCalculator().calculateArea(quadrilateral);
        quadrilateralPerimeter = new PerimeterCalculator().calculatePerimeter(quadrilateral);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getQuadrilateralArea() {
        return quadrilateralArea;
    }

    public void setQuadrilateralArea(double quadrilateralArea) {
        this.quadrilateralArea = quadrilateralArea;
    }

    public double getQuadrilateralPerimeter() {
        return quadrilateralPerimeter;
    }

    public void setQuadrilateralPerimeter(double quadrilateralPerimeter) {
        this.quadrilateralPerimeter = quadrilateralPerimeter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuadRegisterator that = (QuadRegisterator) o;
        return getID() == that.getID() &&
                Double.compare(that.getQuadrilateralArea(), getQuadrilateralArea()) == 0 &&
                Double.compare(that.getQuadrilateralPerimeter(), getQuadrilateralPerimeter()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getQuadrilateralArea(), getQuadrilateralPerimeter());
    }

    @Override
    public void update() {
        List<Quadrilateral> quadrilaterals = QuadRepository.
                getInstance().queryQuadrilaterals(new FindByIDQuadrilateralSpecification(this.ID));
        Quadrilateral quadrilateral = quadrilaterals.get(0);
        this.setQuadrilateralArea(new AreaCalculator().calculateArea(quadrilateral));
        this.setQuadrilateralPerimeter(new PerimeterCalculator().calculatePerimeter(quadrilateral));

    }
}

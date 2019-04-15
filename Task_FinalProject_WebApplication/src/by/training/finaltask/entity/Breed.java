package by.training.finaltask.entity;

import java.util.Objects;

public final class Breed {

    private String name;
    private double minWeight;
    private double maxWeight;
    private int lifeExpectancy;
    private String origin;

    public Breed(String name, double minWeight, double maxWeight,
                 int lifeExpectancy, String origin) {
        this.name = name;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.lifeExpectancy = lifeExpectancy;
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(double minWeight) {
        this.minWeight = minWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(int lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Breed breed = (Breed) o;
        return Double.compare(breed.getMinWeight(), getMinWeight()) == 0 &&
                Double.compare(breed.getMaxWeight(), getMaxWeight()) == 0 &&
                getLifeExpectancy() == breed.getLifeExpectancy() &&
                getName().equals(breed.getName()) &&
                getOrigin().equals(breed.getOrigin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMinWeight(), getMaxWeight(), getLifeExpectancy(), getOrigin());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Breed{");
        sb.append("name='").append(name).append('\'');
        sb.append(", minWeight=").append(minWeight);
        sb.append(", maxWeight=").append(maxWeight);
        sb.append(", lifeExpectancy=").append(lifeExpectancy);
        sb.append(", origin='").append(origin).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

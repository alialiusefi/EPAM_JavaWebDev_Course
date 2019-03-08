package by.training.task2.entity;

import java.util.Objects;

public class Configuration {

    private int amountOfCashiers;
    private int amountOfCustomers;
    private int fromRange;
    private int toRange;

    public Configuration(int amountOfCashiers, int amountOfCustomers, int fromRange, int toRange) {
        this.amountOfCashiers = amountOfCashiers;
        this.amountOfCustomers = amountOfCustomers;
        this.fromRange = fromRange;
        this.toRange = toRange;
    }

    public int getAmountOfCashiers() {
        return amountOfCashiers;
    }

    public void setAmountOfCashiers(int amountOfCashiers) {
        this.amountOfCashiers = amountOfCashiers;
    }

    public int getAmountOfCustomers() {
        return amountOfCustomers;
    }

    public void setAmountOfCustomers(int amountOfCustomers) {
        this.amountOfCustomers = amountOfCustomers;
    }

    public int getFromRange() {
        return fromRange;
    }

    public void setFromRange(int fromRange) {
        this.fromRange = fromRange;
    }

    public int getToRange() {
        return toRange;
    }

    public void setToRange(int toRange) {
        this.toRange = toRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return getAmountOfCashiers() == that.getAmountOfCashiers() &&
                getAmountOfCustomers() == that.getAmountOfCustomers() &&
                getFromRange() == that.getFromRange() &&
                getToRange() == that.getToRange();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmountOfCashiers(), getAmountOfCustomers(), getFromRange(), getToRange());
    }

    @Override
    public String toString() {
        return "Configuration1{" +
                "amountOfCashiers=" + amountOfCashiers +
                ", amountOfCustomers=" + amountOfCustomers +
                ", fromRange=" + fromRange +
                ", toRange=" + toRange +
                '}';
    }
}

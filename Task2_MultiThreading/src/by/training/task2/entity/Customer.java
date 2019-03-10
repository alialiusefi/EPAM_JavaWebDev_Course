package by.training.task2.entity;

import java.util.Objects;

public class Customer {

    private int ID;
    private int timeMilli;

    public Customer(int ID, int timeMilli) {
        this.ID = ID;
        this.timeMilli = timeMilli;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTimeMilli() {
        return timeMilli;
    }

    public void setTimeMilli(int timeMilli) {
        this.timeMilli = timeMilli;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getID() == customer.getID() &&
                getTimeMilli() == customer.getTimeMilli();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getTimeMilli());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID=" + ID +
                ", timeMilli=" + timeMilli +
                '}';
    }
}

package by.training.task2.entity;

import java.util.Objects;

public class Customer {

    private int ID;
    private int timeToPrepareInMilliSeconds;

    public Customer(int ID, int timeToPrepareInMilliSeconds) {
        this.ID = ID;
        this.timeToPrepareInMilliSeconds = timeToPrepareInMilliSeconds;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTimeToPrepareInMilliSeconds() {
        return timeToPrepareInMilliSeconds;
    }

    public void setTimeToPrepareInMilliSeconds(int timeToPrepareInMilliSeconds) {
        this.timeToPrepareInMilliSeconds = timeToPrepareInMilliSeconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getID() == customer.getID() &&
                getTimeToPrepareInMilliSeconds() == customer.getTimeToPrepareInMilliSeconds();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getTimeToPrepareInMilliSeconds());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID=" + ID +
                ", timeToPrepareInMilliSeconds=" + timeToPrepareInMilliSeconds +
                '}';
    }
}

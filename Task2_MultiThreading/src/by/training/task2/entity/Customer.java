package by.training.task2.entity;

import java.util.Objects;

public class Customer {

    private int ID;
<<<<<<< HEAD
    private int timeMilli;

    public Customer(int ID, int timeMilli) {
        this.ID = ID;
        this.timeMilli = timeMilli;
=======
    private int timeToPrepareInMilliSeconds;

    public Customer(int ID, int timeToPrepareInMilliSeconds) {
        this.ID = ID;
        this.timeToPrepareInMilliSeconds = timeToPrepareInMilliSeconds;
>>>>>>> master
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

<<<<<<< HEAD
    public int getTimeMilli() {
        return timeMilli;
    }

    public void setTimeMilli(int timeMilli) {
        this.timeMilli = timeMilli;
=======
    public int getTimeToPrepareInMilliSeconds() {
        return timeToPrepareInMilliSeconds;
    }

    public void setTimeToPrepareInMilliSeconds(int timeToPrepareInMilliSeconds) {
        this.timeToPrepareInMilliSeconds = timeToPrepareInMilliSeconds;
>>>>>>> master
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getID() == customer.getID() &&
<<<<<<< HEAD
                getTimeMilli() == customer.getTimeMilli();
=======
                getTimeToPrepareInMilliSeconds() == customer.getTimeToPrepareInMilliSeconds();
>>>>>>> master
    }

    @Override
    public int hashCode() {
<<<<<<< HEAD
        return Objects.hash(getID(), getTimeMilli());
=======
        return Objects.hash(getID(), getTimeToPrepareInMilliSeconds());
>>>>>>> master
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID=" + ID +
<<<<<<< HEAD
                ", timeMilli=" + timeMilli +
=======
                ", timeToPrepareInMilliSeconds=" + timeToPrepareInMilliSeconds +
>>>>>>> master
                '}';
    }
}

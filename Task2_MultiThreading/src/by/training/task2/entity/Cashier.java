package by.training.task2.entity;

import by.training.task2.exception.IncorrectDataException;

import java.io.IOException;
import java.sql.Time;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cashier implements Callable<Integer> {

    int ID;
    Queue<Customer> customersQueue;
    Lock lock = new ReentrantLock();


    public Cashier(int id, Queue<Customer> customersQueue) {
        this.ID = id;
        this.customersQueue = customersQueue;

    }

    public Customer getCustomerFromRestaurant() throws IOException, IncorrectDataException {
        return Restaurant.getInstance().getUnorderedCustomer();
    }

    @Override
    public String toString() {
        return "Cashier NO: " + ID;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(this.toString() + " initialized!");
        while (Restaurant.getInstance().totalUnServedCustomersCounter != 0) {
            Customer unOrderedcustomer;
            //unordered customer stands in the queue
            if ((unOrderedcustomer = getCustomerFromRestaurant()) != null) {
                customersQueue.add(unOrderedcustomer);
                System.out.println(unOrderedcustomer + " was added in queue of: " + this.toString());
                TimeUnit.MILLISECONDS.sleep(unOrderedcustomer.getTimeMilli());
            } else {
                //no more unordered customers, time to serve
                Customer orderedCustomer;
                if((orderedCustomer = Restaurant.getInstance().getOrderedCustomer())!=null)
                {
                    prepareFood(orderedCustomer);
                    System.out.println(this.toString() + " served " + orderedCustomer);
                }
            }
            //send already ordered customers into restaurant
            if (!customersQueue.isEmpty()) {
                Customer unOrderedCustomerFromQueue = customersQueue.poll();
                Restaurant.getInstance().addOrderedCustomer(unOrderedCustomerFromQueue);
                System.out.println(this.toString() + "received order of" + unOrderedCustomerFromQueue + " and sent back to restaurant");
            }
        }
        System.out.println(this.toString() + " stopped!");
        return 1;
    }

    private void prepareFood(Customer customer) {
        try {
            TimeUnit.MILLISECONDS.sleep(customer.getTimeMilli());
        } catch (InterruptedException r) {
            System.out.println(r.getMessage());
        }
    }
}


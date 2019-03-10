package by.training.task2.entity;

import by.training.task2.exception.IncorrectDataException;

import java.io.IOException;
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
            Customer customer;
            //unordered customer stands in the queue
            if ((customer = getCustomerFromRestaurant()) != null) {
                customersQueue.add(customer);
                System.out.println(customer + " was added in queue of: " + this.toString());
            } else {
                //no more unordered customers, time to serve
                Customer customer1 = Restaurant.getInstance().getOrderedCustomer();
                prepareFood(customer1);
                System.out.println(this.toString() + " served " + customer1);
            }
            //send already ordered customers into restaurant
            if (!customersQueue.isEmpty()) {
                Customer customer1 = customersQueue.poll();
                Restaurant.getInstance().addOrderedCustomer(customer1);
                System.out.println(this.toString() + "received order of" + customer1+ " and sent back to restaurant");
            }
        }
        System.out.println(this.toString() + " stopped!");
        return 1;
    }

    private void prepareFood(Customer customer) {
        try {
            TimeUnit.MILLISECONDS.sleep(customer.getTimeMilli());
            System.out.println(customer + " served!");
        } catch (InterruptedException r) {
            System.out.println(r.getMessage());
        }
    }
}


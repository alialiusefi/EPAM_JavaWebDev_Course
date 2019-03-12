package by.training.task2.entity;

import by.training.task2.exception.IncorrectDataException;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public final class Cashier implements Callable<Integer> {

    private int ID;
    private Queue<Customer> customersQueue;

    public Cashier(final int id, final Queue<Customer> queue) {
        this.ID = id;
        this.customersQueue = queue;

    }

    public Customer getCustomerFromRestaurant()
            throws IOException, IncorrectDataException {
        return Restaurant.getInstance().getUnorderedCustomer();
    }

    public Queue<Customer> getCustomersQueue() {
        return customersQueue;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(this.toString() + " initialized!");
        while (Restaurant.getInstance().getTotalUnServedCustomersCounter() != 0) {
            Customer unOrderedCustomer = getCustomerFromRestaurant();
            if (unOrderedCustomer != null) {
                customersQueue.add(unOrderedCustomer);
                System.out.println(unOrderedCustomer
                        + " was added in queue of: " + this.toString());
                TimeUnit.MILLISECONDS.sleep(unOrderedCustomer.getTimeMilli());
            } else {
                Customer orderedCustomer =
                        Restaurant.getInstance().getOrderedCustomer();
                if (orderedCustomer != null) {
                    prepareFood(orderedCustomer);
                    System.out.println(this.toString()
                            + " served " + orderedCustomer);
                }
            }
            if (!customersQueue.isEmpty()) {
                Customer unOrderedCustomerFromQueue
                        = customersQueue.poll();
                Restaurant.getInstance().addOrderedCustomer(
                        unOrderedCustomerFromQueue);
                System.out.println(this.toString()
                        + " received order of " + unOrderedCustomerFromQueue
                        + " and customer is waiting in OrderedCustomerList to get served");
            }
        }
        System.out.println(this.toString() + " stopped!");
        return 1;
    }

    private void prepareFood(final Customer customer) {
        try {
            System.out.println(this.toString() + " is now serving " + customer + " for " + customer.getTimeMilli());
            TimeUnit.MILLISECONDS.sleep(customer.getTimeMilli());
        } catch (InterruptedException r) {
            System.out.println(r.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Cashier NO: " + ID;
    }
}


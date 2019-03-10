package by.training.task2.entity;

import by.training.task2.exception.IncorrectDataException;
import by.training.task2.generator.CashierGenerator;
import by.training.task2.generator.CustomerGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {

    private static ReentrantLock lock = new ReentrantLock();
    private static Restaurant INSTANCE;
    private Configuration configuration;
    private List<Customer> listOfUnOrderedCustomers;
    private List<Cashier> listOfCashiers;
    private List<Customer> listOfOrderedCustomers;
    public int totalUnServedCustomersCounter;

    private Restaurant() throws IOException, IncorrectDataException {
        configuration = new Configuration();
        listOfUnOrderedCustomers = new CustomerGenerator().generateCustomers(configuration);
        listOfCashiers = new CashierGenerator().generateCashiers(configuration);
        listOfOrderedCustomers = new ArrayList<>();
        totalUnServedCustomersCounter = listOfUnOrderedCustomers.size();
        assignCustomers();
    }

    public static Restaurant getInstance() throws IOException, IncorrectDataException {
        lock.lock();
        try {
            if (INSTANCE == null) {
                INSTANCE = new Restaurant();
            }
        } finally {
            lock.unlock();
        }
        return INSTANCE;
    }

    public List<Cashier> getListOfCashiers() {
        return listOfCashiers;
    }

    public List<Customer> getListOfOrderedCustomers() {
        return listOfOrderedCustomers;
    }

    public Customer getOrderedCustomer() {
        Customer customer = null;
        lock.lock();
        if (!listOfOrderedCustomers.isEmpty()) {
            customer = listOfOrderedCustomers.get(0);
            listOfOrderedCustomers.remove(customer);
            totalUnServedCustomersCounter--;
        }
        lock.unlock();
        return customer;
    }

    public Customer getUnorderedCustomer() {
        Customer customer = null;
        lock.lock();
        if (!listOfUnOrderedCustomers.isEmpty()) {
            customer = listOfUnOrderedCustomers.get(0);
            listOfUnOrderedCustomers.remove(customer);
        }
        lock.unlock();
        return customer;
    }

    public void addOrderedCustomer(Customer customer) {
        lock.lock();
        listOfOrderedCustomers.add(customer);
        lock.unlock();
    }

    public void assignCustomers() {
        if (!listOfUnOrderedCustomers.isEmpty()) {
            for (int i = 0; i < listOfUnOrderedCustomers.size(); i++) {
                int k = 0;
                if (k + 1 % listOfCashiers.size() == 0) {
                    k = 0;
                }
                Customer customer = listOfUnOrderedCustomers.get(i);
                Cashier cashier = listOfCashiers.get(k++);
                cashier.customersQueue.add(customer);
                System.out.println();
            }
        }
    }
}

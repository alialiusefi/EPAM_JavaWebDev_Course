package by.training.task2.entity;

import by.training.task2.generator.CustomerGenerator;

import java.util.List;

// Observer
public class Restaurant {

    private static final String FILE_PATH = "\\data\\config.txt";
    private static final Restaurant INSTANCE = new Restaurant();
    private Restaurant() {
        listOfUnOrderedCustomers = new CustomerGenerator().generateCustomers();
    }

    public static Restaurant getInstance() {
        return INSTANCE;
    }


    List<Cashier> listOfCashiers;
    List<Customer> listOfOrderedCustomers;
    List<Customer> listOfUnOrderedCustomers;

    //assignCustomer(Customer customer)

}

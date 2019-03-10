package by.training.task2.generator;

import by.training.task2.entity.Configuration;
import by.training.task2.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CustomerGenerator {

    public List<Customer> generateCustomers(Configuration configuration)
    {
        int amountOfCustomers = configuration.getAmountOfCustomers();
        int fromRange = configuration.getFromRange();
        int toRange = configuration.getToRange();
        List<Customer> list = new ArrayList<>(amountOfCustomers);
        for(int i = 0; i < amountOfCustomers; i++)
        {
            int time = ThreadLocalRandom.current().nextInt(fromRange,toRange + 1);
            list.add(new Customer(i + 1,time));
        }
        return list;
    }
}

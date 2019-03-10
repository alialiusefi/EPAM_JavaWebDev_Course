package by.training.task2.generator;

import by.training.task2.entity.Cashier;
import by.training.task2.entity.Configuration;
import by.training.task2.entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class CashierGenerator {

    public List<Cashier> generateCashiers(Configuration configuration) {
        int amountOfCashiers = configuration.getAmountOfCashiers();
        List<Cashier> cashiers = new ArrayList<>(amountOfCashiers);
        for (int i = 0; i < amountOfCashiers; i++) {
            cashiers.add(new Cashier(i + 1, new PriorityQueue<Customer>((Customer customer1, Customer customer2) ->
            {
                return 0;
            }
            )));
        }
        return cashiers;
    }

}

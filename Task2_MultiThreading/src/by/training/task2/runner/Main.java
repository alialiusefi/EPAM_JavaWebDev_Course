package by.training.task2.runner;

import by.training.task2.entity.Restaurant;
import by.training.task2.exception.IncorrectDataException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        try {
            int amountOfCashiers = Restaurant.getInstance().getListOfCashiers().size();
            ExecutorService cashiersExecutor = Executors.newFixedThreadPool(amountOfCashiers);
            List<Future<Integer>> cashierFutures =
                    cashiersExecutor.invokeAll(Restaurant.getInstance().getListOfCashiers());
            cashiersExecutor.shutdown();
        } catch (InterruptedException | IOException | IncorrectDataException r) {
            System.out.println(r.getMessage());


        }
    }
}

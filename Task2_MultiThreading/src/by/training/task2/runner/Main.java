package by.training.task2.runner;

<<<<<<< HEAD
import by.training.task2.entity.Cashier;
import by.training.task2.entity.Customer;
import by.training.task2.entity.Restaurant;
import by.training.task2.exception.IncorrectDataException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        try {
            int amountOfCashiers = Restaurant.getInstance().getListOfCashiers().size();
            ExecutorService cashiersExecutor = Executors.newFixedThreadPool(amountOfCashiers);
            ExecutorService restaurantExecutor = Executors.newSingleThreadExecutor();
            try{
                List<Future<Integer>> cashierFutures = cashiersExecutor.invokeAll(Restaurant.getInstance().getListOfCashiers());
                cashiersExecutor.shutdown();
            }
            catch (InterruptedException r)
            {
                System.out.println(r.getMessage());
            }
        } catch (IOException | IncorrectDataException r) {
            System.out.println(r.getMessage());
        }

    }


=======
public class Main {
>>>>>>> master
}

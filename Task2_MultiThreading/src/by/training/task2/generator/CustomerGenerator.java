package by.training.task2.generator;

<<<<<<< HEAD
import by.training.task2.entity.Configuration;
import by.training.task2.entity.Customer;
=======
import by.training.task2.entity.Customer;
import by.training.task2.exception.IncorrectDataException;
import by.training.task2.parser.DataParser;
import by.training.task2.reader.DataReader;
import by.training.task2.validator.DataValidator;

import java.io.File;
import java.io.IOException;
>>>>>>> master
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CustomerGenerator {

<<<<<<< HEAD
    public List<Customer> generateCustomers(Configuration configuration)
    {
        int amountOfCustomers = configuration.getAmountOfCustomers();
        int fromRange = configuration.getFromRange();
        int toRange = configuration.getToRange();
=======
    public List<Customer> generateCustomers(File path) throws IOException, IncorrectDataException
    {
        List<String> readStrings = new DataReader().readData(path);
        List<String> parsedStrings = new DataParser().parseListOfString(readStrings);
        List<String> validatedStrings = new DataValidator().validateData(parsedStrings);
        List<Integer> integerList = new DataParser().parseToListOfInt(validatedStrings);
        int amountOfCustomers = integerList.indexOf(1);
        int fromRange = integerList.indexOf(2);
        int toRange = integerList.indexOf(3);
>>>>>>> master
        List<Customer> list = new ArrayList<>(amountOfCustomers);
        for(int i = 0; i < amountOfCustomers; i++)
        {
            int time = ThreadLocalRandom.current().nextInt(fromRange,toRange + 1);
            list.add(new Customer(i + 1,time));
        }
        return list;
    }
}

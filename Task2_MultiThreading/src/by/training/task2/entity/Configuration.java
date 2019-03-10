package by.training.task2.entity;

<<<<<<< HEAD
import by.training.task2.exception.IncorrectDataException;
import by.training.task2.parser.DataParser;
import by.training.task2.reader.DataReader;
import by.training.task2.validator.DataValidator;

import java.io.File;
import java.io.IOException;
import java.util.List;
=======
>>>>>>> master
import java.util.Objects;

public class Configuration {

<<<<<<< HEAD
    private static final String FILE_PATH = "data\\config.txt";
=======
>>>>>>> master
    private int amountOfCashiers;
    private int amountOfCustomers;
    private int fromRange;
    private int toRange;

<<<<<<< HEAD
    public Configuration() throws IOException, IncorrectDataException {

        File file = new File(FILE_PATH);
        List<String> readStrings = new DataReader().readData(file);
        List<String> parsedStrings = new DataParser().parseListOfString(readStrings);
        List<String> validatedStrings = new DataValidator().validateData(parsedStrings);
        List<Integer> integerList = new DataParser().parseToListOfInt(validatedStrings);
        this.amountOfCashiers = integerList.get(0);
        this.amountOfCustomers = integerList.get(1);
        this.fromRange = integerList.get(2);
        this.toRange = integerList.get(3);
=======
    public Configuration(int amountOfCashiers, int amountOfCustomers, int fromRange, int toRange) {
        this.amountOfCashiers = amountOfCashiers;
        this.amountOfCustomers = amountOfCustomers;
        this.fromRange = fromRange;
        this.toRange = toRange;
>>>>>>> master
    }

    public int getAmountOfCashiers() {
        return amountOfCashiers;
    }

    public void setAmountOfCashiers(int amountOfCashiers) {
        this.amountOfCashiers = amountOfCashiers;
    }

    public int getAmountOfCustomers() {
        return amountOfCustomers;
    }

    public void setAmountOfCustomers(int amountOfCustomers) {
        this.amountOfCustomers = amountOfCustomers;
    }

    public int getFromRange() {
        return fromRange;
    }

    public void setFromRange(int fromRange) {
        this.fromRange = fromRange;
    }

    public int getToRange() {
        return toRange;
    }

    public void setToRange(int toRange) {
        this.toRange = toRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return getAmountOfCashiers() == that.getAmountOfCashiers() &&
                getAmountOfCustomers() == that.getAmountOfCustomers() &&
                getFromRange() == that.getFromRange() &&
                getToRange() == that.getToRange();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmountOfCashiers(), getAmountOfCustomers(), getFromRange(), getToRange());
    }

    @Override
    public String toString() {
        return "Configuration1{" +
                "amountOfCashiers=" + amountOfCashiers +
                ", amountOfCustomers=" + amountOfCustomers +
                ", fromRange=" + fromRange +
                ", toRange=" + toRange +
                '}';
    }
}

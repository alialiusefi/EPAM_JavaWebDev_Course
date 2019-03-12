package by.training.task2.entity;

import by.training.task2.exception.IncorrectDataException;
import by.training.task2.parser.DataParser;
import by.training.task2.reader.DataReader;
import by.training.task2.validator.DataValidator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class Configuration {

    private static final String FILE_PATH = "data\\config.txt";
    private static final int LIST_IDX_AMOUNTOFCASHIERS = 0;
    private static final int LIST_IDX_AMOUNTOFCUSTOMERS = 1;
    private static final int LIST_IDX_FROMRANGE = 2;
    private static final int LIST_IDX_TORANGE = 3;
    private int amountOfCashiers;
    private int amountOfCustomers;
    private int fromRange;
    private int toRange;

    public Configuration() throws IOException, IncorrectDataException {

        File file = new File(FILE_PATH);
        List<String> readStrings = new DataReader().readData(file);
        List<String> parsedStrings =
                new DataParser().parseListOfString(readStrings);
        List<String> validatedStrings =
                new DataValidator().validateData(parsedStrings);
        List<Integer> integerList =
                new DataParser().parseToListOfInt(validatedStrings);
        this.amountOfCashiers = integerList.get(LIST_IDX_AMOUNTOFCASHIERS);
        this.amountOfCustomers = integerList.get(LIST_IDX_AMOUNTOFCUSTOMERS);
        this.fromRange = integerList.get(LIST_IDX_FROMRANGE);
        this.toRange = integerList.get(LIST_IDX_TORANGE);
    }

    public int getAmountOfCashiers() {
        return amountOfCashiers;
    }

    public void setAmountOfCashiers(final int amountOfCashiers) {
        this.amountOfCashiers = amountOfCashiers;
    }

    public int getAmountOfCustomers() {
        return amountOfCustomers;
    }

    public void setAmountOfCustomers(final int amountOfCustomers) {
        this.amountOfCustomers = amountOfCustomers;
    }

    public int getFromRange() {
        return fromRange;
    }

    public void setFromRange(final int fromRange) {
        this.fromRange = fromRange;
    }

    public int getToRange() {
        return toRange;
    }

    public void setToRange(final int toRange) {
        this.toRange = toRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return getAmountOfCashiers() == that.getAmountOfCashiers()
                && getAmountOfCustomers() == that.getAmountOfCustomers()
                && getFromRange() == that.getFromRange()
                && getToRange() == that.getToRange();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmountOfCashiers(),
                getAmountOfCustomers(), getFromRange(), getToRange());
    }

    @Override
    public String toString() {
        return "Configuration1{"
                + "amountOfCashiers=" + amountOfCashiers +
                ", amountOfCustomers=" + amountOfCustomers +
                ", fromRange=" + fromRange +
                ", toRange=" + toRange +
                '}';
    }
}

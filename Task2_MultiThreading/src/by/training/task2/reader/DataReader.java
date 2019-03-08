package by.training.task2.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    public List<String> readData(final File inputFile) throws IOException {
        List<String> stringList = new ArrayList<>();
        try (BufferedReader bufferedReader = new
                BufferedReader(new FileReader(inputFile))) {
            String readString;
            while ((readString = bufferedReader.readLine()) != null) {
                stringList.add(readString);
            }
        }
        return stringList;
    }

}

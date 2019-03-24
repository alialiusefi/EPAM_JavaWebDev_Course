package by.training.task3.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    public String readData(final File inputFile) throws IOException {
        StringBuffer buffer = new StringBuffer();
        try (BufferedReader bufferedReader = new
                BufferedReader(new FileReader(inputFile))) {
            String readString;
            while ((readString = bufferedReader.readLine()) != null) {
                buffer.append(readString);
            }
        }
        return buffer.toString();
    }

}

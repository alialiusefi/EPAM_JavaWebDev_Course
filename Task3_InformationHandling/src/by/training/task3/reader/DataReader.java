package by.training.task3.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads text from file into one string.
 */
public final class DataReader {

    public static final Logger LOGGER = LogManager.getLogger(DataReader.class);

    public String readData(final File inputFile) throws IOException {
        StringBuffer buffer = new StringBuffer();
        try (BufferedReader bufferedReader = new
                BufferedReader(new FileReader(inputFile))) {
            String readString;
            while ((readString = bufferedReader.readLine()) != null) {
                buffer.append(readString + "\n");
            }
        }
        return buffer.toString();
    }

}

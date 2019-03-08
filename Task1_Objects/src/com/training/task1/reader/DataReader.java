package com.training.task1.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class DataReader {

    public static Logger logger = LogManager.getLogger(DataReader.class);

    /**
     * @param inputFile file location to read data from
     * @return returns a list of strings read
     * @throws IOException thrown if file not found or incorrect directory
     */

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

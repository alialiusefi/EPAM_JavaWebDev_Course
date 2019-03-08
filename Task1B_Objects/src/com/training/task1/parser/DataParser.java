package com.training.task1.parser;

import com.training.task1.exception.InvalidDataException;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses data into list of pairs.
 */
public final class DataParser {

    public static final Logger LOGGER = LogManager.getLogger(DataParser.class);

    /**
     * @param line contains 4 points that are seperated by spaces
     * @return  returns a list of pairs x and y value
     * @throws InvalidDataException throws when amount of points are incorrect
     */
    public List<Pair<String, String>> parsePoints(final String line)
            throws InvalidDataException {
        List<Pair<String, String>> result = new ArrayList<>();
        String[] tokens = line.trim().split(" ");
        for (String token : tokens) {
            String[] points = token.split(",");
            if (points.length == 2) {
                result.add(new Pair<>(points[0], points[1]));
            } else {
                throw new InvalidDataException("Incorrect given coordinates!");
            }
        }
        return result;
    }
}

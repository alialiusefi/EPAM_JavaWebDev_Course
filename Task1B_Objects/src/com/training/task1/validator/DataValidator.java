package com.training.task1.validator;

import com.training.task1.exception.InvalidDataException;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Validates list of pair of points.
 */
public final class DataValidator {
    public static final String POINT_REGEX = "^-?\\d+(\\.\\d+)?$";
    public static final Logger LOGGER = LogManager.getLogger(DataValidator.class);
    /**
     * @param pointList list of pair of points to validate
     * @return returns the same list of the data is correct else throws exception
     * @throws InvalidDataException exception is thrown when data is incorrect.
     */
    public List<Pair<String, String>> validatePoints
    (List<Pair<String, String>> pointList) throws InvalidDataException {
        if (pointList.size() == 4) {
            for (Pair<String, String> pair : pointList) {
                if (!(pair.getKey().matches(POINT_REGEX)
                        && pair.getValue().matches(POINT_REGEX))) {
                    throw new InvalidDataException("Incorrect Point Structure or Data!");
                }
            }
        } else {
            throw new InvalidDataException("Incorrect amount of points!");
        }
        return pointList;
    }


}

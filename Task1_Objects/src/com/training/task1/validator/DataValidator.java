package com.training.task1.validator;

import com.training.task1.exception.InvalidDataInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DataValidator {

    public static final Logger logger = LogManager.getLogger(DataValidator.class);
    private static final String STRUCTURE_REGEX = "\\d+(\\.\\d+)?,\\d+(\\.\\d+)?";
    private static final int AMOUNT_OF_POINTS = 4;

    public String[][] validatePoints(final String line) throws InvalidDataInputException {
        String[][] validatedData = new String[AMOUNT_OF_POINTS][2];
        String[] tokens = line.trim().split(" ");
        int count = 0;
        if (tokens.length == AMOUNT_OF_POINTS) {
            for (String token : tokens) {
                if (token.matches(STRUCTURE_REGEX)) {
                    String[] points = token.split(",");
                    validatedData[count++] = points;
                } else {
                    throw new InvalidDataInputException(token
                            + " has Incorrect structure or Invalid coordinates!");
                }
            }
        } else {
            throw new InvalidDataInputException("Incorrect amount of points!");
        }
        return validatedData;
    }
}

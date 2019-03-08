package com.training.task1.validator;

import com.training.task1.exception.InvalidDataException;
import javafx.util.Pair;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class DataValidatorTest {

    private DataValidator dataValidator = new DataValidator();
    private List<List<Pair<String, String>>> listOfListPointsPairs;
    private List<Pair<String, String>> listOfCorrectPoints;

    @DataProvider
    public Object[][] testValidatePoints() {
        listOfListPointsPairs = new ArrayList<>();
        List<Pair<String, String>> listofPoints1 = new ArrayList<>();
        listofPoints1.add(new Pair<>("2.0", "ff"));
        listofPoints1.add(new Pair<>("2.0", "ff"));
        listofPoints1.add(new Pair<>("20", "a"));
        listofPoints1.add(new Pair<>("2.0", "ff"));
        List<Pair<String, String>> listofPoints2 = new ArrayList<>();
        listofPoints2.add(new Pair<>("2.0", "2.0"));
        listofPoints2.add(new Pair<>("2.0", "15.0"));
        listofPoints2.add(new Pair<>("20", "7.0"));
        listofPoints2.add(new Pair<>("2.0", "5.0"));
        listofPoints2.add(new Pair<>("2.0", "-4.0"));
        listOfListPointsPairs.add(listofPoints1);
        listOfListPointsPairs.add(listofPoints2);
        return new Object[][]{
                {listOfListPointsPairs.get(0), null},
                {listOfListPointsPairs.get(1), null}
        };
    }

    @BeforeMethod
    public void setUp() {
        listOfCorrectPoints = new ArrayList<>();
        listOfCorrectPoints.add(new Pair<>("1.0", "2.0"));
        listOfCorrectPoints.add(new Pair<>("1", "2.0"));
        listOfCorrectPoints.add(new Pair<>("1.0", "-44444"));
        listOfCorrectPoints.add(new Pair<>("22222", "2.0"));
    }

    //TODO:Test for correct case

    @Test(dataProvider = "testValidatePoints", expectedExceptions = InvalidDataException.class)
    public void testValidatePoints(List<Pair<String, String>> pairList,
                                   List<Pair<String, String>> expectedValidated) throws InvalidDataException {
        List<Pair<String, String>> actual = dataValidator.validatePoints(pairList);
        assertEquals(actual, null);
    }

    @Test
    public void testValidatePoints2() {
        List<Pair<String, String>> actual = null;
        try {
            actual = dataValidator.validatePoints(listOfCorrectPoints);
            assertEquals(actual, listOfCorrectPoints);
        } catch (InvalidDataException e) {
            DataValidator.LOGGER.warn(e.getMessage(), e);
        }
    }

}
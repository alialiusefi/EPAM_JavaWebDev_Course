package com.training.task1.validator;

import com.training.task1.exception.InvalidDataInputException;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class DataValidatorTest {

    private DataValidator dataValidator = new DataValidator();
    private String[][] expectedPoints;
    private String actualLine;

    @BeforeClass
    public void setUp() {
        actualLine = "1.0,2.0 -81.0,23.0 31,22 -11,2.0";
        expectedPoints = new String[][]{
                {"1.0", "2.0"},
                {"-81.0", "23.0"},
                {"31", "22"},
                {"-11", "2.0"}
        };
    }

    @DataProvider
    public Object[][] testValidateDataExceptionsTestData() {
        return new Object[][]
                {
                        {"1.0,2.0 -81.0,23.0 31,22 -112.0", expectedPoints},
                        {"", expectedPoints},
                        {"1,2 2,1 9,9,9", expectedPoints}
                };
    }

    @Test
    public void testValidateData() {
        try {
            String[][] actualPoints = dataValidator.validatePoints(actualLine);
            assertEquals(actualPoints, expectedPoints);
        } catch (InvalidDataInputException e) {
            DataValidator.logger.warn(e.getMessage(), e);
        }
    }

    @Test(dataProvider = "testValidateDataExceptionsTestData", expectedExceptions = InvalidDataInputException.class)
    public void testValidateDataExceptions(String line,String[][] expected) throws InvalidDataInputException {
        String[][] actualPoints = dataValidator.validatePoints(line);
    }
}
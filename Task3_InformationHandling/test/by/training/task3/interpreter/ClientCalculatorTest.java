package by.training.task3.interpreter;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ClientCalculatorTest {

    @Test
    public void testCalculateExpression() {
        ClientCalculator clientCalculator = new ClientCalculator();
        String expression = "30>>3";
        int actual = clientCalculator.calculateExpression(expression);
        assertEquals(actual,3);
    }
}
package by.training.task3.interpreter;

import by.training.task3.interpreter.base.AbstractMathExpression;

import java.util.ArrayList;

public class Calculator {

    private ArrayList<AbstractMathExpression> expressions;

    public Calculator(String expression)
    {
        expressions = new ArrayList<>();
        parse(expression);
    }

    private void parse(String expression)
    {
    // code here
    }
}

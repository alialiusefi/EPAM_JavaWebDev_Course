package by.training.task3.interpreter;


import by.training.task3.interpreter.base.AbstractExpression;

public class NonTerminalExpressionNumber extends AbstractExpression {

    private int number;

    public NonTerminalExpressionNumber(int number) {
        this.number = number;
    }

    @Override
    public void interpret(Context a) {
        a.pushValue(this.number);
    }
}

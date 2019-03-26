package by.training.task3.interpreter;


import by.training.task3.interpreter.base.AbstractExpression;

public class TerminalExpressionNOT extends AbstractExpression {

    @Override
    public void interpret(Context context) {
        int b = context.popValue();
        context.pushValue(~b);
    }
}
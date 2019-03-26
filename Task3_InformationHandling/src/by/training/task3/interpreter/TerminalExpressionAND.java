package by.training.task3.interpreter;

import by.training.task3.interpreter.base.AbstractExpression;

public class TerminalExpressionAND extends AbstractExpression {

    @Override
    public void interpret(Context context) {
        int b = context.popValue();
        int a = context.popValue();
        context.pushValue(a & b);
    }
}
package by.training.task3.entity;

import by.training.task3.parser.ExpressionParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.TextComposite;

/**
 * Expression that contains bit and unary operators
 */
public final class Expression extends TextComposite {

    public Expression(final String expression) {
        super(ExpressionParser.getInstance().parse(expression));
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (Component i : this.textComponents) {
            buffer.append(i.getComponent(0));
        }
        return buffer.toString();

    }
}

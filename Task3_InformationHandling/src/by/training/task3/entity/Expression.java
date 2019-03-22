package by.training.task3.entity;

import by.training.task3.parser.ExpressionParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.TextComposite;

public class Expression extends TextComposite implements Component {

    public Expression(String expression) {
        super(ExpressionParser.getInstance().parse(expression));
    }

    private Object getValue() {
        return null;
    }
}

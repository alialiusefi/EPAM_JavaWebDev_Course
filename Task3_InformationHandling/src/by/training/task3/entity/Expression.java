package by.training.task3.entity;

import by.training.task3.parser.ExpressionParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.TextComposite;

public class Expression extends TextComposite implements Component {

    public Expression(String expression) {
        super(ExpressionParser.getInstance().parse(expression));
    }

    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        for(Component i : this.textComponents) {
            buffer.append(i.getComponent(0));
        }
        return buffer.toString();

    }
}

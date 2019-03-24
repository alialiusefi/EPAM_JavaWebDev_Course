package by.training.task3.entity;

import by.training.task3.parser.PunctuationParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.TextComposite;

public class Punctuation extends TextComposite implements Component {

    public Punctuation(String punctuation) {
        super(PunctuationParser.getInstance().parse(punctuation));
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

package by.training.task3.entity;

import by.training.task3.parser.PunctuationParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.TextComposite;

/**
 * Puctuation contains a symobol/symbols.
 */
public class Punctuation extends TextComposite {

    public Punctuation(final String punctuation) {
        super(PunctuationParser.getInstance().parse(punctuation));
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

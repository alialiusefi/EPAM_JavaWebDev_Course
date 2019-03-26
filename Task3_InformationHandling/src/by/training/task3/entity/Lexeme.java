package by.training.task3.entity;

import by.training.task3.parser.LexemeParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.TextComposite;

/**
 *  A part of a sentence that can be an expression,word or a punctuation.
 */
public final class Lexeme extends TextComposite {

    public Lexeme(String lexemeString) {
        super(LexemeParser.getInstance().parse(lexemeString));
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (Component i : this.textComponents) {
            buffer.append(i.toString());
        }
        buffer.append(' ');
        return buffer.toString();
    }
}

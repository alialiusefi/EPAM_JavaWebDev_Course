package by.training.task3.entity;

import by.training.task3.parser.LexemeParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.TextComposite;

public class Lexeme extends TextComposite implements Component {

    public Lexeme(String lexemeString) {
        super(LexemeParser.getInstance().parse(lexemeString));
    }

    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        for(Component i : this.textComponents) {
            buffer.append(i.toString());
        }
        buffer.append(' ');
        return buffer.toString();
    }
}

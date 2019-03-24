package by.training.task3.entity;

import by.training.task3.parser.WordParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.TextComposite;

public class Word extends TextComposite implements Component {

    public Word(String word) {
        super(WordParser.getInstance().parse(word));

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

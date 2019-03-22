package by.training.task3.entity;

import by.training.task3.parser.WordParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.TextComposite;

public class Word extends TextComposite implements Component {

    public Word(String word) {
        super(WordParser.getInstance().parse(word));

    }


}

package by.training.task3.entity;

import by.training.task3.parser.PunctuationParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.TextComposite;

public class Punctuation extends TextComposite implements Component {

    public Punctuation(String punctuation) {
        super(PunctuationParser.getInstance().parse(punctuation));
    }


}

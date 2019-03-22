package by.training.task3.entity;

import by.training.task3.parser.PunctuationParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.Composite;

public class Punctuation extends Composite implements Component {

    Punctuation(String punctuation)
    {
        super(PunctuationParser.getInstance().parse(punctuation));
    }

}

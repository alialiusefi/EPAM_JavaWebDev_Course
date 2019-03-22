package by.training.task3.entity;

import by.training.task3.parser.SentenceParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.TextComposite;


public class Sentence extends TextComposite implements Component {


    public Sentence(String sentence) {
        super(SentenceParser.getInstance().parse(sentence));
    }


}

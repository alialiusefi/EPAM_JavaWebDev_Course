package by.training.task3.entity;

import by.training.task3.parser.SentenceParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.Composite;


public class Sentence extends Composite implements Component {


    public Sentence(String sentence) {
        super(SentenceParser.getInstance().parse(sentence));
    }



}

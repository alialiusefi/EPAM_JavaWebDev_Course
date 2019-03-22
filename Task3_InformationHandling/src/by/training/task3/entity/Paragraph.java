package by.training.task3.entity;

import by.training.task3.parser.ParagraphParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.TextComposite;

public class Paragraph extends TextComposite implements Component {

    public Paragraph(String str) {
        super(ParagraphParser.getInstance().parse(str));
    }


}

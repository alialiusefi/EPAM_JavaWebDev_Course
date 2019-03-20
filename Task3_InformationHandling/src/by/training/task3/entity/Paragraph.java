package by.training.task3.entity;

import by.training.task3.parser.ParagraphParser;

import java.util.List;

public class Paragraph {

    private List<Sentence> sentenceList;

    Paragraph(String str)
    {
        sentenceList = ParagraphParser.getInstance().parse(str);
    }

}

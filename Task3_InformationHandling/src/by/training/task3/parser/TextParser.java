package by.training.task3.parser;

import by.training.task3.entity.Paragraph;
import by.training.task3.pattern.Component;

import java.util.ArrayList;
import java.util.List;

public class TextParser {

    private static TextParser instance = null;

    private static final String DELIMITERS = "\n\t";
    private TextParser() {
    }

    private ParagraphParser getNextParser()
    {
        return ParagraphParser.getInstance();
    }

    public static TextParser getInstance() {
        if (instance == null) {
            instance = new TextParser();
        }
        return instance;
    }

    public List<Component> parse(String text) {
        ArrayList<Component> paragraphArrayList = new ArrayList<>();
        String[] paragraphs = text.split(DELIMITERS);
        for(String i : paragraphs)
        {
            paragraphArrayList.add(new Paragraph(i));
        }
        return paragraphArrayList;
    }

}
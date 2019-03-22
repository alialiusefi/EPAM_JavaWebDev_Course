package by.training.task3.parser;

import by.training.task3.entity.Lexeme;
import by.training.task3.pattern.Component;

import java.util.ArrayList;
import java.util.List;

public class SentenceParser {

    private static SentenceParser instance = null;
    private static final String DELIMITERS = " ";

    private SentenceParser() {
    }

    public static SentenceParser getInstance() {
        if (instance == null) {
            instance = new SentenceParser();
        }
        return instance;
    }

    public List<Component> parse(String sentence) {
        ArrayList<Component> lexemeArrayList = new ArrayList<>();
        String[] lexemeArray = sentence.split(DELIMITERS);
        for (String i : lexemeArray) {
            lexemeArrayList.add(new Lexeme(i));
        }
        return lexemeArrayList;

    }


}

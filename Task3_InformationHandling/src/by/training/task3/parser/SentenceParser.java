package by.training.task3.parser;


import by.training.task3.entity.Lexeme;
import by.training.task3.entity.Paragraph;
import by.training.task3.entity.Sentence;

import java.util.ArrayList;
import java.util.List;

public class SentenceParser {

    private static SentenceParser instance = null;

    private SentenceParser() {
    }

    public static SentenceParser getInstance() {
        if (instance == null) {
            instance = new SentenceParser();
        }
        return instance;
    }

    public List<Lexeme> parse(Sentence sentence) {
        ArrayList<Lexeme> lexemeArrayList = new ArrayList<>();
        //Implementation Here
        return lexemeArrayList;

    }


}

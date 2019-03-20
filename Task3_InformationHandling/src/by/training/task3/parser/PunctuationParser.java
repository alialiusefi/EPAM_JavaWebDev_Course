package by.training.task3.parser;

import by.training.task3.entity.Expression;
import by.training.task3.entity.Lexeme;
import by.training.task3.entity.Punctuation;

import java.util.ArrayList;
import java.util.List;

public class PunctuationParser {

    private static PunctuationParser instance = null;

    private PunctuationParser() {
    }

    public static PunctuationParser getInstance() {
        if (instance == null) {
            instance = new PunctuationParser();
        }
        return instance;
    }

    public List<Character> parse(Lexeme punctuation) {
        ArrayList<Character> charArrayList = new ArrayList<>();
        //Implementation Here
        return charArrayList;

    }


}

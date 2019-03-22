package by.training.task3.parser;

import by.training.task3.entity.Lexeme;
import by.training.task3.entity.Symbol;
import by.training.task3.pattern.Composite;

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

    public List<Composite> parse(Lexeme punctuation) {
        ArrayList<Symbol> charArrayList = new ArrayList<>();
        //Implementation Here
        return charArrayList;

    }


}

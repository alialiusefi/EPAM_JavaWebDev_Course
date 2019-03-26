package by.training.task3.parser;

import by.training.task3.entity.Symbol;
import by.training.task3.pattern.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses punctuation into symbols.
 */
public final class PunctuationParser {

    private static PunctuationParser instance = null;

    private PunctuationParser() {
    }

    public static PunctuationParser getInstance() {
        if (instance == null) {
            instance = new PunctuationParser();
        }
        return instance;
    }

    public List<Component> parse(String punctuation) {
        ArrayList<Component> symbolArrayList = new ArrayList<>();
        char[] symbolArray = punctuation.toCharArray();
        for (char i : symbolArray) {
            symbolArrayList.add(new Symbol(i));
        }
        return symbolArrayList;
    }


}

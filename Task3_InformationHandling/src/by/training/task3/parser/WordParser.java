package by.training.task3.parser;

import by.training.task3.entity.Symbol;
import by.training.task3.pattern.Component;

import java.util.ArrayList;
import java.util.List;

public class WordParser {

    private static WordParser instance = null;

    private WordParser() {
    }

    public static WordParser getInstance() {
        if (instance == null) {
            instance = new WordParser();
        }
        return instance;
    }

    public List<Component> parse(String word) {
        List<Component> symbolArrayList = new ArrayList<>();
        char[] symbolArray = word.toCharArray();
        for (char i : symbolArray) {
                symbolArrayList.add(new Symbol(i));
        }
        return symbolArrayList;

    }


}

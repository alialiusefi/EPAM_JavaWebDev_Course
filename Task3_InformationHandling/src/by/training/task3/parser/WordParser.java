package by.training.task3.parser;

import by.training.task3.entity.Word;

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

    public List<Character> parse(Word word) {
        List<Character> characterList = new ArrayList<>();
        //Implementation Here
        return characterList;

    }


}

package by.training.task3.parser;


import by.training.task3.entity.Lexeme;

import java.util.ArrayList;
import java.util.List;

public class LexemeParser {

    private static LexemeParser instance = null;

    private LexemeParser() {
    }

    public static LexemeParser getInstance() {
        if (instance == null) {
            instance = new LexemeParser();
        }
        return instance;
    }

    public Lexeme parse(Lexeme lexeme) {
        Lexeme res = null;
        //switch(Lexeme)
        // case Word parse();
        // case Expression parse();
        // case Punctuation parse();
        return res;

    }



}

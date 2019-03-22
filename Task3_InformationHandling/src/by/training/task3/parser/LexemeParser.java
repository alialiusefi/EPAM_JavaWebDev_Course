package by.training.task3.parser;

import by.training.task3.entity.Expression;
import by.training.task3.entity.Lexeme;
import by.training.task3.entity.Punctuation;
import by.training.task3.entity.Word;
import by.training.task3.pattern.Component;

import java.util.ArrayList;
import java.util.List;

public class LexemeParser {

    private static LexemeParser instance = null;
    private static final String PUNCTUATION_PATTERN = "\\?|(\\...|\\.)|\\!";
    private static final String EXPRESSION_PATTERN = "\\~|\\&|\\||\\^\\>>|\\>>>|\\<<";

    private LexemeParser() {
    }

    public static LexemeParser getInstance() {
        if (instance == null) {
            instance = new LexemeParser();
        }
        return instance;
    }

    // TODO: split lexeme to word and punctuation else to an expression
    public List<Component> parse(String lexeme) {
        List<Component> componentList = new ArrayList<>();
        if(lexeme.matches(PUNCTUATION_PATTERN))
        {
            componentList.add(new Punctuation(lexeme));
        }
        else if(lexeme.matches(EXPRESSION_PATTERN)){
            componentList.add(new Expression(lexeme));
        } else {
            componentList.add(new Word(lexeme));
        }
        return componentList;

    }




}

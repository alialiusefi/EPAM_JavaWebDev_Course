package by.training.task3.parser;

import by.training.task3.entity.Expression;
import by.training.task3.entity.Punctuation;
import by.training.task3.entity.Word;
import by.training.task3.pattern.Component;

import java.util.ArrayList;
import java.util.List;

public class LexemeParser {

    private static LexemeParser instance = null;
    private static final String PUNCTUATION_PATTERN = "\\?|(\\.\\.\\.|\\.)|!";
    private static final String EXPRESSION_PATTERN = "~|&|(\\|)|\\^|>>|>>>|<<";
    private static final String WORDWITHPUNCT_PATTERN = "(?<=(\\?|\\.\\.\\.|\\.|!))";

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

        String trimmedString = lexeme.trim();
        List<Component> componentList = new ArrayList<>();
        if(!trimmedString.isEmpty()){
        if (trimmedString.matches(EXPRESSION_PATTERN)) {
            componentList.add(new Expression(trimmedString));
        } else {
            if (trimmedString.matches(WORDWITHPUNCT_PATTERN)) {
                String[] splittedString = trimmedString.split(WORDWITHPUNCT_PATTERN);
                componentList.add(new Word(splittedString[0]));
                componentList.add(new Punctuation(splittedString[1]));
            } else {
                componentList.add(new Word(trimmedString));
            }
        }}
        return componentList;

    }


}

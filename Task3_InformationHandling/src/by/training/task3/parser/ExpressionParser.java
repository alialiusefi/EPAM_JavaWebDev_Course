package by.training.task3.parser;

import by.training.task3.entity.Symbol;
import by.training.task3.pattern.Component;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser {

    private static ExpressionParser instance = null;

    private ExpressionParser() {
    }

    public static ExpressionParser getInstance() {
        if (instance == null) {
            instance = new ExpressionParser();
        }
        return instance;
    }

    public List<Component> parse(String expression) {
        List<Component> symbolArrayList = new ArrayList<>();
        char[] symbolArray = expression.toCharArray();
        for (char i : symbolArray) {
            symbolArrayList.add(new Symbol(i));
        }
        return symbolArrayList;

    }

}

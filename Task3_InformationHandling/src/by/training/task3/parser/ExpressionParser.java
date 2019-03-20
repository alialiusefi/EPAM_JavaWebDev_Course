package by.training.task3.parser;

import by.training.task3.entity.Expression;
import by.training.task3.entity.Lexeme;

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

    public List<Character> parse(Expression expression) {
        ArrayList<Character> charArrayList = new ArrayList<>();
        //Implementation Here
        return charArrayList;

    }

}

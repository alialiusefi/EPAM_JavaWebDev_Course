package by.training.task3.interpreter;

import by.training.task3.interpreter.base.AbstractExpression;

import java.util.*;

public class ClientCalculator {

    private ArrayList<AbstractExpression> listExpression;

    public ClientCalculator() {
        listExpression = new ArrayList<>();
    }

    public Integer calculateExpression(String expression) {
        String postfix = parseToPostFix(expression);
        parseToExpressions(postfix);
        Context context = new Context();
        for (AbstractExpression terminal : listExpression) {
            terminal.interpret(context);
        }
        return context.popValue();
    }

    private void parseToExpressions(String infixExpression) {
        String postfixExpression = parseToPostFix(infixExpression);

        for (String lexeme : postfixExpression.split(" ")) {
            switch (lexeme) {
                case "~":
                    listExpression.add(new TerminalExpressionNOT());
                    break;
                case ">>>":
                    listExpression.add(new TerminalExpressionUnsignedRightShift());
                    break;
                case ">>":
                    listExpression.add(new TerminalExpressionRightShift());
                    break;
                case "<<":
                    listExpression.add(new TerminalExpressionLeftShift());
                    break;
                case "&":
                    listExpression.add(new TerminalExpressionAND());
                    break;
                case "^":
                    listExpression.add(new TerminalExpressionXOR());
                    break;
                case "|":
                    listExpression.add(new TerminalExpressionOR());
                    break;
                default: {
                    try (Scanner scan = new Scanner(lexeme)) {
                        if (scan.hasNextInt()) {
                            listExpression.add(
                                    new NonTerminalExpressionNumber(scan.nextInt()));
                        }
                    }
                }
            }
        }
    }

    private String parseToPostFix(String infix) {
        String infixwithspaces;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < infix.length(); i++) {
            if (infix.charAt(i) == '(' || infix.charAt(i) == ')') {
                continue;
            }
            if (i == 0) {
                buffer.append(infix.charAt(0));
                continue;
            }
            if (i == infix.length() - 1) {
                buffer.append(infix.charAt(infix.length() - 1));
                continue;
            }
            if (Character.isDigit(infix.charAt(i))) {
                if (!Character.isDigit(infix.charAt(i + 1)) && !Character.isDigit(infix.charAt(i - 1))) {
                    buffer.append(" ");
                    buffer.append(infix.charAt(i));
                    buffer.append(" ");
                    continue;
                }
            }
            buffer.append(infix.charAt(i));
        }

        String[] splittedStringArray = buffer.toString().split(" ");
        List<String> stringsWithOperators = Arrays.asList(splittedStringArray);
        ArrayDeque<String> stack = new ArrayDeque<>();
        String postfix = "";
        for (int i = 0; i < stringsWithOperators.size(); i++) {
            String token = stringsWithOperators.get(i);
            if (!isOperator(token)) {
                postfix = postfix + token;
            } else {
                if (!token.equals("(") && !token.equals(")")) {
                    if (stack.isEmpty()) {
                        stack.push(token);
                    } else {

                        while (getPriority(stack.peek()) >= getPriority(token)) {
                            postfix = postfix + stack.pop();
                            if (stack.isEmpty())
                                break;
                        }
                        stack.push(token);
                    }

                } else {
                    if (token.equals("("))
                        stack.push(token);
                    else {
                        while (!stack.peek().equals("(")) {
                            postfix = postfix + stack.pop();
                        }
                        stack.pop();
                    }
                }
            }
        }
        return postfix;
    }

    private int getPriority(String operator) {
        if (operator.equals("|"))
            return 1;
        if (operator.equals("^"))
            return 2;
        if (operator.equals("&"))
            return 3;
        if (operator.equals(">>") || operator.equals(">>>") || operator.equals("<<"))
            return 4;
        if (operator == "~")
            return 5;
        if (operator == "(" || operator == ")")
            return 0;
        return -1;
    }

    private boolean isOperator(String operator) {
        return operator.equals("~")
                || operator.equals("&")
                || operator.equals("|")
                || operator.equals("^")
                || operator.equals(">>")
                || operator.equals(">>>")
                || operator.equals("<<")
                || operator.equals("(")
                || operator.equals(")");
    }
}

package by.training.task3.comparator;

import by.training.task3.entity.Lexeme;
import by.training.task3.pattern.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class AmountOfFoundSymbolsInLexemeComparator implements Comparator<Component> {


    private Character character;
    public static final Logger logger = LogManager.getLogger(AmountOfFoundSymbolsInLexemeComparator.class);
    AmountOfFoundSymbolsInLexemeComparator(Character character) {
        this.character = character;
    }

    @Override
    public int compare(Component o1, Component o2) {
        if (o1 instanceof Lexeme && o2 instanceof Lexeme) {
            Lexeme lexeme1 = (Lexeme) o1;
            Lexeme lexeme2 = (Lexeme) o2;
            Integer lexemeAmountOfMatchingSymbols1 = findAmountOfGivenSymbol(lexeme1, this.character);
            Integer lexemeAmountOfMatchingSymbols2 = findAmountOfGivenSymbol(lexeme2, this.character);
            int result = lexemeAmountOfMatchingSymbols1.compareTo(lexemeAmountOfMatchingSymbols2);
            if (result == 0) {
                lexeme1.toString().compareTo(lexeme2.toString());
            } else {
                return result;
            }
        }
        return 0;
    }

    private int findAmountOfGivenSymbol(Lexeme lexeme, Character symbol) {
        String lexemeString = lexeme.toString();
        int amountOfMatchingSymbols = 0;
        for (int i = 0; i < lexemeString.length(); i++) {
            Character character = lexemeString.charAt(i);
            if (character.equals(symbol)) {
                amountOfMatchingSymbols++;
            }
        }
        return amountOfMatchingSymbols;
    }

}

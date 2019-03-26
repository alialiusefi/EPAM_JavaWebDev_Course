package by.training.task3.comparator;

import by.training.task3.entity.Lexeme;
import by.training.task3.pattern.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

/**
 * Comparator that reverse compares 2 lexemes by
 * the amount of found given symbol.
 */
public final class AmountOfFoundSymbolsInLexemeRevComparator
        implements Comparator<Component> {

    /**
     * Character that will be searched for in every lexeme.
     */
    private Character character;
    public static final Logger LOGGER = LogManager.getLogger(
            AmountOfFoundSymbolsInLexemeRevComparator.class);

    public AmountOfFoundSymbolsInLexemeRevComparator(
            final Character character) {
        this.character = character;
    }

    @Override
    public int compare(final Component o1, final Component o2) {
        if (o1 instanceof Lexeme && o2 instanceof Lexeme) {
            Lexeme lexeme1 = (Lexeme) o1;
            Lexeme lexeme2 = (Lexeme) o2;
            Integer lexemeAmountOfMatchingSymbols1 = findAmountOfGivenSymbol(
                    lexeme1, this.character);
            Integer lexemeAmountOfMatchingSymbols2 = findAmountOfGivenSymbol(
                    lexeme2, this.character);
            int result = lexemeAmountOfMatchingSymbols1.compareTo(
                    lexemeAmountOfMatchingSymbols2);
            if (result == 0) {
                return (-1) * lexeme1.toString().compareTo(lexeme2.toString());
            } else {
                return (-1) * result;
            }
        }
        return 0;
    }

    private int findAmountOfGivenSymbol(
            final Lexeme lexeme, final Character symbol) {
        String lexemeString = lexeme.toString();
        int amountOfMatchingSymbols = 0;
        for (int i = 0; i < lexemeString.length(); i++) {
            Character currcharacter = lexemeString.charAt(i);
            if (currcharacter.equals(symbol)) {
                amountOfMatchingSymbols++;
            }
        }
        return amountOfMatchingSymbols;
    }

}

package by.training.task3.comparator;

import by.training.task3.entity.Lexeme;
import by.training.task3.pattern.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

public class AmountOfSymbolsInLexemeComparator implements Comparator<Component> {

    public static final Logger logger = LogManager.getLogger(AmountOfSymbolsInLexemeComparator.class);

    @Override
    public int compare(Component o1, Component o2) {
        if (o1 instanceof Lexeme && o2 instanceof Lexeme)
        {
            Lexeme lexeme1 = (Lexeme) o1;
            Lexeme lexeme2 = (Lexeme) o2;
            List<Component> lexeme1Components= lexeme1.getComponents();
            List<Component> lexeme2Components = lexeme2.getComponents();
            Integer sizeLexeme1 = 0;
            Integer sizeLexeme2 = 0;
            for(Component i : lexeme1Components)
            {
                sizeLexeme1 += i.getComponents().size();
            }
            for(Component i : lexeme2Components)
            {
                sizeLexeme2 += i.getComponents().size();
            }
            return sizeLexeme1.compareTo(sizeLexeme2);
        }
        return 0;
    }
}

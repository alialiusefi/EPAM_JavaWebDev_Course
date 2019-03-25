package by.training.task3.comparator;

import by.training.task3.entity.Sentence;
import by.training.task3.pattern.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class AmountOfLexemeInSentenceComparator implements Comparator<Component> {

    public static final Logger logger = LogManager.getLogger(AmountOfLexemeInSentenceComparator.class);

    @Override
    public int compare(Component o1, Component o2) {
        if (o1 instanceof Sentence && o2 instanceof Sentence)
        {
            Sentence sentence1 = (Sentence) o1;
            Sentence sentence2 = (Sentence) o2;
            Integer sizeSentence1 = sentence1.getComponents().size();
            Integer sizeSentence2 = sentence2.getComponents().size();
            return sizeSentence1.compareTo(sizeSentence2);
        }
        return 0;
    }
}

package by.training.task3.comparator;

import by.training.task3.entity.Paragraph;
import by.training.task3.pattern.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

    public class AmountOfSentencesInParagraphComparator implements Comparator<Component> {

        public static final Logger logger = LogManager.getLogger(AmountOfSentencesInParagraphComparator.class);

        @Override
        public int compare(Component o1, Component o2) {
            if(o1 instanceof Paragraph && o2 instanceof Paragraph)
            {
                Paragraph paragraph1 = (Paragraph) o1;
                Paragraph paragraph2 = (Paragraph) o2;
                Integer sizeParagraph1 = paragraph1.getComponents().size();
                Integer sizeParagraph2 = paragraph2.getComponents().size();
                return sizeParagraph1.compareTo(sizeParagraph2);
            }
            return 0;
        }
    }

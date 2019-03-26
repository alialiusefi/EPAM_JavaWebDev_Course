package by.training.task3.action;

import by.training.task3.entity.Text;
import by.training.task3.pattern.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class that contains methods that sorts lexemes.
 */
public final class LexemeSorter {

    /**
     * @param text             Text Object .
     * @param lexemeComparator Comparator that every lexeme will compared by.
     * @return returns a list of sentences that have their lexemes sorted.
     */
    public List<Component> sortEachSentence(
            final Text text, final Comparator<Component> lexemeComparator) {
        List<Component> paragraphs = text.getComponents();
        List<Component> sentences = new ArrayList<>();
        for (Component i : paragraphs) {
            sentences.addAll(i.getComponents());
        }
        for (Component i : sentences) {
            i.getComponents().sort(lexemeComparator);
        }
        return sentences;
    }

    /**
     * @param text             Text Object
     * @param lexemeComparator Comparator that every lexeme will compared by.
     * @return returns a list of lexems that are sorted using the comparator
     */
    public List<Component> sortAllLexemes(
            final Text text, final Comparator<Component> lexemeComparator) {
        List<Component> paragraphs = text.getComponents();
        List<Component> sentences = new ArrayList<>();
        for (Component i : paragraphs) {
            sentences.addAll(i.getComponents());
        }
        List<Component> lexemes = new ArrayList<>();
        for (Component i : sentences) {
            lexemes.addAll(i.getComponents());
        }
        lexemes.sort(lexemeComparator);
        return lexemes;
    }


}

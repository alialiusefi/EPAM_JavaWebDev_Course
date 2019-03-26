package by.training.task3.action;

import by.training.task3.entity.Text;
import by.training.task3.pattern.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class that contains methods that sorts a
 * paragraphs and returns a list of sorted paragraphs.
 */
public final class ParagraphSorter {

    /**
     * @param text                Text Object
     * @param paragraphComparator Comparator that will be sorted by
     * @return returns list of paragraphs that
     * are sorted using paragraphComparator
     */
    public List<Component> sortParagraphs(
            final Text text, final Comparator<Component> paragraphComparator) {
        List<Component> componentList = new ArrayList<>();
        componentList.addAll(text.getComponents());
        componentList.sort(paragraphComparator);
        return componentList;
    }

}

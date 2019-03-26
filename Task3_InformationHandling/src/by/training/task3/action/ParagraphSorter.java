package by.training.task3.action;

import by.training.task3.entity.Text;
import by.training.task3.pattern.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParagraphSorter {

    public List<Component> sort(Text text, Comparator<Component> paragraphComparator) {
        List<Component> componentList = new ArrayList<>();
        componentList.addAll(text.getComponents());
        componentList.sort(paragraphComparator);
        return componentList;
    }

}

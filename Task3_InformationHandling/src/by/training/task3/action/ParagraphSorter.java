package by.training.task3.action;

import by.training.task3.entity.Paragraph;
import by.training.task3.entity.Text;
import by.training.task3.pattern.Component;

import java.util.Comparator;
import java.util.List;

public class ParagraphSorter {

    public void sort(Text text, Comparator<Component> paragraphComparator)
    {
        List<Component> componentList = text.getComponents();
        componentList.sort(paragraphComparator);
    }

}

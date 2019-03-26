package by.training.task3.action;

import by.training.task3.entity.Text;
import by.training.task3.pattern.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LexemeSorter {

    public List<Component> sort(Text text, Comparator<Component> componentComparator) {
        List<Component> paragraphs = text.getComponents();
        List<Component> sentences = new ArrayList<>();
        for(Component i : paragraphs)
        {
            sentences.addAll(i.getComponents());
        }
        for(Component i : sentences)
        {
            i.getComponents().sort(componentComparator);
        }
        return sentences;
    }


}

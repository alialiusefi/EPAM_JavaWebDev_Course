package by.training.task3.action;

import by.training.task3.entity.Sentence;
import by.training.task3.pattern.Component;

import java.util.Comparator;
import java.util.List;

public class LexemeSorter {

    public void sort(Sentence sentence, Comparator<Component> componentComparator)
    {
        List<Component> componentList = sentence.getComponents();
        componentList.sort(componentComparator);
    }


}

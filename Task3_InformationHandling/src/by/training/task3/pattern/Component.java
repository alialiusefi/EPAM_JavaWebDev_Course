package by.training.task3.pattern;

import java.util.List;

public interface Component {

    Object getComponent(int idx);
    void remove(Component component);
    void addComponent(Component component);
    List<Component> getComponents();

}

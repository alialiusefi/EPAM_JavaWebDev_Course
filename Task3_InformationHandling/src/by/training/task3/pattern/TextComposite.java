package by.training.task3.pattern;


import java.util.List;

public abstract class TextComposite {

    protected List<Component> textComponents;

    protected TextComposite(List<Component> textComponents)
    {
        this.textComponents = textComponents;
    }

    public void addComponent(Component component){
        this.textComponents.add(component);
    }
    public Component getComponent(int idx){
        if(idx >= 0)
        {
            return this.textComponents.get(idx);
        }
        return null;
    }
    public void remove(Component component){
        this.textComponents.remove(component);
    }



}

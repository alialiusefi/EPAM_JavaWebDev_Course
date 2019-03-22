package by.training.task3.pattern;


import java.util.List;

public abstract class Composite {

    protected List<Component> textComponents;

    protected Composite(List<Component> textComponents)
    {
        this.textComponents = textComponents;
    }

    public void addComponent(Component component){
        this.textComponents.add(component);
    }
    public Component getChild(int idx){
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

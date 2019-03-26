package by.training.task3.pattern;


import java.util.List;
import java.util.Objects;

public abstract class TextComposite implements Component{

    protected List<Component> textComponents;

    protected TextComposite(List<Component> textComponents)
    {
        this.textComponents = textComponents;
    }

    @Override
    public void addComponent(Component component){
        this.textComponents.add(component);
    }

    @Override
    public Object getComponent(int idx){
        if(idx >= 0)
        {
            return this.textComponents.get(idx);
        }
        return null;
    }

    public void remove(Component component){
        this.textComponents.remove(component);
    }
    @Override
    public List<Component> getComponents()
    {
        return textComponents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextComposite that = (TextComposite) o;
        return Objects.equals(textComponents, that.textComponents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textComponents);
    }
}

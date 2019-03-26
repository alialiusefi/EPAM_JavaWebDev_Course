package by.training.task3.entity;

import by.training.task3.pattern.Component;
import by.training.task3.pattern.TextComposite;

import java.util.Objects;


public class Symbol extends TextComposite implements Component {

    private Character character;

    public Symbol(char character) {
        super(null);
        this.character = character;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return character.equals(symbol.character);
    }

    @Override
    public int hashCode() {
        return Objects.hash(character);
    }

    @Override
    public Object getComponent(int idx) {
        return this.character;
    }
}

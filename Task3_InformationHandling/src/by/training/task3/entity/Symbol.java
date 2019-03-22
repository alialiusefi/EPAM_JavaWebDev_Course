package by.training.task3.entity;

import by.training.task3.pattern.Component;


public class Symbol implements Component {

    private Character character;

    public Symbol(char character) {
        this.character = character;
    }

    @Override
    public String toString() {
        String str;
        str = this.character + "";
        return str;
    }

    @Override
    public Component getComponent(int idx) {
        return null;
    }
}

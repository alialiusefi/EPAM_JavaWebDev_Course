package by.training.task3.entity;

import by.training.task3.pattern.Component;


public class Symbol implements Component {

    private Character character;

    public Symbol(char character) {
        this.character = character;
    }

    @Override
    public Object getComponent(int idx) {
        return this.character;
    }
}

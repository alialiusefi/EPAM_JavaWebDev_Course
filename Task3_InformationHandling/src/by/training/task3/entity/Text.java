package by.training.task3.entity;

//                                   / |->Word-->char
//Text->Paragraph->Sentence->Lexeme-| |->Expression-->char
//                                   \ |->Punctuation-->char

/*
    TODO: Interpreter
    TODO: Individual Task
    TODO: Logs and Exceptions
    TODO: Tests
    TODO: Javadoc
*/

import by.training.task3.parser.TextParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.TextComposite;

public class Text extends TextComposite {

    public Text(String text) {
        super(TextParser.getInstance().parse(text));
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (Component i : this.textComponents) {
            buffer.append(i.toString());
        }
        return buffer.toString();

    }

}

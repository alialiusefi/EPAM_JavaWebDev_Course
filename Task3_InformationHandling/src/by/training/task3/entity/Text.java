package by.training.task3.entity;

//
//                                   / |->Word-->char
//Text->Paragraph->Sentence->Lexeme-| |->Expression-->char
//                                   \ |->Punctuation-->char

import by.training.task3.parser.TextParser;

import java.util.List;

public class Text {

    public List<Paragraph> paragraphList;

    Text(String text)
    {
        paragraphList = TextParser.getInstance().parse(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

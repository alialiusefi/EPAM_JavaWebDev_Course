package by.training.task3.entity;


//                                   / |->Word-->char
//Text->Paragraph->Sentence->Lexeme-| |->Expression-->char
//                                   \ |->Punctuation-->char

import by.training.task3.parser.TextParser;
import by.training.task3.pattern.Composite;

public class Text extends Composite {

    public Text(String text)
    {
        // parses into paragraph components
        super(TextParser.getInstance().parse(text));
    }

}

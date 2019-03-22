package by.training.task3.entity;

//                                   / |->Word-->char
//Text->Paragraph->Sentence->Lexeme-| |->Expression-->char
//                                   \ |->Punctuation-->char

/*
    TODO: Fix Output toString(), especially the Symbol class
    TODO: Fix regular expressions
    TODO: Interpreter
    TODO:
*/

import by.training.task3.parser.TextParser;
import by.training.task3.pattern.TextComposite;

public class Text extends TextComposite {

    public Text(String text) {
        // parses into paragraph components
        super(TextParser.getInstance().parse(text));
    }

}

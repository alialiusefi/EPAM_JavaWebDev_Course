package by.training.task3.entity;

//                                   / |->Word-->char
//Text->Paragraph->Sentence->Lexeme-| |->Expression-->char
//                                   \ |->Punctuation-->char

/*
    TODO: Tests
    TODO: Javadoc
*/

import by.training.task3.parser.TextParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.TextComposite;

/**
 * Text contains paragraphs.
 */
public final class Text extends TextComposite  {

    public Text(final String text) {
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

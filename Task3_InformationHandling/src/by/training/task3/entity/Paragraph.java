package by.training.task3.entity;

import by.training.task3.parser.ParagraphParser;
import by.training.task3.pattern.Component;
import by.training.task3.pattern.TextComposite;

public class Paragraph extends TextComposite implements Component {

    public Paragraph(String str) {
        super(ParagraphParser.getInstance().parse(str));
    }

    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append('\t');
        for(Component i : this.textComponents) {
            buffer.append(i.toString());
        }
        buffer.append('\n');
        return buffer.toString();

    }
}

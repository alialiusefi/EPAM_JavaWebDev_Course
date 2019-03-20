package by.training.task3.parser;

import by.training.task3.entity.Paragraph;
import by.training.task3.entity.Sentence;
import by.training.task3.entity.Text;

import java.util.ArrayList;
import java.util.List;

public class ParagraphParser {

    private static ParagraphParser instance = null;
    private static final String delimiters = "\\.\\?\\!\\.[1-3]";
    private ParagraphParser() {
    }

    public static ParagraphParser getInstance() {
        if (instance == null) {
            instance = new ParagraphParser();
        }
        return instance;
    }

    public List<Sentence> parse(String str) {
        ArrayList<Sentence> sentenceArrayList = new ArrayList<>();

        //Implementation Here
        return sentenceArrayList;

    }

}

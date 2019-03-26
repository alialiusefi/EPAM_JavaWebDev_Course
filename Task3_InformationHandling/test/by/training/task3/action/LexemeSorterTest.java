package by.training.task3.action;

import by.training.task3.comparator.AmountOfFoundSymbolsInLexemeRevComparator;
import by.training.task3.comparator.AmountOfSymbolsInLexemeComparator;
import by.training.task3.entity.Lexeme;
import by.training.task3.entity.Sentence;
import by.training.task3.entity.Text;
import by.training.task3.pattern.Component;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class LexemeSorterTest {


    List<Component> expectedSentences;
    List<Component> expectedLexemes;
    String actualText;

    @BeforeClass
    public void setUp() {
        actualText = "\t This is a Sentence!\n" +  "\t Text 1 GGGGGG!\n" + "\t aaaaaaaaaaaaaaaaa a aa aaaa !\n";

        expectedSentences = new ArrayList<>();
        expectedSentences.add(new Sentence("\t a is This Sentence! \n"));
        expectedSentences.add(new Sentence("\t 1 Text GGGGGG! \n"));
        expectedSentences.add(new Sentence("\t a ! aa aaaa aaaaaaaaaaaaaaaaa \n"));
        expectedLexemes = new ArrayList<>();
        expectedLexemes.add(new Lexeme("aaaaaaaaaaaaaaaaa "));
        expectedLexemes.add(new Lexeme(" aaaa "));
        expectedLexemes.add(new Lexeme(" aa "));
        expectedLexemes.add(new Lexeme(" a "));
        expectedLexemes.add(new Lexeme(" a "));
        expectedLexemes.add(new Lexeme(" is "));
        expectedLexemes.add(new Lexeme(" This "));
        expectedLexemes.add(new Lexeme(" Text "));
        expectedLexemes.add(new Lexeme(" Sentence! "));
        expectedLexemes.add(new Lexeme(" GGGGGG! "));
        expectedLexemes.add(new Lexeme(" 1 "));
        expectedLexemes.add(new Lexeme(" ! "));
    }

    @Test
    public void testSortEachSentence() {
        LexemeSorter lexemeSorter = new LexemeSorter();
        Text text = new Text(actualText);
        List<Component> actual = lexemeSorter.sortEachSentence(text, new AmountOfSymbolsInLexemeComparator());
        assertEquals(actual, expectedSentences);
    }

    @Test
    public void testSortAllLexemes() {
        LexemeSorter lexemeSorter = new LexemeSorter();
        Text text = new Text(actualText);
        List<Component> actual = lexemeSorter.sortAllLexemes(text,new AmountOfFoundSymbolsInLexemeRevComparator('a'));
        assertEquals(actual,expectedLexemes);
    }
}
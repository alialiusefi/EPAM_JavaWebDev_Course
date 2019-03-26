package by.training.task3.action;

import by.training.task3.comparator.AmountOfSymbolsInLexemeComparator;
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
    String actualText;

    @BeforeClass
    public void setUp() {
        actualText = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, " +
                "remaining 30>>>3 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1)" +
                " with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop " +
                "publishing software like Aldus PageMaker including versions of Lorem Ipsum.  \n" +
                "\tIt is a long established fact that a reader will be distracted by the readable content of a page " +
                "when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it" +
                " has a more-or-less normal distribution of letters, as opposed to using (Content here), content here'," +
                " making it look like readable English.  \n" +
                "\tIt is a (111^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking" +
                " at its layout.  \n" +
                "\t1\n";
        expectedSentences = new ArrayList<>();
        expectedSentences.add(new Sentence("\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, " +
                "remaining 30>>>3 essentially ~6&9|(3&4) unchanged."));
        expectedSentences.add(new Sentence("It was popularised in the 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1)" +
                " with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop " +
                "publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n"));
        expectedSentences.add(new Sentence("\tIt is a long established fact that a reader will be distracted by the readable content of a page " +
                "when looking at its layout."));
        expectedSentences.add(new Sentence("The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it" +
                " has a more-or-less normal distribution of letters, as opposed to using (Content here), content here'," +
                " making it look like readable English.\n"));
        expectedSentences.add(new Sentence("\tIt is a (111^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking" +
                " at its layout.\n"));
        expectedSentences.add(new Sentence("\t1\n"));

    }

    @Test
    public void testSort() {
        LexemeSorter lexemeSorter = new LexemeSorter();
        Text text = new Text(actualText);
        List<Component> actual = lexemeSorter.sort(text, new AmountOfSymbolsInLexemeComparator());
        assertEquals(actual, expectedSentences);
    }
}
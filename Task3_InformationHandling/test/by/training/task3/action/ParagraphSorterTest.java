package by.training.task3.action;

import by.training.task3.comparator.AmountOfSentencesInParagraphComparator;
import by.training.task3.entity.Paragraph;
import by.training.task3.entity.Text;
import by.training.task3.pattern.Component;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ParagraphSorterTest {

    List<Component> actualList;
    List<Component> expectedList;
    String actualText;

    @BeforeClass
    public void setUp()
    {
        actualText = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 30>>>3 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.  \n" +
                "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.  \n" +
                "\tIt is a (111^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.  \n" +
                "\t1\n";
        /*actualList = new ArrayList<>();
        actualList.add(new Paragraph("\tBye.\n"));
        actualList.add(new Paragraph("\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 30>>>3 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n"));
        actualList.add(new Paragraph("\tIt is a (111^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n" ));
        actualList.add(new Paragraph("\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n" ));
        */
        expectedList = new ArrayList<>();
        expectedList.add(new Paragraph("\t1\n"));
        expectedList.add(new Paragraph("\tIt is a (111^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.  \n" ));
        expectedList.add(new Paragraph("\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic typesetting, remaining 30>>>3 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.  \n"));
        expectedList.add(new Paragraph("\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.  \n" ));
    }
    @Test
    public void testSort() {
        Text text = new Text(actualText);
        ParagraphSorter paragraphSorter = new ParagraphSorter();
        List<Component> paragraphSorted = paragraphSorter.sort(text,new AmountOfSentencesInParagraphComparator());
        assertEquals(paragraphSorted,expectedList);
    }
}
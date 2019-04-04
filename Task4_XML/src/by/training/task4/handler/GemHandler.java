package by.training.task4.handler;

import by.training.task4.builder.attribute.DiamondGemAttributes;
import by.training.task4.builder.attribute.EmeraldGemAttributes;
import by.training.task4.builder.attribute.PearlGemAttributes;
import by.training.task4.entity.AbstractGem;
import by.training.task4.entity.Pearl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class GemHandler extends DefaultHandler {

    public static final Logger logger =
            LogManager.getLogger(GemHandler.class);
    private Set<AbstractGem> allGems;
    private AbstractGem current = null;
    private EnumSet<DiamondGemAttributes> diamondGemAttr;
    private EnumSet<EmeraldGemAttributes> emeraldGemAttr;
    private EnumSet<PearlGemAttributes> pearlGemAttr;

    public GemHandler()
    {
        allGems = new HashSet<>();
        diamondGemAttr = EnumSet.range(DiamondGemAttributes.NAME,
                DiamondGemAttributes.AMOUNTOFCUTS);
        emeraldGemAttr = EnumSet.range(EmeraldGemAttributes.NAME,
                EmeraldGemAttributes.AMOUNTOFCUTS);
        pearlGemAttr = EnumSet.range(PearlGemAttributes.NAME,
                PearlGemAttributes.LUSTER);
    }

    public Set<AbstractGem> getAllGems() {
        return allGems;
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attrs)
    {
        /*StringBuilder stringBuilder = new StringBuilder(localName);
        for (int i = 0; i < attrs.getLength(); i++) {
            stringBuilder.append(" ").append(attrs.getLocalName(i))
                    .append("=").append(attrs.getValue(i));
        }
        System.out.print(stringBuilder.toString().trim());*/
    }

    @Override
    public void startDocument()
    {
        logger.debug("Parsing Started!");
    }

    @Override
    public void characters(char[] ch, int start, int length)
    {
        System.out.print(new String(ch,start,length));
    }
    @Override
    public void endDocument()
    {
        logger.debug("Parsing Ended");
    }
}

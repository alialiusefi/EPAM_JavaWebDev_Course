package by.training.task4.handler;

import by.training.task4.builder.attribute.AbstractGemEnum;
import by.training.task4.builder.attribute.DiamondGemEnum;
import by.training.task4.builder.attribute.EmeraldGemEnum;
import by.training.task4.builder.attribute.PearlGemEnum;
import by.training.task4.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class GemHandler extends DefaultHandler {

    public static final Logger logger =
            LogManager.getLogger(GemHandler.class);
    private Set<AbstractGem> allGems;
    private AbstractGem current = null;
    private AbstractGemEnum currentGemEnum = null;
    private DiamondGemEnum currentDiamondEnum = null;
    private EmeraldGemEnum currentEmeraldEnum = null;
    private PearlGemEnum currentPearlEnum = null;
    private EnumSet<AbstractGemEnum> abstractGemAttr;
    private EnumSet<DiamondGemEnum> diamondGemAttr;
    private EnumSet<EmeraldGemEnum> emeraldGemAttr;
    private EnumSet<PearlGemEnum> pearlGemAttr;

    public GemHandler() {
        allGems = new HashSet<>();
        diamondGemAttr = EnumSet.range(DiamondGemEnum.NAME,
                DiamondGemEnum.AMOUNTOFCUTS);
        emeraldGemAttr = EnumSet.range(EmeraldGemEnum.NAME,
                EmeraldGemEnum.AMOUNTOFCUTS);
        pearlGemAttr = EnumSet.range(PearlGemEnum.NAME,
                PearlGemEnum.LUSTER);
        abstractGemAttr = EnumSet.range(AbstractGemEnum.NAME,
                AbstractGemEnum.ID);
    }

    public Set<AbstractGem> getAllGems() {
        return allGems;
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attrs) {

        if ("Diamond".equals(localName) || "Pearl".equals(localName) || "Emerald".equals(localName))
        {
            int id = Integer.parseInt(attrs.getValue("id"));
            Origin origin = Origin.valueOf(attrs.getValue("origin"));
            String name = attrs.getValue("name"));
            Preciousness preciousness = Preciousness.valueOf(
                    attrs.getValue("preciousness"));
            Weight weight = new Weight();
            double value = Double.parseDouble(attrs.getValue("value"));
            weight.setValue(value);
            String unit = attrs.getValue("unit");
            weight.setUnit(unit);
            String[] datestr = attrs.getValue("gemArrival").split("-");
            GregorianCalendar gregorianCalendar = new GregorianCalendar(
                    Integer.parseInt(datestr[2]),
                    Integer.parseInt(datestr[1]) - 1,
                    Integer.parseInt(datestr[0]));
            if("Diamond".equals(localName))
            {
                double transparency = Double.parseDouble(attrs.getValue("transparency"));
                int amountOfCuts = Integer.parseInt(attrs.getValue("amountofcuts"));
                current = new Diamond();
                current.setOrigin(origin);
                current.setId(id);
                current.getContent().add(name);
                current.getContent().add(preciousness);
                current.getContent().add(weight);
                current.getContent().add(gregorianCalendar);
                current.getContent().add(transparency);
                current.getContent().add(amountOfCuts);
            }
            if("Emerald".equals(localName))
            {
                String color = attrs.getValue("color");
                double transparency = Double.parseDouble(attrs.getValue("transparency"));
                int amountOfCuts = Integer.parseInt(attrs.getValue("amountofcuts"));
                current = new Emerald();
                current.setOrigin(origin);
                current.setId(id);
                current.getContent().add(name);
                current.getContent().add(preciousness);
                current.getContent().add(weight);
                current.getContent().add(gregorianCalendar);
                current.getContent().add(transparency);
                current.getContent().add(amountOfCuts);
                current.getContent().add(color);
            }
            if("Pearl".equals(localName))
            {
                current = new Pearl();
                current.setOrigin(origin);
                current.setId(id);
                current.getContent().add(name);
                current.getContent().add(preciousness);
                current.getContent().add(weight);
                current.getContent().add(gregorianCalendar);
                
            }
        }
        else {
            AbstractGemEnum temp = AbstractGemEnum.valueOf(localName.toUpperCase());
            if(abstractGemAttr.contains(temp))
            {
                currentGemEnum = temp;
            }
        }

        // if element is a diamond
        if () {
            Diamond diamond = new ObjectFactory().createDiamond();
            //diamond.getContent().add(gregorianCalendar);
            //diamond.getContent().add(Double.parseDouble(attrs.getValue("transparency")));
            //diamond.getContent().add()));
        }
        if("Pearl".equals(localName))
        {

        }
        if("Emerald".equals(localName))
        {

        }



    }

    @Override
    public void startDocument() {
        logger.debug("Parsing Started!");
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        System.out.print(new String(ch, start, length));
    }

    @Override
    public void endDocument() {
        logger.debug("Parsing Ended");
    }
}

package by.training.task4.handler;

import by.training.task4.builder.tag.GemEnum;
import by.training.task4.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class GemHandler extends DefaultHandler {

    public static final Logger logger =
            LogManager.getLogger(GemHandler.class);
    private Set<AbstractGem> allGems;
    private AbstractGem current = null;
    private GemEnum currentGemEnum = null;
    private EnumSet<GemEnum> abstractGemAttr;

    public GemHandler() {
        allGems = new HashSet<>();
        abstractGemAttr = EnumSet.range(GemEnum.GEMS,
                GemEnum.LUSTER);
    }

    public Set<AbstractGem> getGems() {
        return allGems;
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attrs) {
        if ("Diamond".equals(localName) ||
                "Pearl".equals(localName) ||
                "Emerald".equals(localName)) {
            if ("Diamond".equals(localName)) {
                current = new ObjectFactory().createDiamond();
            }
            if ("Pearl".equals(localName)) {
                current = new ObjectFactory().createPearl();
            }
            if ("Emerald".equals(localName)) {
                current = new ObjectFactory().createEmerald();
            }
            int id = Integer.parseInt(attrs.getValue("id"));
            Origin origin = Origin.valueOf(attrs.getValue("origin"));
            current.setId(id);
            current.setOrigin(origin);
        } else {
            GemEnum temp = GemEnum.valueOf(localName.toUpperCase());
            if (abstractGemAttr.contains(temp)) {
                currentGemEnum = temp;
            }
        }
    }

    @Override
    public void startDocument() {
        logger.debug("Parsing Started!");
    }

    public void endElement(String uri, String localName, String qName) {
        if ("Diamond".equals(localName) ||
                "Pearl".equals(localName) ||
                "Emerald".equals(localName)) {
            allGems.add(current);
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();
        if (currentGemEnum != null) {
            switch (currentGemEnum) {
                case GEMS:
                    break;
                case NAME:
                    current.getContent().add(str);
                    break;
                case PRECIOUSNESS:
                    current.getContent().add(Preciousness.valueOf(str));
                    break;
                case WEIGHT:
                    current.getContent().add(new Weight());
                    break;
                case UNIT:
                    for (Serializable i : current.getContent()) {
                        if (i instanceof Weight) {
                            ((Weight) i).setUnit(str);
                            break;
                        }
                    }
                    break;
                case VALUE:
                    for (Serializable i : current.getContent()) {
                        if (i instanceof Weight) {
                            ((Weight) i).setValue(Double.parseDouble(str));
                        }
                    }
                    break;
                case GEMARRIVAL:
                    String datestr = str;
                    current.getContent().add(datestr);
                    break;
                case TRANSPARENCY:
                    current.getContent().add(Double.parseDouble(str));
                    break;
                case AMOUNTOFCUTS:
                    current.getContent().add(Integer.parseInt(str));
                    break;
                case COLOR:
                    current.getContent().add(str);
                    break;
                case LUSTER:
                    current.getContent().add(Luster.fromValue(str.toUpperCase()));
                    break;
                default:
                    throw new SAXException(
                            currentGemEnum.name());
            }
        }
        currentGemEnum = null;
    }

    @Override
    public void endDocument() {
        logger.debug("Parsing Ended");
    }
}

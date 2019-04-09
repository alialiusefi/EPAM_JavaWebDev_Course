package by.training.task4.builder;

import by.training.task4.builder.tag.GemEnum;
import by.training.task4.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class GemsStaxBuilder {

    public static final Logger LOGGER =
            LogManager.getLogger(GemsStaxBuilder.class);

    private HashSet<AbstractGem> gems = new HashSet<>();
    private XMLInputFactory inputFactory;

    public GemsStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<AbstractGem> getGems() {
        return gems;
    }

    public void buildSetGems(String filename) {
        XMLStreamReader reader = null;
        String name;
        try (FileInputStream inputStream =
                     new FileInputStream(new File(filename))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            //StaxParsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    //checking it its a new gem(diamond,pearl,emerald)
                    if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.DIAMOND ||
                            GemEnum.valueOf(name.toUpperCase()) == GemEnum.EMERALD ||
                            GemEnum.valueOf(name.toUpperCase()) == GemEnum.PEARL) {
                        AbstractGem gem = null;
                        //diamond
                        if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.DIAMOND) {
                            gem = buildDiamond(reader);
                        }
                        if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.EMERALD) {
                            gem = buildEmerald(reader);
                        }
                        if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.PEARL) {
                            gem = buildPearl(reader);
                        }
                        if (gem != null) {
                            gems.add(gem);
                        }
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    private Diamond buildDiamond(XMLStreamReader reader) throws XMLStreamException {
        Diamond diamond = new Diamond();
        //getting attributes
        diamond.setId(Integer.parseInt(reader.getAttributeValue(
                null, GemEnum.ID.getValue())));
        diamond.setOrigin(Origin.valueOf(reader.getAttributeValue(
                null, GemEnum.ORIGIN.getValue())));
        //getting content
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            diamond.getContent().add(getXMLText(reader));
                            break;
                        case PRECIOUSNESS:
                            name = getXMLText(reader);
                            diamond.getContent().add(Preciousness.valueOf(name));
                            break;
                        case WEIGHT:
                            diamond.getContent().add(getXMLWeight(reader));
                            break;
                        case GEMARRIVAL:
                            name = getXMLText(reader);
                            if (name == null) {
                                break;
                            }
                            String[] datestr = name.split("-");
                            GregorianCalendar gregorianCalendar = new GregorianCalendar(
                                    Integer.parseInt(datestr[2]),
                                    Integer.parseInt(datestr[1]) - 1,
                                    Integer.parseInt(datestr[0]));
                            diamond.getContent().add(gregorianCalendar);
                            break;
                        //diamond specs here
                        case TRANSPARENCY:
                            diamond.getContent().add(Double.parseDouble(getXMLText(reader)));
                            break;
                        case AMOUNTOFCUTS:
                            diamond.getContent().add(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.DIAMOND) {
                        return diamond;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown Element in tag diamond");
    }

    private Emerald buildEmerald(XMLStreamReader reader) throws XMLStreamException {
        Emerald emerald = new Emerald();
        //getting attributes
        emerald.setId(Integer.parseInt(reader.getAttributeValue(
                null, GemEnum.ID.getValue())));
        emerald.setOrigin(Origin.valueOf(reader.getAttributeValue(
                null, GemEnum.ORIGIN.getValue())));
        //getting content
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            emerald.getContent().add(getXMLText(reader));
                            break;
                        case PRECIOUSNESS:
                            name = getXMLText(reader);
                            emerald.getContent().add(Preciousness.valueOf(name));
                            break;
                        case WEIGHT:
                            emerald.getContent().add(getXMLWeight(reader));
                            break;
                        case GEMARRIVAL:
                            name = getXMLText(reader);
                            if (name == null) {
                                break;
                            }
                            String[] datestr = name.split("-");
                            GregorianCalendar gregorianCalendar = new GregorianCalendar(
                                    Integer.parseInt(datestr[2]),
                                    Integer.parseInt(datestr[1]) - 1,
                                    Integer.parseInt(datestr[0]));
                            emerald.getContent().add(gregorianCalendar);
                            break;
                        //emerald specs here
                        case COLOR:
                            emerald.getContent().add(getXMLText(reader));
                            break;
                        case TRANSPARENCY:
                            emerald.getContent().add(Double.parseDouble(getXMLText(reader)));
                            break;
                        case AMOUNTOFCUTS:
                            emerald.getContent().add(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.EMERALD) {
                        return emerald;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown Element in tag emerald");
    }

    private Pearl buildPearl(XMLStreamReader reader) throws XMLStreamException {
        Pearl pearl = new Pearl();
        //getting attributes
        pearl.setId(Integer.parseInt(reader.getAttributeValue(
                null, GemEnum.ID.getValue())));
        pearl.setOrigin(Origin.valueOf(reader.getAttributeValue(
                null, GemEnum.ORIGIN.getValue())));
        //getting content
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemEnum.valueOf(name.toUpperCase())) {
                        case NAME:
                            pearl.getContent().add(getXMLText(reader));
                            break;
                        case PRECIOUSNESS:
                            name = getXMLText(reader);
                            pearl.getContent().add(Preciousness.valueOf(name));
                            break;
                        case WEIGHT:
                            pearl.getContent().add(getXMLWeight(reader));
                            break;
                        case GEMARRIVAL:
                            name = getXMLText(reader);
                            if (name == null) {
                                break;
                            }
                            String[] datestr = name.split("-");
                            GregorianCalendar gregorianCalendar = new GregorianCalendar(
                                    Integer.parseInt(datestr[2]),
                                    Integer.parseInt(datestr[1]) - 1,
                                    Integer.parseInt(datestr[0]));
                            pearl.getContent().add(gregorianCalendar);
                            break;
                        //pearl specs here
                        case COLOR:
                            pearl.getContent().add(getXMLText(reader));
                            break;
                        case LUSTER:
                            pearl.getContent().add(Luster.fromValue(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.PEARL) {
                        return pearl;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown Element in tag pearl");
    }

    private Weight getXMLWeight(XMLStreamReader reader)
            throws XMLStreamException {
        Weight weight = new Weight();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemEnum.valueOf(name.toUpperCase())) {
                        case UNIT:
                            weight.setUnit(getXMLText(reader));
                            break;
                        case VALUE:
                            weight.setValue(Double.parseDouble(
                                    getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.WEIGHT) {
                        return weight;
                    }
                    break;
            }

        }
        throw new XMLStreamException("Unknown tag found!");
    }


    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}

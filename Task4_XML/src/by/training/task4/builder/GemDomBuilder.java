package by.training.task4.builder;

import by.training.task4.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class GemDomBuilder {

    public static final Logger LOGGER =
            LogManager.getLogger(GemDomBuilder.class);

    private Set<AbstractGem> gems;
    private DocumentBuilder docBuilder;

    public GemDomBuilder() throws ParserConfigurationException {
        this.gems = new HashSet<>();
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        docBuilder = factory.newDocumentBuilder();
    }

    public Set<AbstractGem> getGems() {
        return gems;
    }

    public void buildSetStudents(String fileName) throws IOException, SAXException {
        Document doc = null;

        doc = docBuilder.parse(fileName);
        Element root = doc.getDocumentElement();
        NodeList diamondsList = root.getElementsByTagName("Diamond");
        NodeList pearlsList = root.getElementsByTagName("Pearl");
        NodeList emeraldsList = root.getElementsByTagName("Emerald");
        //for emeralds
        for (int i = 0; i < emeraldsList.getLength(); i++) {
            Element emeraldElement = (Element) emeraldsList.item(i);
            Emerald emerald = buildEmerald(emeraldElement);
            gems.add(emerald);
        }
        //for pearls
        for (int i = 0; i < pearlsList.getLength(); i++) {
            Element pearlElement = (Element) pearlsList.item(i);
            Pearl pearl = buildPearl(pearlElement);
            gems.add(pearl);
        }
        //for diamonds
        for (int i = 0; i < diamondsList.getLength(); i++) {
            Element diamondElement = (Element) diamondsList.item(i);
            Diamond diamond = buildDiamond(diamondElement);
            gems.add(diamond);
        }
    }

    // replace every getAttribute with getcontextelement
    private Emerald buildEmerald(Element element) {
        Emerald emerald = new Emerald();
        emerald.setId(Integer.parseInt(
                element.getAttribute("id")));
        emerald.setOrigin(Origin.fromValue(
                element.getAttribute("origin")));
        emerald.getContent().add(
                getElementTextContent(element, "name"));
        emerald.getContent().add(Preciousness.fromValue(
                getElementTextContent(element, "preciousness")));
        Weight weight = new Weight();
        Element weightElem = (Element) element.getElementsByTagName(
                "weight").item(0);
        weight.setUnit(getElementTextContent(weightElem, "unit"));
        weight.setValue(Double.parseDouble(getElementTextContent(weightElem, "value")));
        emerald.getContent().add(weight);
        String[] datestr = getElementTextContent(element, "gemArrival").split("-");
        GregorianCalendar gregorianCalendar = new GregorianCalendar(
                Integer.parseInt(datestr[2]),
                Integer.parseInt(datestr[1]) - 1,
                Integer.parseInt(datestr[0]));
        emerald.getContent().add(gregorianCalendar);
        // till here
        emerald.getContent().add(getElementTextContent(element, "color"));
        emerald.getContent().add(Double.parseDouble(
                getElementTextContent(element, "transparency")));
        emerald.getContent().add(Integer.parseInt(
                getElementTextContent(element, "amountofcuts")));
        return emerald;
    }

    private Diamond buildDiamond(Element element) {
        Diamond diamond = new Diamond();
        diamond.setId(Integer.parseInt(
                element.getAttribute("id")));
        diamond.setOrigin(Origin.fromValue(
                element.getAttribute("origin")));
        diamond.getContent().add(
                getElementTextContent(element, "name"));
        diamond.getContent().add(Preciousness.fromValue(
                getElementTextContent(element, "preciousness")));
        Weight weight = new Weight();
        Element weightElem = (Element) element.getElementsByTagName(
                "weight").item(0);
        weight.setUnit(getElementTextContent(weightElem, "unit"));
        weight.setValue(Double.parseDouble(getElementTextContent(weightElem, "value")));
        diamond.getContent().add(weight);
        String[] datestr = getElementTextContent(element, "gemArrival").split("-");
        GregorianCalendar gregorianCalendar = new GregorianCalendar(
                Integer.parseInt(datestr[2]),
                Integer.parseInt(datestr[1]) - 1,
                Integer.parseInt(datestr[0]));
        diamond.getContent().add(gregorianCalendar);
        diamond.getContent().add(Double.parseDouble(
                getElementTextContent(element, "transparency")));
        diamond.getContent().add(Integer.parseInt(
                getElementTextContent(element, "amountofcuts")));
        return diamond;
    }

    private Pearl buildPearl(Element element) {
        Pearl pearl = new Pearl();
        pearl.setId(Integer.parseInt(
                element.getAttribute("id")));
        pearl.setOrigin(Origin.fromValue(
                element.getAttribute("origin")));
        pearl.getContent().add(
                getElementTextContent(element, "name"));
        pearl.getContent().add(Preciousness.fromValue(
                getElementTextContent(element, "preciousness")));
        Weight weight = new Weight();
        Element weightElem = (Element) element.getElementsByTagName(
                "weight").item(0);
        weight.setUnit(getElementTextContent(weightElem, "unit"));
        weight.setValue(Double.parseDouble(getElementTextContent(weightElem, "value")));
        pearl.getContent().add(weight);
        String[] datestr = getElementTextContent(element, "gemArrival").split("-");
        GregorianCalendar gregorianCalendar = new GregorianCalendar(
                Integer.parseInt(datestr[2]),
                Integer.parseInt(datestr[1]) - 1,
                Integer.parseInt(datestr[0]));
        pearl.getContent().add(gregorianCalendar);
        pearl.getContent().add(getElementTextContent(element, "color"));
        pearl.getContent().add(Luster.fromValue(
                getElementTextContent(element, "luster")));
        return pearl;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

}

package by.training.task4.servlet;

import by.training.task4.builder.GemDomBuilder;
import by.training.task4.builder.GemsSaxBuilder;
import by.training.task4.builder.GemsStaxBuilder;
import by.training.task4.entity.AbstractGem;
import by.training.task4.entity.Diamond;
import by.training.task4.entity.Emerald;
import by.training.task4.entity.Pearl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

//don't forget to add in web.xml
public class WebParser extends HttpServlet {

    public static final Logger LOGGER = LogManager.getLogger(WebParser.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parserTypeStr = request.getParameter("parserType");
        String xmlPath = request.getParameter("xmlText");
        String language = request.getParameter("language");
        Set<AbstractGem> diamonds = new HashSet<>();
        Set<AbstractGem> pearls = new HashSet<>();
        Set<AbstractGem> emeralds = new HashSet<>();
        Set<AbstractGem> gems = new HashSet<>();

        request.setAttribute("lang","text" + "_" + language);
        switch (parserTypeStr) {
            case "sax":
                request.setAttribute("parser","Sax Parser");
                GemsSaxBuilder gemsSaxBuilder = new GemsSaxBuilder();
                gemsSaxBuilder.buildSetGems(xmlPath);
                gems = gemsSaxBuilder.getGems();
                for (AbstractGem i : gems) {
                    if (i instanceof Diamond) {
                        diamonds.add(i);
                    }
                    if (i instanceof Pearl) {
                        pearls.add(i);
                    }
                    if (i instanceof Emerald) {
                        emeralds.add(i);
                    }
                }
                break;
            case "stax":
                request.setAttribute("parser","Stax Parser");
                GemsStaxBuilder gemsStaxBuilder = new GemsStaxBuilder();
                gemsStaxBuilder.buildSetGems(xmlPath);
                gems = gemsStaxBuilder.getGems();
                for (AbstractGem i : gems) {
                    if (i instanceof Diamond) {
                        diamonds.add(i);
                    }
                    if (i instanceof Pearl) {
                        pearls.add(i);
                    }
                    if (i instanceof Emerald) {
                        emeralds.add(i);
                    }
                }
                break;
            case "dom":
                request.setAttribute("parser","Dom Parser");
                try {
                    GemDomBuilder gemsDomBuilder = new GemDomBuilder();
                    gemsDomBuilder.buildSetStudents(xmlPath);
                    gems = gemsDomBuilder.getGems();
                } catch (SAXException| ParserConfigurationException e) {
                    WebParser.LOGGER.warn(e.getMessage(),e);
                }
                for (AbstractGem i : gems) {
                    if (i instanceof Diamond) {
                        diamonds.add(i);
                    }
                    if (i instanceof Pearl) {
                        pearls.add(i);
                    }
                    if (i instanceof Emerald) {
                        emeralds.add(i);
                    }
                }
                break;
        }

        request.setAttribute("diamonds", diamonds);
        request.setAttribute("emeralds", emeralds);
        request.setAttribute("pearls", pearls);
        request.getRequestDispatcher("/jsp/result.jsp").forward(request, response);
    }
}

package by.training.task4.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.GregorianCalendar;

//don't forget to add in web.xml
//@WebServlet("/timeaction")
public class TimeServlet extends HttpServlet {

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
        GregorianCalendar gc = new GregorianCalendar();
        String timeJsp = request.getParameter("time");
        float delta = ((float) (gc.getTimeInMillis() - Long.parseLong(timeJsp))) / 1_000;
        request.setAttribute("res", delta);
        request.getRequestDispatcher("/jsp/result.jsp").forward(request, response);
    }
}

package by.training.task4.demo;

import java.util.Locale;
import java.util.ResourceBundle;

public class HamletInternational {

    public static void main(String[] args) {
        String encountry = "en";
        String enlanguage = "US";
        Locale localeEN = new Locale(enlanguage,encountry);
        ResourceBundle rb = ResourceBundle.getBundle("text",localeEN);
        System.out.println(rb.getString("str1") + " " + rb.getString("str2"));

        String rucountry = "ru";
        String rulanguage = "RU";
        Locale localeRU = new Locale(rulanguage,rucountry);
        ResourceBundle rbRU = ResourceBundle.getBundle("text",localeRU);
        System.out.println(rbRU.getString("str1") + " " + rbRU.getString("str2"));

        String bycountry = "by";
        String bylanguage = "BE";
        Locale localeBY = new Locale(bylanguage,bycountry);
        ResourceBundle rbBY = ResourceBundle.getBundle("text",localeBY);
        System.out.println(rbBY.getString("str1") + " " + rbBY.getString("str2"));

    }


}

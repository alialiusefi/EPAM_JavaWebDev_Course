package by.training.task2.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataParser {

    public List<String> parseListOfString(List<String> list) {
        List<String> parsedList = new ArrayList<>();
        for (String i : list) {
            parsedList.addAll(Arrays.asList(i.split(" ")));
        }
        return parsedList;
    }

    public List<Integer> parseToListOfInt(List<String> list) {
        List<Integer> parsedList = new ArrayList<>();
        for (String strInteger : list) {
            parsedList.add(Integer.parseInt(strInteger));
        }
        return parsedList;
    }


}

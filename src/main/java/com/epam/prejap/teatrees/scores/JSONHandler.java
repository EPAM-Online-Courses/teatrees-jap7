package com.epam.prejap.teatrees.scores;

import java.util.Collections;
import java.util.List;

/**
 * @author Herman Kulik
 */
public class JSONHandler {
    private List<Record> recordsList;

    public JSONHandler() {
        JSONParser parser = new JSONParser();
        this.recordsList = parser.getRecordsList();
    }

    public static void main(String[] args) {
        JSONHandler jsonHandler = new JSONHandler();

        Collections.sort(jsonHandler.recordsList);
        jsonHandler.recordsList.forEach(System.out::println);
    }

    public Record getHighestRecord(){
        return null;
    }


}

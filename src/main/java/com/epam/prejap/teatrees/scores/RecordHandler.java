package com.epam.prejap.teatrees.scores;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Andrzej Sadlek
 * @author Herman Kulik
 */
public class RecordHandler {
    private final List<Record> recordsList;
    private final JSONParser parser;

    public RecordHandler(File fileToParse) {
        this.parser = new JSONParser(fileToParse);
        this.recordsList = parser.getRecordsList();
    }

    public List<Record> getHighScore(){
        Collections.sort(recordsList);
        List<Record> topResults = recordsList.subList(0, 25); // TODO sprawdz czy < 25
        topResults.forEach(System.out::println); //TODO remove
        System.out.println(topResults.size()); //TODO remove
        return topResults;
    }

    public boolean verifyNewRecord(Record newRecord){ // verifies new record, returns true if you beat a record
        if(recordsList.contains(newRecord)){
            Record oldRecord = recordsList.get(recordsList.indexOf(newRecord));
            if(newRecord.getScore() > oldRecord.getScore()){
                recordsList.remove(oldRecord);
                recordsList.add(newRecord);
                return true;
            }
        } else {
            recordsList.add(newRecord);
        }
        return false;
    }


}

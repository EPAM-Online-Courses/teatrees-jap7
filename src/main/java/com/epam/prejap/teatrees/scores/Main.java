package com.epam.prejap.teatrees.scores;

import java.io.File;
import java.util.Objects;

/**
 * @author Herman Kulik
 */
public class Main {
    public static void main(String[] args) {
        File jsonFile = new File(Objects.requireNonNull(RecordHandler.class.getClassLoader().getResource("score.json")).getFile());
        RecordHandler handler = new RecordHandler(jsonFile);

        handler.getHighScore();
        Record newRecord = new Record("her", 3000000);
        handler.verifyNewRecord(newRecord);
        handler.getHighScore();
    }
}

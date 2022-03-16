package com.epam.prejap.teatrees.scores;

import java.io.File;
import java.util.Objects;

/**
 * @author Herman Kulik
 */
public class Main {
    public static void main(String[] args) {
        //sciezka do .json
        File jsonFile = new File(Objects.requireNonNull(RecordHandler.class.getClassLoader().getResource("score.json")).getFile());
        //tworzy parsera i otrzymuje od niego liste rekordow
        RecordHandler handler = new RecordHandler(jsonFile);

        // pokazuje top 25 rekordow
        handler.getHighScore();

        //dodajemy nowy record do list
        Record newRecord = new Record("her", 3000000);
        handler.verifyNewRecord(newRecord);

        //dodaje nowy record do bazy Store
        handler.updateStore();
        // pokazuje odswiezone 25 rekordow
        handler.getHighScore();
    }
}

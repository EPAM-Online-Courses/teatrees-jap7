package com.epam.prejap.teatrees.records;

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
        RecordHandler handler = new RecordHandler();


    }
}

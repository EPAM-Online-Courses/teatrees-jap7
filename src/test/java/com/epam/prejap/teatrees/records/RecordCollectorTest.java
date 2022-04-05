package com.epam.prejap.teatrees.records;

import org.testng.annotations.Test;

@Test
public class RecordCollectorTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void exceptionShouldBeThrownIfNullPassed() {
        RecordCollector scoreRecord = new RecordCollector(null);
    }

}

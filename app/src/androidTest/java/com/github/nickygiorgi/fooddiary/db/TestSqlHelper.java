package com.github.nickygiorgi.fooddiary.db;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TestSqlHelper {

    @Test
    public void persistDate() {

        // testing null input
        assertNull("null date should persist to null", sqlHelper.persistDate(null));

        //testing not null input
        assertNotNull("valid date should not persist to null", sqlHelper.persistDate(new Date()));
        Date now = new Date();
        assertEquals("valid date should persist to java.util date.getTime()", now.getTime(), (long) sqlHelper.persistDate(now));
    }

}

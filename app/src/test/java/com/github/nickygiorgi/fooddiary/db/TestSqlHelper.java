package com.github.nickygiorgi.fooddiary.db;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TestSqlHelper {

    @Test
    public void persistDate() {
        assertNull("null date should persist to null", sqlHelper.persistDate(null));

        Date now = new Date();
        long expected = now.getTime();
        long actual = sqlHelper.persistDate(now);
        assertNotNull("valid date should not persist to null", actual);
        assertEquals("valid date should persist to java.util date.getTime()", expected, actual);
    }

    @Test
    public void loadDate() {
        assertNull("negative date record should return null", sqlHelper.loadDate(-1));

        assertNull("null date record should return null", sqlHelper.loadDate(0));

        Date expected = new Date();
        long now = expected.getTime();
        Date actual = sqlHelper.loadDate(now);
        assertEquals("non null date record should return the right date", expected, actual);
    }

}
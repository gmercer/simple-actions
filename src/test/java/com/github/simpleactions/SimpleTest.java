package com.github.simpleactions;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SimpleTest {

    @BeforeAll
    public static void setup() {
        Simple.migrate();
    }

    @Test
    public void testName() {
        assertEquals("Simple", new Simple().getName());
    }

    @Test
    public void testGetUsers() {
        Simple simple = new Simple();
        assertNotEquals(0, simple.getUsers().length) ;
    }
}
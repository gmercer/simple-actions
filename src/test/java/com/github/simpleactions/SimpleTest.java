package com.github.simpleactions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTest {

    @Test
    public void testName() {
        assertEquals("Simple", new Simple().getName());
    }

}
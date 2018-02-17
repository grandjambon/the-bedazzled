package com.pj.bedazzled.springboot.controller;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class BeDazzledControllerTest {

    @Test
    public void testSeasonFormatting() {
        assertEquals("season05", String.format("season%02d", 5));
        assertEquals("season15", String.format("season%02d", 15));
    }

}
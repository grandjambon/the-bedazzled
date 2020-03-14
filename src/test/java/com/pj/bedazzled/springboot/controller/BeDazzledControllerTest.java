package com.pj.bedazzled.springboot.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ModelMap;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={BeDazzledConfiguration.class, BeDazzledController.class})
public class BeDazzledControllerTest {

    @SuppressWarnings("unused")
    @Autowired
    private BeDazzledController beDazzledController;

    @Test
    public void testSeasonFormatting() {
        assertEquals("season05", String.format("season%02d", 5));
        assertEquals("season15", String.format("season%02d", 15));
    }

    @Test
    public void testSeason() throws IOException {
        ModelMap modelMap = new ModelMap();
        beDazzledController.season(39, modelMap);

        assertNotNull(modelMap);
    }

}
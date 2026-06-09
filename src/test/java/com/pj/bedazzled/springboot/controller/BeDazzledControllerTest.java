package com.pj.bedazzled.springboot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ModelMap;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeDazzledControllerTest {

    @Autowired
    private BeDazzledController beDazzledController;

    @Test
    void testSeasonFormatting() {
        assertThat(String.format("season%02d", 5)).isEqualTo("season05");
        assertThat(String.format("season%02d", 15)).isEqualTo("season15");
    }

    @Test
    void testSeason() throws IOException {
        ModelMap modelMap = new ModelMap();
        beDazzledController.season(39, modelMap);
        assertThat(modelMap).isNotNull();
    }
}

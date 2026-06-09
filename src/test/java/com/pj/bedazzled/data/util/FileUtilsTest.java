package com.pj.bedazzled.data.util;

import com.pj.bedazzled.data.model.Match;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import static com.pj.bedazzled.data.util.FileUtils.getSeasonNames;
import static org.assertj.core.api.Assertions.assertThat;

class FileUtilsTest {

    @Test
    void testFileUtilsTest() throws IOException {
        Map<String, Match> matches = FileUtils.getSeasonForDirectory("season01");
        assertThat(matches).hasSize(14);
    }

    @Test
    void testGetSeasons() throws IOException {
        Collection<String> seasons = getSeasonNames();
        assertThat(seasons).isNotEmpty()
                .allSatisfy(s -> assertThat(s).startsWith("season"));
    }
}

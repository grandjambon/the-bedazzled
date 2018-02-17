package com.pj.bedazzled.data.util;

import com.pj.bedazzled.data.model.Match;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import static com.pj.bedazzled.data.util.FileUtils.getSeasonNames;
import static org.junit.Assert.*;

/**
 * All of this has a dependency on bedazzled-data being on the classpath
 */
public class FileUtilsTest {

    @Test
    public void testFileUtilsTest() throws IOException {
        Map<String, Match> matches = FileUtils.getSeasonForDirectory("season01");
        assertEquals(14, matches.size());
    }

    @Test
    public void testGetSeasons() throws IOException {
        Collection<String> seasons = getSeasonNames();
        assertNotEquals(0,seasons);
//        assertEquals(26, seasons.size());   // disable this going forward

        for (String s : seasons) {
            assertTrue(s.startsWith("season"));
        }
    }
}
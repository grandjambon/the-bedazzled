package com.pj.bedazzled.data.util;

import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SpringFileResolverTest {

    @Test
    public void test() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("/seasons/season01/reports/*");
        assertNotNull(resources);
        assertEquals(14, resources.length);
    }

    @Test
    public void testGetSeasons() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("/seasons/*/");
        assertNotNull(resources);

    }

}

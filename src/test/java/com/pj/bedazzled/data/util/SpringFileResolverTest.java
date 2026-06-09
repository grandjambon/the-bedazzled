package com.pj.bedazzled.data.util;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class SpringFileResolverTest {

    @Test
    void test() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:seasons/season01/reports/*");
        assertThat(resources).hasSize(14);
    }

    @Test
    void testGetSeasons() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:seasons/*/");
        assertThat(resources).isNotNull();
    }
}

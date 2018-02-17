package com.pj.bedazzled.springboot.controller;

import com.pj.bedazzled.data.BeDazzledDataManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@SuppressWarnings("unused")
@Configuration
public class BeDazzledConfiguration {

    @Bean
    @Scope("singleton")
    public BeDazzledDataManager getBeDazzledDataManager() {
        return new BeDazzledDataManager();
    }
}

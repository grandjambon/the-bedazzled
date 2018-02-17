package com.pj.bedazzled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages={"com.pj.bedazzled.springboot"})
public class BeDazzledWebWarApp extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BeDazzledWebWarApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BeDazzledWebWarApp.class, args);
    }
}

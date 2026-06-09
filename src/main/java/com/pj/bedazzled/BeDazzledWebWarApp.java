package com.pj.bedazzled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.pj.bedazzled.springboot"})
public class BeDazzledWebWarApp {

    public static void main(String[] args) {
        SpringApplication.run(BeDazzledWebWarApp.class, args);
    }
}

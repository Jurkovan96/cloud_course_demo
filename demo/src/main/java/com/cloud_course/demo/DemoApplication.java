package com.cloud_course.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        System.out.println("this is a custom log!");
        System.out.println("this is a second custom log!");

        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        springApplication.setAdditionalProfiles("amazon");
        springApplication.run();
    }
}

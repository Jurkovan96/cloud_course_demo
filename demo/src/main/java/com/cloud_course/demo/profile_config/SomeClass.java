package com.cloud_course.demo.profile_config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
public class SomeClass {

    @Bean
    @Profile("azure")
    public void logNameAzure() {
        log.info("Log from Azure");
    }

    @Bean
    @Profile("amazon")
    public void logNameAmazon() {
        log.info("Log from Amazon");
    }

}

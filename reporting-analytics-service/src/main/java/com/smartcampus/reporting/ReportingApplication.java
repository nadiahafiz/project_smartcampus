package com.smartcampus.reporting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ReportingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReportingApplication.class, args);
    }

    @Bean // This tells Spring to create the object so you can use it in your controller
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
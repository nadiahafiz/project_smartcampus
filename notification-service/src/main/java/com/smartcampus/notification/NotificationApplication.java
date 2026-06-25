package com.smartcampus.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationApplication {
    public static void main(String[] args) {
        // Keeps her app running on port 8083 so it doesn't clash with your port 8080!
        System.setProperty("server.port", "8083");
        SpringApplication.run(NotificationApplication.class, args);
    }
}
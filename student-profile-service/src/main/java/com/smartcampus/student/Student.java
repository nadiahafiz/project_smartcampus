package com.smartcampus.student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // 👈 This tells Spring Boot to create a table named "STUDENT" in H2!
public class Student {

    @Id // 👈 This marks this field as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 👈 Automatically increments IDs (1, 2, 3...)
    private Long id;
    
    private String name;
    private String email; // Add whatever fields you have!

    // --- No-Args Constructor (Required by JPA) ---
    public Student() {}

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
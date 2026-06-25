package com.smartcampus.student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // 👈 This tells Spring Boot to create a table named "STUDENT" in H2!
public class Student {

    @Id // 👈 This marks this field as the Primary Key
    private String studentId;
    
    private String name;
    private String email;
    private String major;// Add whatever fields you have!

    // --- No-Args Constructor (Required by JPA) ---
    public Student() {}

    // --- Getters and Setters ---
    public String getStudentId() { 
        return studentId; 
    }

    public void setStudentId(String studentId) { 
        this.studentId = studentId; 
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
}
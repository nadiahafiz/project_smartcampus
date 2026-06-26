package com.smartcampus.reporting.dto;

public class StudentDTO {
    private String studentId;
    private String name;
    private String email;
    private String major; // 🔥 We need this to group them!

    // Default Constructor
    public StudentDTO() {}

    // Getters and Setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
}
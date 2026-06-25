package com.smartcampus.student;

public class Student {
    private String id;
    private String name;
    private String email;
    private String major;

    public Student() {}

    public Student(String id, String name, String email, String major) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.major = major;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
}
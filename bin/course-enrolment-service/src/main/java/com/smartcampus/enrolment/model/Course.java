package com.smartcampus.enrolment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses") // Creates a table named 'courses' in H2
public class Course {
    @Id // Sets courseCode as the Primary Key
    private String courseCode;
    private String courseName;
    private int capacity;
    private int enrolledCount;

    public Course(String courseCode, String courseName, int capacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.capacity = capacity;
        this.enrolledCount = 0;
    }

    // Getters and Setters
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getCapacity() { return capacity; }
    public int getEnrolledCount() { return enrolledCount; }
    public void setEnrolledCount(int enrolledCount) { this.enrolledCount = enrolledCount; }
    public Course() {} // JPA requires an empty constructor!
}
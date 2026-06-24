package com.smartcampus.enrolment.model;

public class Course {
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
}
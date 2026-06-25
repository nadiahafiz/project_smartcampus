package com.smartcampus.enrolment.model;

public class EnrolmentRequest {
    private String studentId;
    private String courseCode;

    // Getters and Setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
}
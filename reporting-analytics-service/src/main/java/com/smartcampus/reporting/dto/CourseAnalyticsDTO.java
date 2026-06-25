package com.smartcampus.reporting.dto;

public class CourseAnalyticsDTO {
    private String courseName;
    private int addCount;
    private int dropCount;

    public CourseAnalyticsDTO(String courseName, int addCount, int dropCount) {
        this.courseName = courseName;
        this.addCount = addCount;
        this.dropCount = dropCount;
    }

    public String getCourseName() { return courseName; }
    public int getAddCount() { return addCount; }
    public int getDropCount() { return dropCount; }
}
package com.smartcampus.reporting.dto;

public class ProgrammeReportDTO {
    private String programmeName;
    private long studentCount;

    public ProgrammeReportDTO(String programmeName, long studentCount) {
        this.programmeName = programmeName;
        this.studentCount = studentCount;
    }

    // Getters
    public String getProgrammeName() { return programmeName; }
    public long getStudentCount() { return studentCount; }
}
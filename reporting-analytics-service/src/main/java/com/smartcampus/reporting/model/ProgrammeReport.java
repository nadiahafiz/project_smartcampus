package com.smartcampus.reporting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProgrammeReport {
    @Id
    @GeneratedValue
    private Long id;
    private String programmeName;
    private long studentCount;

    public ProgrammeReport() {}
    public ProgrammeReport(String programmeName, long studentCount) {
        this.programmeName = programmeName;
        this.studentCount = studentCount;
    }
    
    public Long getId() { return id; }
    public String getProgrammeName() { return programmeName; }
    public long getStudentCount() { return studentCount; }
}
package com.smartcampus.notification;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Auto-incrementing primary key for tracking alerts
    
    private String studentId;
    private String alertType; // e.g., "Library Booking" or "Course Enrolment"
    private String message;

    // --- No-Args Constructor ---
    public Notification() {}

 // --- Getters and Setters ---
    public Long getId() { 
        return this.id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getStudentId() { 
        return this.studentId; 
    }

    public void setStudentId(String studentId) { 
        this.studentId = studentId; 
    }

    public String getAlertType() { 
        return this.alertType; 
    }

    public void setAlertType(String alertType) { 
        this.alertType = alertType; 
    }

    public String getMessage() { 
        return this.message; 
    }

    public void setMessage(String message) { 
        this.message = message; 
    }
}
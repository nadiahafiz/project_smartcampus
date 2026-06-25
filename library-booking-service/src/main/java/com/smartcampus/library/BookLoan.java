package com.smartcampus.library;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String studentId;
    private String bookTitle;
    private String loanStatus; // Added to trace the status in English

    // Default constructor required by JPA
    public BookLoan() {}

    public BookLoan(String studentId, String bookTitle, String loanStatus) {
        this.studentId = studentId;
        this.bookTitle = bookTitle;
        this.loanStatus = loanStatus;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public String getLoanStatus() { return loanStatus; }
    public void setLoanStatus(String loanStatus) { this.loanStatus = loanStatus; }
}
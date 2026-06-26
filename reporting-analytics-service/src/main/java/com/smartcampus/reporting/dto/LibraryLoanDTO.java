package com.smartcampus.reporting.dto;

public class LibraryLoanDTO {
    private String studentId;
    private String bookTitle;
    private String loanStatus;

    public LibraryLoanDTO() {}

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    public String getLoanStatus() { return loanStatus; }
    public void setLoanStatus(String loanStatus) { this.loanStatus = loanStatus; }
}
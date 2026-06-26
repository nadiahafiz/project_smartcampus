package com.smartcampus.reporting.dto;

public class LibraryBookingDTO {
    private String studentId;
    private String roomNumber;
    private String bookingDate;

    public LibraryBookingDTO() {}

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }
}
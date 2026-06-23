package com.smartcampus.library;

public class Booking {
    private String studentId;
    private String roomNumber;

    public Booking() {}

    public Booking(String studentId, String roomNumber) {
        this.studentId = studentId;
        this.roomNumber = roomNumber;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
}
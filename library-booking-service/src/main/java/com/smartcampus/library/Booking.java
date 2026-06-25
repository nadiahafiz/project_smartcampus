package com.smartcampus.library;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String studentId;
    private String roomNumber;
    private String bookingDate; // Tambahan pembolehubah tarikh baru

    // Constructor wajib untuk JPA
    public Booking() {}

    public Booking(String studentId, String roomNumber, String bookingDate) {
        this.studentId = studentId;
        this.roomNumber = roomNumber;
        this.bookingDate = bookingDate;
    }

    // Getter dan Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }
}
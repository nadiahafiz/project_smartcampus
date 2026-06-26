package com.smartcampus.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/library")
@CrossOrigin(origins = "*")
public class LibraryController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookLoanRepository bookLoanRepository;


    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @PostMapping("/bookings")
    public Booking createBooking(@RequestBody Booking booking) {
        // 1. Save the booking in the library database first
        Booking savedBooking = bookingRepository.save(booking);
        
        // 2. SEND BACKGROUND NETWORK CALL TO NOTIFICATION SERVICE
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            Map<String, String> notificationPayload = new HashMap<>();
            notificationPayload.put("studentId", savedBooking.getStudentId()); // 🔥 Changed from recipientId to studentId
            notificationPayload.put("alertType", "Library Booking");          // 🔥 Added this so the badge isn't null
            notificationPayload.put("message", "Discussion Room successfully reserved for student " + savedBooking.getStudentId() + ".");

            String notificationUrl = "http://localhost:8083/api/notifications";
            restTemplate.postForEntity(notificationUrl, notificationPayload, String.class);
            
        } catch (Exception e) {
            System.out.println("Notification alert failed to deliver, but booking was saved successfully.");
        }
        return savedBooking;
    }


    @GetMapping("/loans")
    public List<BookLoan> getAllLoans() {
        return bookLoanRepository.findAll();
    }

    // 🔥 UPDATED METHOD TO FIRE THE NOTIFICATION FOR BOOK LOANS 🔥
    @PostMapping("/loans")
    public BookLoan createLoan(@RequestBody BookLoan loan) {
        loan.setLoanStatus("Borrowed"); 
        
        // 1. Save the loan in the library database first
        BookLoan savedLoan = bookLoanRepository.save(loan);
        
        // 2. SEND BACKGROUND NETWORK CALL TO NOTIFICATION SERVICE
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            Map<String, String> notificationPayload = new HashMap<>();
            notificationPayload.put("studentId", savedLoan.getStudentId());    // 🔥 Changed from recipientId to studentId
            notificationPayload.put("alertType", "Library Loan");             // 🔥 Added this so the badge isn't null
            notificationPayload.put("message", "Library Book loan successfully recorded. Book Title: " + savedLoan.getBookTitle() + ".");

            String notificationUrl = "http://localhost:8083/api/notifications";
            restTemplate.postForEntity(notificationUrl, notificationPayload, String.class);
            
        } catch (Exception e) {
            System.out.println("Notification alert failed to deliver for book loan, but loan was saved successfully.");
        }
        
        return savedLoan;
    }
}
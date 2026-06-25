package com.smartcampus.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate; // 🛠️ Added for network communication
import java.util.HashMap;                       // 🛠️ Added for map creation
import java.util.Map;                           // 🛠️ Added for map creation
import java.util.List;

@RestController
@RequestMapping("/api/library")
@CrossOrigin(origins = "*") 
public class LibraryController {

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping("/bookings")
    public Booking createBooking(@RequestBody Booking newBooking) {
        // 1. Save the booking in the library database first
        Booking savedBooking = bookingRepository.save(newBooking);
        
        // 2. SEND BACKGROUND NETWORK CALL TO NOTIFICATION SERVICE
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            // Prepare the matching notification payload structure
            Map<String, String> notificationPayload = new HashMap<>();
            notificationPayload.put("studentId", savedBooking.getStudentId()); // Pulls studentId from library object
            notificationPayload.put("alertType", "Library Booking");
            notificationPayload.put("message", "Discussion Room successfully reserved for student " + savedBooking.getStudentId() + ".");

            // Target your friend's notification service API port 8083
            String notificationUrl = "http://localhost:8083/api/notifications";
            restTemplate.postForEntity(notificationUrl, notificationPayload, String.class);
            
        } catch (Exception e) {
            // If the notification service is stopped, the booking still saves without crashing the library app!
            System.out.println("Notification alert failed to deliver, but booking was saved successfully.");
        }

        return savedBooking;
    }

    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
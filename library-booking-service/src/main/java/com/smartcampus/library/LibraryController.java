package com.smartcampus.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/library")
@CrossOrigin(origins = "*") 
public class LibraryController {

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping("/bookings")
    public Booking createBooking(@RequestBody Booking newBooking) {
        return bookingRepository.save(newBooking);
    }

    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
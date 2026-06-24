package com.smartcampus.library;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/library")
@CrossOrigin(origins = "*") 
public class LibraryController {

    private static List<Booking> bookingList = new ArrayList<>();

    @PostMapping("/bookings")
    public Booking createBooking(@RequestBody Booking newBooking) {
        bookingList.add(newBooking);
        System.out.println("New booking received for Student ID: " + newBooking.getStudentId());
        return newBooking;
    }

    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingList;
    }
}
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

    @Autowired
    private BookLoanRepository bookLoanRepository;


    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @PostMapping("/bookings")
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingRepository.save(booking);
    }


    @GetMapping("/loans")
    public List<BookLoan> getAllLoans() {
        return bookLoanRepository.findAll();
    }

    @PostMapping("/loans")
    public BookLoan createLoan(@RequestBody BookLoan loan) {
        loan.setLoanStatus("Borrowed"); 
        return bookLoanRepository.save(loan);
    }
}
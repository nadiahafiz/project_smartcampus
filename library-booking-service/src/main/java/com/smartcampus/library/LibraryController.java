package com.smartcampus.library;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/library")
@CrossOrigin(origins = "*")
public class LibraryController {

    @Autowired
    private BookingRepository bookingRepository; // Untuk urusan tempahan bilik

    @Autowired
    private BookLoanRepository bookLoanRepository; // Sila pastikan nama kelas ini sepadan dengan fail Repository anda

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
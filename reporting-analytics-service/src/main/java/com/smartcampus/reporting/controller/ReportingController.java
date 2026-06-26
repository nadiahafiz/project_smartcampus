package com.smartcampus.reporting.controller;

import com.smartcampus.reporting.model.ProgrammeReport;
import com.smartcampus.reporting.repository.ProgrammeReportRepository;
import com.smartcampus.reporting.dto.*;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*") // Allows your Port 8080 UI to fetch this data without CORS errors
public class ReportingController {
	
	private final RestTemplate restTemplate = new RestTemplate();

    private final ProgrammeReportRepository repository;

    public ReportingController(ProgrammeReportRepository repository) {
        this.repository = repository;
    }

    // 1. Existing Enrollment Data (Fetches from H2 DB)
    @GetMapping("/enrolment-by-programme")
    public List<ProgrammeReportDTO> getEnrolmentReport() {
        try {
            // 1. Fetch the raw array of real students from your friend's profile service (Port 8080)
            String studentServiceUrl = "http://localhost:8080/api/students";
            StudentDTO[] students = restTemplate.getForObject(studentServiceUrl, StudentDTO[].class);

            if (students == null || students.length == 0) {
                return new ArrayList<>(); // Return empty list if no students exist yet
            }

            // 2. Use Java Streams to group students by their major and count them instantly!
            Map<String, Long> groupedData = Arrays.stream(students)
                    .collect(Collectors.groupingBy(StudentDTO::getMajor, Collectors.counting()));

            // 3. Map the grouped data into the format your UI expects
            return groupedData.entrySet().stream()
                    .map(entry -> new ProgrammeReportDTO(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("Error calling Student Service: " + e.getMessage());
            return new ArrayList<>(); // Fallback gracefully if Port 8080 is down
        }
    }

 // 2. Live Course Capacity Tracker (Fetches dynamically from Port 8081)
    @GetMapping("/course-analytics")
    public List<CourseAnalyticsDTO> getCourseAnalytics() {
        try {
            // 1. Hit your Course Enrolment Microservice on port 8081
            String enrolmentServiceUrl = "http://localhost:8081/api/enrolments/courses";
            CourseDTO[] courses = restTemplate.getForObject(enrolmentServiceUrl, CourseDTO[].class);

            List<CourseAnalyticsDTO> analyticsList = new ArrayList<>();

            if (courses != null) {
                for (CourseDTO course : courses) {
                    // Calculate available slots dynamically: Max Capacity - Enrolled Count
                    int availableSeats = course.getCapacity() - course.getEnrolledCount();
                    
                    // Map it cleanly: Course Name, Enrolled Count (Adds), Available Seats (Drops column placeholder)
                    analyticsList.add(new CourseAnalyticsDTO(
                        course.getCourseCode() + " - " + course.getCourseName(), 
                        course.getEnrolledCount(), 
                        availableSeats
                    ));
                }
            }
            return analyticsList;

        } catch (Exception e) {
            System.out.println("Error calling Enrolment Service: " + e.getMessage());
            return new ArrayList<>(); // Fallback gracefully if Port 8081 is down
        }
    }

 // 3. Live Library & Facility Usage (Fetches dynamically from Port 8082)
    @GetMapping("/library-usage")
    public List<LibraryUsageDTO> getLibraryUsage() {
        try {
            // 🔥 UPDATED PATHS TO INCLUDE "/api/library" MATCHING YOUR CONTROLLER
            String bookingsUrl = "http://localhost:8082/api/library/bookings"; 
            String loansUrl = "http://localhost:8082/api/library/loans";

            // 1. Pull current active arrays from your library system
            LibraryBookingDTO[] bookings = restTemplate.getForObject(bookingsUrl, LibraryBookingDTO[].class);
            LibraryLoanDTO[] loans = restTemplate.getForObject(loansUrl, LibraryLoanDTO[].class);

            int totalBookings = (bookings != null) ? bookings.length : 0;
            int totalLoans = (loans != null) ? loans.length : 0;

            // 2. Map the live metrics dynamically into your friend's front-end layout cards
            return Arrays.asList(
                new LibraryUsageDTO("Discussion Rooms", "Facility", totalBookings, "Active Reservations"),
                new LibraryUsageDTO("Total Checked Out Books", "Asset", totalLoans, "Current Loans")
            );

        } catch (Exception e) {
            System.out.println("Error calling Library Service: " + e.getMessage());
            return Arrays.asList(
                new LibraryUsageDTO("Library Microservice Offline", "Error", 0, "Port 8082 misconfigured")
            );
        }
    }

 // 4. Live Notification Source Analytics (Fetches dynamically from Port 8083)
    @GetMapping("/notification-analytics")
    public List<NotificationAnalyticsDTO> getNotificationAnalytics() {
        try {
            // 1. Fetch live system logs from the notification engine on port 8083
            String notificationServiceUrl = "http://localhost:8083/api/notifications";
            NotificationLogDTO[] logs = restTemplate.getForObject(notificationServiceUrl, NotificationLogDTO[].class);

            int enrolmentAlerts = 0;
            int libraryAlerts = 0;

            // 2. Parse logs to categorize traffic origins based on keywords
            if (logs != null) {
                for (NotificationLogDTO log : logs) {
                    String msg = log.getMessage().toLowerCase();
                    if (msg.contains("enrolled") || msg.contains("course")) {
                        enrolmentAlerts++;
                    } else if (msg.contains("library") || msg.contains("room") || msg.contains("book")) {
                        libraryAlerts++;
                    }
                }
            }

            int totalProcessed = enrolmentAlerts + libraryAlerts;

            // 3. Map values directly into your friend's existing dashboard table structure
            return Arrays.asList(
                new NotificationAnalyticsDTO("Course Registration Alerts", totalProcessed, enrolmentAlerts),
                new NotificationAnalyticsDTO("Library Desk Activity Alerts", totalProcessed, libraryAlerts)
            );

        } catch (Exception e) {
            System.out.println("Error calling Notification Service: " + e.getMessage());
            return Arrays.asList(
                new NotificationAnalyticsDTO("Notification Service Offline", 0, 0)
            );
        }
    }
}
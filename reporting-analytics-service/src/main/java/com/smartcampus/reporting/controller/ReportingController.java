package com.smartcampus.reporting.controller;

import com.smartcampus.reporting.model.ProgrammeReport;
import com.smartcampus.reporting.repository.ProgrammeReportRepository;
import com.smartcampus.reporting.dto.*;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {

    private final ProgrammeReportRepository repository;

    public ReportingController(ProgrammeReportRepository repository) {
        this.repository = repository;
    }

    // 1. Existing Enrollment Data (Fetches from H2 DB)
    @GetMapping("/enrolment-by-programme")
    public List<ProgrammeReport> getEnrolmentReport() {
        return repository.findAll();
    }

    // 2. Course Analytics (Dummy Data)
    @GetMapping("/course-analytics")
    public List<CourseAnalyticsDTO> getCourseAnalytics() {
        return Arrays.asList(
            new CourseAnalyticsDTO("Data Structures", 120, 5),
            new CourseAnalyticsDTO("Advanced Thermodynamics", 45, 12),
            new CourseAnalyticsDTO("Machine Learning", 85, 2)
        );
    }

    // 3. Library Usage (Dummy Data)
    @GetMapping("/library-usage")
    public List<LibraryUsageDTO> getLibraryUsage() {
        return Arrays.asList(
            new LibraryUsageDTO("Discussion Room A", "Room", 88, "2:00 PM - 4:00 PM"),
            new LibraryUsageDTO("Study Pod 3", "Room", 45, "10:00 AM - 12:00 PM"),
            new LibraryUsageDTO("Clean Code (Book)", "Book", 100, "N/A")
        );
    }

    // 4. Notification Analytics (Dummy Data)
    @GetMapping("/notification-analytics")
    public List<NotificationAnalyticsDTO> getNotificationAnalytics() {
        return Arrays.asList(
            new NotificationAnalyticsDTO("Tuition Payment", 500, 450),
            new NotificationAnalyticsDTO("Library Overdue Alert", 150, 45),
            new NotificationAnalyticsDTO("Enrolment Window Open", 1200, 1150)
        );
    }
}
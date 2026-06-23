package com.smartcampus.reporting.controller;

import com.smartcampus.reporting.dto.ProgrammeReportDTO; // Import added
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List; // Import added

@RestController
@RequestMapping("/api/reports")
public class ReportingController {

    private final RestTemplate restTemplate;

    public ReportingController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/enrolment-by-programme")
    public ResponseEntity<List<ProgrammeReportDTO>> getReport() {
        // Your logic...
        return ResponseEntity.ok(fetchMockData());
    }

    private List<ProgrammeReportDTO> fetchMockData() {
        return Arrays.asList(
            new ProgrammeReportDTO("Computer Science", 150),
            new ProgrammeReportDTO("Mechanical Engineering", 95)
        );
    }
}
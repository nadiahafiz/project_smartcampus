package com.smartcampus.enrolment.controller;

import com.smartcampus.enrolment.model.Course;
import com.smartcampus.enrolment.model.EnrolmentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/enrolments")
public class EnrolmentController {

    // Database-per-service isolation (In-memory map for courses)
    private final ConcurrentHashMap<String, Course> courseDatabase = new ConcurrentHashMap<>();
    private final RestTemplate restTemplate = new RestTemplate();

    public EnrolmentController() {
        // Pre-populating some campus courses for testing
        courseDatabase.put("BITP3123", new Course("BITP3123", "Distributed Application Development", 3));
        courseDatabase.put("BITP3113", new Course("BITP3113", "Software Engineering", 60));
    }

    @PostMapping
    public ResponseEntity<String> enrolStudent(@RequestBody EnrolmentRequest request) {
        String studentId = request.getStudentId();
        String courseCode = request.getCourseCode();

        // 1. Verify if the course exists in OUR database
        Course course = courseDatabase.get(courseCode);
        if (course == null) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        // 2. Check course capacity
        if (course.getEnrolledCount() >= course.getCapacity()) {
            return new ResponseEntity<>("Course is full!", HttpStatus.BAD_REQUEST);
        }

        // 3. SERVICE COMPOSITION (R4): Call your friend's Student Profile Service
        // Assuming her service runs on port 8080 (check her application.properties)
        String studentServiceUrl = "http://localhost:8080/students/" + studentId;
        
        try {
            // Synchronous GET call to see if student exists
            ResponseEntity<Object> response = restTemplate.getForEntity(studentServiceUrl, Object.class);
            
            if (response.getStatusCode() == HttpStatus.OK) {
                // Student exists! Proceed with enrollment
                course.setEnrolledCount(course.getEnrolledCount() + 1);
                return new ResponseEntity<>("Enrolment successful for student " + studentId + " in " + courseCode, HttpStatus.CREATED);
            }
        } catch (HttpClientErrorException.NotFound e) {
            // Capture 404 from your friend's service
            return new ResponseEntity<>("Enrolment rejected: Student ID does not exist in Student Profile Service.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handles system downtime (Failure Handling R9)
            return new ResponseEntity<>("Student Service is currently unavailable. Try again later.", HttpStatus.SERVICE_UNAVAILABLE);
        }

        return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
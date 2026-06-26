package com.smartcampus.enrolment.controller;

import com.smartcampus.enrolment.model.Course;
import com.smartcampus.enrolment.model.EnrolmentRequest;
import com.smartcampus.enrolment.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;  // 🛠️ Added for notification payload mapping
import java.util.Map;      // 🛠️ Added for notification payload mapping
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // Crucial link for your HTML UI
@RequestMapping("/api/enrolments")
public class EnrolmentController {

    @Autowired
    private CourseRepository courseRepository; 

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void initData() {
        if (courseRepository.count() == 0) { 
            courseRepository.save(new Course("BITP3123", "Distributed Application Development", 10));
            courseRepository.save(new Course("BITP3113", "Software Engineering", 60));
        }
    }

    // ==========================================
    // REQUIREMENT FUNCTION: READ/CAPACITY CHECKS
    // ==========================================
    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // ==========================================
    // REQUIREMENT FUNCTION: ADD COURSE UTILITY
    // ==========================================
    @PostMapping("/courses")
    public ResponseEntity<String> addNewCourse(@RequestBody Course newCourse) {
        if (courseRepository.existsById(newCourse.getCourseCode())) {
            return new ResponseEntity<>("Course code already exists!", HttpStatus.BAD_REQUEST);
        }
        courseRepository.save(newCourse);
        return new ResponseEntity<>("Course " + newCourse.getCourseCode() + " added successfully.", HttpStatus.CREATED);
    }

    // ==========================================
    // REQUIREMENT FUNCTION: SEMESTER ENROLMENT
    // ==========================================
    @PostMapping("/enrol")
    public ResponseEntity<String> enrolStudent(@RequestBody EnrolmentRequest request) {
        String studentId = request.getStudentId();
        String courseCode = request.getCourseCode();

        Course course = courseRepository.findById(courseCode).orElse(null);
        if (course == null) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        // Capacity check validation
        if (course.getEnrolledCount() >= course.getCapacity()) {
            return new ResponseEntity<>("Course is full!", HttpStatus.BAD_REQUEST);
        }

        String studentServiceUrl = "http://localhost:8080/api/students/" + studentId;
        
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(studentServiceUrl, Object.class);
            if (response.getStatusCode() == HttpStatus.OK) {
            	 course.setEnrolledCount(course.getEnrolledCount() + 1);
                 courseRepository.save(course); 
                 
                 // 🛠️ ADD ONLY THIS BLOCK FOR NOTIFICATION
                 try {
                     Map<String, String> noti = new HashMap<>();
                     noti.put("studentId", studentId);
                     noti.put("alertType", "Course Enrolment");
                     noti.put("message", "Successfully enrolled in " + courseCode + " - " + course.getCourseName());
                     
                     restTemplate.postForEntity("http://localhost:8083/api/notifications", noti, String.class);
                 } catch (Exception e) {
                     System.out.println("Notification server down, but enrolment succeeded.");
                 }
                
                return new ResponseEntity<>("Enrolment successful for student " + studentId + " in " + courseCode, HttpStatus.CREATED);
            }
        } catch (HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>("Enrolment rejected: Student ID does not exist.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Student Service is currently unavailable.", HttpStatus.SERVICE_UNAVAILABLE);
        }

        return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // ==========================================
    // REQUIREMENT FUNCTION: DROP COURSE
    // ==========================================
    @PostMapping("/drop")
    public ResponseEntity<String> dropCourse(@RequestBody EnrolmentRequest request) {
        String studentId = request.getStudentId();
        String courseCode = request.getCourseCode();

        Course course = courseRepository.findById(courseCode).orElse(null);
        if (course == null) {
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }

        // Check if there's actually anyone registered to drop
        if (course.getEnrolledCount() <= 0) {
            return new ResponseEntity<>("Cannot drop: No students are currently registered in this course.", HttpStatus.BAD_REQUEST);
        }

        String studentServiceUrl = "http://localhost:8080/api/students/" + studentId;
        
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(studentServiceUrl, Object.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                // Decrement seat count safely
                course.setEnrolledCount(course.getEnrolledCount() - 1);
                courseRepository.save(course);
                
                // 🛠️ ADD ONLY THIS BLOCK FOR NOTIFICATION
                try {
                    Map<String, String> noti = new HashMap<>();
                    noti.put("studentId", studentId);
                    noti.put("alertType", "Course Enrolment");
                    noti.put("message", "Successfully dropped course: " + courseCode);
                    
                    restTemplate.postForEntity("http://localhost:8083/api/notifications", noti, String.class);
                } catch (Exception e) {
                    System.out.println("Notification server down, but drop transaction completed.");
                }
                
                return new ResponseEntity<>("Course " + courseCode + " dropped successfully for student " + studentId, HttpStatus.OK);
            }
        } catch (HttpClientErrorException.NotFound e) {
            return new ResponseEntity<>("Drop rejected: Student ID does not exist.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Student Service is currently unavailable.", HttpStatus.SERVICE_UNAVAILABLE);
        }

        return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
package com.smartcampus.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "*") // Allows your HTML UI to talk to this backend
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository; // 👈 Connects directly to your database driver

    // 1. CREATE a new Student Profile in H2
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        // Save the student object directly into the H2 database table
        Student savedStudent = studentRepository.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // 2. GET a Student Profile by ID from H2
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(name = "id") Long id) {
        Optional<Student> student = studentRepository.findById(id);
        
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 3. GET all Student Profiles from H2
    @GetMapping
    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
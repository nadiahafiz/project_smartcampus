package com.smartcampus.student;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final ConcurrentHashMap<String, Student> studentDatabase = new ConcurrentHashMap<>();

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        if (student.getId() == null || studentDatabase.containsKey(student.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        studentDatabase.put(student.getId(), student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(name = "id") String id) {
        Student student = studentDatabase.get(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping
    public Collection<Student> getAllStudents() {
        return studentDatabase.values();
    }
}
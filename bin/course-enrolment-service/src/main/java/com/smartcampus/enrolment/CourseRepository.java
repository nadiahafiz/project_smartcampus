package com.smartcampus.enrolment;

import com.smartcampus.enrolment.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    // This gives you ready-to-use database methods like save(), findAll(), and findById()!
}
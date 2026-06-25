package com.smartcampus.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 👈 This gives you access to built-in methods like .save() and .findAll() without writing SQL!
public interface StudentRepository extends JpaRepository<Student, Long> {
}
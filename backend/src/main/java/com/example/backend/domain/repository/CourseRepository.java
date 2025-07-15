package com.example.backend.domain.repository;

import com.example.backend.domain.model.Course;
import java.util.Optional;

public interface CourseRepository {
    Course save(Course course);
    Optional<Course> findById(Long id);
    void deleteById(Long id);
}

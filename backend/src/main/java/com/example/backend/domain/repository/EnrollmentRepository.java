package com.example.backend.domain.repository;

import com.example.backend.domain.model.Enrollment;
import java.util.Optional;

public interface EnrollmentRepository {
    Enrollment save(Enrollment enrollment);
    Optional<Enrollment> findById(Long id);
}

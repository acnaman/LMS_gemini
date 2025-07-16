package com.example.backend.domain.repository;

import com.example.backend.domain.model.LearningHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LearningHistoryRepository extends JpaRepository<LearningHistory, Long> {
    List<LearningHistory> findByUserId(Long userId);
}

package com.example.backend.domain.repository;

import com.example.backend.domain.model.LearningHistory;
import java.util.List;
import java.util.Optional;

public interface LearningHistoryRepository {
    LearningHistory save(LearningHistory learningHistory);
    Optional<LearningHistory> findById(Long id);
    List<LearningHistory> findByUserId(Long userId);
}

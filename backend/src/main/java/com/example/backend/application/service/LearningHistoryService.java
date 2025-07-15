package com.example.backend.application.service;

import com.example.backend.domain.model.LearningHistory;
import com.example.backend.domain.repository.LearningHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearningHistoryService {

    private final LearningHistoryRepository learningHistoryRepository;

    public LearningHistoryService(LearningHistoryRepository learningHistoryRepository) {
        this.learningHistoryRepository = learningHistoryRepository;
    }

    public LearningHistory createLearningHistory(LearningHistory learningHistory) {
        return learningHistoryRepository.save(learningHistory);
    }

    public Optional<LearningHistory> getLearningHistoryById(Long id) {
        return learningHistoryRepository.findById(id);
    }

    public List<LearningHistory> getLearningHistoriesByUserId(Long userId) {
        return learningHistoryRepository.findByUserId(userId);
    }
}

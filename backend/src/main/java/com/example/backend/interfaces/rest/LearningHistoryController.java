package com.example.backend.interfaces.rest;

import com.example.backend.application.service.LearningHistoryService;
import com.example.backend.domain.model.LearningHistory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/learning-histories")
public class LearningHistoryController {

    private final LearningHistoryService learningHistoryService;

    public LearningHistoryController(LearningHistoryService learningHistoryService) {
        this.learningHistoryService = learningHistoryService;
    }

    @PostMapping
    public LearningHistory createLearningHistory(@RequestBody LearningHistory learningHistory) {
        return learningHistoryService.createLearningHistory(learningHistory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LearningHistory> getLearningHistoryById(@PathVariable Long id) {
        Optional<LearningHistory> learningHistory = learningHistoryService.getLearningHistoryById(id);
        return learningHistory.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<LearningHistory> getLearningHistoriesByUserId(@PathVariable Long userId) {
        return learningHistoryService.getLearningHistoriesByUserId(userId);
    }
}

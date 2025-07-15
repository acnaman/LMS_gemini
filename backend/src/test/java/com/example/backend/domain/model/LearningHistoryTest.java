package com.example.backend.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class LearningHistoryTest {
    @Test
    void testLearningHistoryCreation() {
        User user = new User(1L, "testuser", Role.USER);
        Course course = new Course(1L, "Test Course", "This is a test course.");
        LocalDate completionDate = LocalDate.now();
        LearningHistory learningHistory = new LearningHistory(1L, user, course, completionDate);

        assertEquals(1L, learningHistory.getId());
        assertEquals(user, learningHistory.getUser());
        assertEquals(course, learningHistory.getCourse());
        assertEquals(completionDate, learningHistory.getCompletionDate());
    }
}

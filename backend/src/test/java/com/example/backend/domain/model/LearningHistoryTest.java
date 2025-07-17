package com.example.backend.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class LearningHistoryTest {
    @Test
    void testLearningHistoryCreation() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setRole(Role.USER);

        Course course = new Course();
        course.setId(1L);
        course.setTitle("Test Course");
        course.setDescription("This is a test course.");

        LocalDate completionDate = LocalDate.now();
        LearningHistory learningHistory = new LearningHistory();
        learningHistory.setId(1L);
        learningHistory.setUser(user);
        learningHistory.setCourse(course);
        learningHistory.setCompletionDate(completionDate);

        assertEquals(1L, learningHistory.getId());
        assertEquals(user, learningHistory.getUser());
        assertEquals(course, learningHistory.getCourse());
        assertEquals(completionDate, learningHistory.getCompletionDate());
    }
}

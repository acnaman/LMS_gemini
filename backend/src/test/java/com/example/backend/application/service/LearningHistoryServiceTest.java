package com.example.backend.application.service;

import com.example.backend.domain.model.Course;
import com.example.backend.domain.model.LearningHistory;
import com.example.backend.domain.model.Role;
import com.example.backend.domain.model.User;
import com.example.backend.domain.repository.LearningHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LearningHistoryServiceTest {

    @Mock
    private LearningHistoryRepository learningHistoryRepository;

    @InjectMocks
    private LearningHistoryService learningHistoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createLearningHistory() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setRole(Role.USER);

        Course course = new Course();
        course.setId(1L);
        course.setTitle("Test Course");
        course.setDescription("Description");

        LocalDate completionDate = LocalDate.now();
        LearningHistory learningHistory = new LearningHistory();
        learningHistory.setId(1L);
        learningHistory.setUser(user);
        learningHistory.setCourse(course);
        learningHistory.setCompletionDate(completionDate);

        when(learningHistoryRepository.save(any(LearningHistory.class))).thenReturn(learningHistory);

        LearningHistory createdLearningHistory = learningHistoryService.createLearningHistory(learningHistory);

        assertNotNull(createdLearningHistory);
        assertEquals(1L, createdLearningHistory.getId());
        assertEquals(user, createdLearningHistory.getUser());
        assertEquals(course, createdLearningHistory.getCourse());
        assertEquals(completionDate, createdLearningHistory.getCompletionDate());
        verify(learningHistoryRepository, times(1)).save(learningHistory);
    }

    @Test
    void getLearningHistoryById_found() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setRole(Role.USER);

        Course course = new Course();
        course.setId(1L);
        course.setTitle("Test Course");
        course.setDescription("Description");

        LocalDate completionDate = LocalDate.now();
        LearningHistory learningHistory = new LearningHistory();
        learningHistory.setId(1L);
        learningHistory.setUser(user);
        learningHistory.setCourse(course);
        learningHistory.setCompletionDate(completionDate);

        when(learningHistoryRepository.findById(1L)).thenReturn(Optional.of(learningHistory));

        Optional<LearningHistory> foundLearningHistory = learningHistoryService.getLearningHistoryById(1L);

        assertTrue(foundLearningHistory.isPresent());
        assertEquals(1L, foundLearningHistory.get().getId());
        verify(learningHistoryRepository, times(1)).findById(1L);
    }

    @Test
    void getLearningHistoryById_notFound() {
        when(learningHistoryRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<LearningHistory> foundLearningHistory = learningHistoryService.getLearningHistoryById(1L);

        assertFalse(foundLearningHistory.isPresent());
        verify(learningHistoryRepository, times(1)).findById(1L);
    }

    @Test
    void getLearningHistoriesByUserId() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setRole(Role.USER);

        Course course1 = new Course();
        course1.setId(1L);
        course1.setTitle("Course 1");
        course1.setDescription("Desc 1");

        Course course2 = new Course();
        course2.setId(2L);
        course2.setTitle("Course 2");
        course2.setDescription("Desc 2");

        LocalDate completionDate1 = LocalDate.now();
        LocalDate completionDate2 = LocalDate.now().minusDays(1);

        LearningHistory lh1 = new LearningHistory();
        lh1.setId(1L);
        lh1.setUser(user);
        lh1.setCourse(course1);
        lh1.setCompletionDate(completionDate1);

        LearningHistory lh2 = new LearningHistory();
        lh2.setId(2L);
        lh2.setUser(user);
        lh2.setCourse(course2);
        lh2.setCompletionDate(completionDate2);

        List<LearningHistory> learningHistories = Arrays.asList(lh1, lh2);

        when(learningHistoryRepository.findByUserId(1L)).thenReturn(learningHistories);

        List<LearningHistory> foundLearningHistories = learningHistoryService.getLearningHistoriesByUserId(1L);

        assertNotNull(foundLearningHistories);
        assertEquals(2, foundLearningHistories.size());
        verify(learningHistoryRepository, times(1)).findByUserId(1L);
    }
}

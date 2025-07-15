package com.example.backend.interfaces.rest;

import com.example.backend.application.service.LearningHistoryService;
import com.example.backend.domain.model.Course;
import com.example.backend.domain.model.LearningHistory;
import com.example.backend.domain.model.Role;
import com.example.backend.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LearningHistoryController.class)
class LearningHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LearningHistoryService learningHistoryService;

    @Test
    void testCreateLearningHistory() throws Exception {
        User user = new User(1L, "testuser", Role.USER);
        Course course = new Course(1L, "Test Course", "Description");
        LocalDate completionDate = LocalDate.now();
        LearningHistory learningHistory = new LearningHistory(1L, user, course, completionDate);

        when(learningHistoryService.createLearningHistory(any(LearningHistory.class))).thenReturn(learningHistory);

        mockMvc.perform(post("/api/learning-histories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":1,\"courseId\":1,\"completionDate\":\"" + completionDate.toString() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.user.id").value(1L))
                .andExpect(jsonPath("$.course.id").value(1L))
                .andExpect(jsonPath("$.completionDate").value(completionDate.toString()));
    }

    @Test
    void testGetLearningHistoryById() throws Exception {
        User user = new User(1L, "testuser", Role.USER);
        Course course = new Course(1L, "Test Course", "Description");
        LocalDate completionDate = LocalDate.now();
        LearningHistory learningHistory = new LearningHistory(1L, user, course, completionDate);

        when(learningHistoryService.getLearningHistoryById(1L)).thenReturn(Optional.of(learningHistory));

        mockMvc.perform(get("/api/learning-histories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.user.id").value(1L))
                .andExpect(jsonPath("$.course.id").value(1L))
                .andExpect(jsonPath("$.completionDate").value(completionDate.toString()));
    }

    @Test
    void testGetLearningHistoriesByUserId() throws Exception {
        User user = new User(1L, "testuser", Role.USER);
        Course course1 = new Course(1L, "Course 1", "Desc 1");
        Course course2 = new Course(2L, "Course 2", "Desc 2");
        LocalDate completionDate1 = LocalDate.now();
        LocalDate completionDate2 = LocalDate.now().minusDays(1);

        LearningHistory lh1 = new LearningHistory(1L, user, course1, completionDate1);
        LearningHistory lh2 = new LearningHistory(2L, user, course2, completionDate2);
        List<LearningHistory> learningHistories = Arrays.asList(lh1, lh2);

        when(learningHistoryService.getLearningHistoriesByUserId(1L)).thenReturn(learningHistories);

        mockMvc.perform(get("/api/learning-histories/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].user.id").value(1L))
                .andExpect(jsonPath("$[0].course.id").value(1L))
                .andExpect(jsonPath("$[0].completionDate").value(completionDate1.toString()))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].user.id").value(1L))
                .andExpect(jsonPath("$[1].course.id").value(2L))
                .andExpect(jsonPath("$[1].completionDate").value(completionDate2.toString()));
    }
}

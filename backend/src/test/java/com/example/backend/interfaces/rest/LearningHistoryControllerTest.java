package com.example.backend.interfaces.rest;

import com.example.backend.application.service.LearningHistoryService;
import com.example.backend.domain.model.Course;
import com.example.backend.domain.model.LearningHistory;
import com.example.backend.domain.model.Role;
import com.example.backend.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LearningHistoryController.class)
@WithMockUser(roles = "USER") // USERロールを持つモックユーザーとしてテストを実行
class LearningHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private LearningHistoryService learningHistoryService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void testCreateLearningHistory() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password"); // Add password
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

        when(learningHistoryService.createLearningHistory(any(LearningHistory.class))).thenReturn(learningHistory);

        mockMvc.perform(post("/api/learning-histories").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":1,\"courseId\":1,\"completionDate\":\"" + completionDate.toString() + "\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.user.id").value(1L))
                .andExpect(jsonPath("$.user.username").value("testuser")) // Add username check
                .andExpect(jsonPath("$.user.role").value("USER")) // Add role check
                .andExpect(jsonPath("$.course.id").value(1L))
                .andExpect(jsonPath("$.completionDate").value(completionDate.toString()));
    }

    @Test
    void testGetLearningHistoryById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password"); // Add password
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

        when(learningHistoryService.getLearningHistoryById(1L)).thenReturn(Optional.of(learningHistory));

        mockMvc.perform(get("/api/learning-histories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.user.id").value(1L))
                .andExpect(jsonPath("$.user.username").value("testuser")) // Add username check
                .andExpect(jsonPath("$.user.role").value("USER")) // Add role check
                .andExpect(jsonPath("$.course.id").value(1L))
                .andExpect(jsonPath("$.completionDate").value(completionDate.toString()));
    }

    @Test
    void testGetLearningHistoriesByUserId() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password"); // Add password
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

        when(learningHistoryService.getLearningHistoriesByUserId(1L)).thenReturn(learningHistories);

        mockMvc.perform(get("/api/learning-histories/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].user.id").value(1L))
                .andExpect(jsonPath("$[0].user.username").value("testuser")) // Add username check
                .andExpect(jsonPath("$[0].user.role").value("USER")) // Add role check
                .andExpect(jsonPath("$[0].course.id").value(1L))
                .andExpect(jsonPath("$[0].completionDate").value(completionDate1.toString()))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].user.id").value(1L))
                .andExpect(jsonPath("$[1].user.username").value("testuser")) // Add username check
                .andExpect(jsonPath("$[1].user.role").value("USER")) // Add role check
                .andExpect(jsonPath("$[1].course.id").value(2L))
                .andExpect(jsonPath("$[1].completionDate").value(completionDate2.toString()));
    }
}

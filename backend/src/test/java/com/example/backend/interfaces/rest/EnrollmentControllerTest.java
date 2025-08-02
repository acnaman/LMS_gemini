package com.example.backend.interfaces.rest;

import com.example.backend.application.service.EnrollmentService;
import com.example.backend.domain.model.Course;
import com.example.backend.domain.model.Enrollment;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("deprecation")
@WebMvcTest(EnrollmentController.class)
@WithMockUser(roles = "USER") // USERロールを持つモックユーザーとしてテストを実行
class EnrollmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private EnrollmentService enrollmentService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void testCreateEnrollment() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setRole(Role.USER);

        Course course = new Course();
        course.setId(1L);
        course.setTitle("Test Course");
        course.setDescription("Description");

        Enrollment enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setUser(user);
        enrollment.setCourse(course);

        when(enrollmentService.createEnrollment(any(Enrollment.class))).thenReturn(enrollment);

        mockMvc.perform(post("/api/enrollments").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":1,\"courseId\":1}")) // Simplified content for test
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.user.id").value(1L))
                .andExpect(jsonPath("$.user.username").value("testuser")) // Add username check
                .andExpect(jsonPath("$.user.role").value("USER")) // Add role check
                .andExpect(jsonPath("$.course.id").value(1L));
    }

    @Test
    void testGetEnrollmentById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setRole(Role.USER);

        Course course = new Course();
        course.setId(1L);
        course.setTitle("Test Course");
        course.setDescription("Description");

        Enrollment enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setUser(user);
        enrollment.setCourse(course);

        when(enrollmentService.getEnrollmentById(1L)).thenReturn(java.util.Optional.of(enrollment));

        mockMvc.perform(get("/api/enrollments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.user.id").value(1L))
                .andExpect(jsonPath("$.user.username").value("testuser")) // Add username check
                .andExpect(jsonPath("$.user.role").value("USER")) // Add role check
                .andExpect(jsonPath("$.course.id").value(1L));
    }
}
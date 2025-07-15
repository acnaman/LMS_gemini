package com.example.backend.interfaces.rest;

import com.example.backend.application.service.CourseService;
import com.example.backend.domain.model.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Test
    void testCreateCourse() throws Exception {
        Course course = new Course(1L, "Test Course", "Description of Test Course");
        when(courseService.createCourse(any(Course.class))).thenReturn(course);

        mockMvc.perform(post("/api/admin/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Test Course\",\"description\":\"Description of Test Course\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Course"))
                .andExpect(jsonPath("$.description").value("Description of Test Course"));
    }

    @Test
    void testGetCourseById() throws Exception {
        Course course = new Course(1L, "Test Course", "Description of Test Course");
        when(courseService.getCourseById(1L)).thenReturn(java.util.Optional.of(course));

        mockMvc.perform(get("/api/admin/courses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Course"))
                .andExpect(jsonPath("$.description").value("Description of Test Course"));
    }

    @Test
    void testUpdateCourse() throws Exception {
        Course updatedCourse = new Course(1L, "Updated Course", "Updated Description");
        when(courseService.updateCourse(any(Course.class))).thenReturn(updatedCourse);

        mockMvc.perform(put("/api/admin/courses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"title\":\"Updated Course\",\"description\":\"Updated Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Updated Course"))
                .andExpect(jsonPath("$.description").value("Updated Description"));
    }

    @Test
    void testDeleteCourse() throws Exception {
        doNothing().when(courseService).deleteCourse(1L);

        mockMvc.perform(delete("/api/admin/courses/1"))
                .andExpect(status().isNoContent());
    }
}

package com.example.backend.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CourseTest {
    @Test
    void testCourseCreation() {
        Course course = new Course(1L, "Test Course", "This is a test course.");
        assertEquals(1L, course.getId());
        assertEquals("Test Course", course.getTitle());
        assertEquals("This is a test course.", course.getDescription());
    }
}

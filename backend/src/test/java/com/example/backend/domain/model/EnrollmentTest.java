package com.example.backend.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EnrollmentTest {
    @Test
    void testEnrollmentCreation() {
        User user = new User(1L, "testuser", Role.USER);
        Course course = new Course(1L, "Test Course", "This is a test course.");
        Enrollment enrollment = new Enrollment(1L, user, course);

        assertEquals(1L, enrollment.getId());
        assertEquals(user, enrollment.getUser());
        assertEquals(course, enrollment.getCourse());
    }
}

package com.example.backend.application.service;

import com.example.backend.domain.model.Course;
import com.example.backend.domain.model.Enrollment;
import com.example.backend.domain.model.Role;
import com.example.backend.domain.model.User;
import com.example.backend.domain.repository.EnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEnrollment() {
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

        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(enrollment);

        Enrollment createdEnrollment = enrollmentService.createEnrollment(enrollment);

        assertNotNull(createdEnrollment);
        assertEquals(1L, createdEnrollment.getId());
        assertEquals(user, createdEnrollment.getUser());
        assertEquals(course, createdEnrollment.getCourse());
        verify(enrollmentRepository, times(1)).save(enrollment);
    }

    @Test
    void getEnrollmentById_found() {
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

        when(enrollmentRepository.findById(1L)).thenReturn(Optional.of(enrollment));

        Optional<Enrollment> foundEnrollment = enrollmentService.getEnrollmentById(1L);

        assertTrue(foundEnrollment.isPresent());
        assertEquals(1L, foundEnrollment.get().getId());
        verify(enrollmentRepository, times(1)).findById(1L);
    }

    @Test
    void getEnrollmentById_notFound() {
        when(enrollmentRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Enrollment> foundEnrollment = enrollmentService.getEnrollmentById(1L);

        assertFalse(foundEnrollment.isPresent());
        verify(enrollmentRepository, times(1)).findById(1L);
    }

    @Test
    void getAllEnrollments() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("testuser1");
        user1.setRole(Role.USER);

        Course course1 = new Course();
        course1.setId(1L);
        course1.setTitle("Course 1");
        course1.setDescription("Desc 1");

        Enrollment enrollment1 = new Enrollment();
        enrollment1.setId(1L);
        enrollment1.setUser(user1);
        enrollment1.setCourse(course1);

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("testuser2");
        user2.setRole(Role.ADMIN);

        Course course2 = new Course();
        course2.setId(2L);
        course2.setTitle("Course 2");
        course2.setDescription("Desc 2");

        Enrollment enrollment2 = new Enrollment();
        enrollment2.setId(2L);
        enrollment2.setUser(user2);
        enrollment2.setCourse(course2);

        List<Enrollment> enrollments = Arrays.asList(enrollment1, enrollment2);

        when(enrollmentRepository.findAll()).thenReturn(enrollments);

        List<Enrollment> foundEnrollments = enrollmentService.getAllEnrollments();

        assertNotNull(foundEnrollments);
        assertEquals(2, foundEnrollments.size());
        verify(enrollmentRepository, times(1)).findAll();
    }
}

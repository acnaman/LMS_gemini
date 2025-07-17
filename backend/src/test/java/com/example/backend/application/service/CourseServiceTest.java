package com.example.backend.application.service;

import com.example.backend.domain.model.Course;
import com.example.backend.domain.repository.CourseRepository;
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

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCourse() {
        Course course = new Course();
        course.setId(1L);
        course.setTitle("Test Course");
        course.setDescription("Description");

        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course createdCourse = courseService.createCourse(course);

        assertNotNull(createdCourse);
        assertEquals(1L, createdCourse.getId());
        assertEquals("Test Course", createdCourse.getTitle());
        assertEquals("Description", createdCourse.getDescription());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void getCourseById_found() {
        Course course = new Course();
        course.setId(1L);
        course.setTitle("Test Course");
        course.setDescription("Description");

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Optional<Course> foundCourse = courseService.getCourseById(1L);

        assertTrue(foundCourse.isPresent());
        assertEquals(1L, foundCourse.get().getId());
        verify(courseRepository, times(1)).findById(1L);
    }

    @Test
    void getCourseById_notFound() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Course> foundCourse = courseService.getCourseById(1L);

        assertFalse(foundCourse.isPresent());
        verify(courseRepository, times(1)).findById(1L);
    }

    @Test
    void getAllCourses() {
        Course course1 = new Course();
        course1.setId(1L);
        course1.setTitle("Course 1");
        course1.setDescription("Desc 1");

        Course course2 = new Course();
        course2.setId(2L);
        course2.setTitle("Course 2");
        course2.setDescription("Desc 2");

        List<Course> courses = Arrays.asList(course1, course2);

        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> foundCourses = courseService.getAllCourses();

        assertNotNull(foundCourses);
        assertEquals(2, foundCourses.size());
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void updateCourse() {
        Course course = new Course();
        course.setId(1L);
        course.setTitle("Updated Course");
        course.setDescription("Updated Description");

        when(courseRepository.save(any(Course.class))).thenReturn(course);

        Course updatedCourse = courseService.updateCourse(course);

        assertNotNull(updatedCourse);
        assertEquals(1L, updatedCourse.getId());
        assertEquals("Updated Course", updatedCourse.getTitle());
        assertEquals("Updated Description", updatedCourse.getDescription());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void deleteCourse() {
        doNothing().when(courseRepository).deleteById(1L);

        courseService.deleteCourse(1L);

        verify(courseRepository, times(1)).deleteById(1L);
    }
}

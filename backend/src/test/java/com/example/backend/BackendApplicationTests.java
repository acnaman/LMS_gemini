package com.example.backend;

import com.example.backend.domain.repository.UserRepository;
import com.example.backend.domain.repository.CourseRepository;
import com.example.backend.domain.repository.EnrollmentRepository;
import com.example.backend.domain.repository.LearningHistoryRepository; // Add this import
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class BackendApplicationTests {

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private CourseRepository courseRepository;

	@MockBean
	private EnrollmentRepository enrollmentRepository;

	@MockBean
	private LearningHistoryRepository learningHistoryRepository; // Add this line

	@Test
	void contextLoads() {
	}

}

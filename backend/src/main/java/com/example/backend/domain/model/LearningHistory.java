package com.example.backend.domain.model;

import java.time.LocalDate;

public class LearningHistory {
    private Long id;
    private User user;
    private Course course;
    private LocalDate completionDate;

    public LearningHistory(Long id, User user, Course course, LocalDate completionDate) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.completionDate = completionDate;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }
}

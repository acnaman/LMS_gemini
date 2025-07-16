package com.example.backend.domain.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class LearningHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDate completionDate;

    public LearningHistory(Long id, User user, Course course, LocalDate completionDate) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.completionDate = completionDate;
    }

    protected LearningHistory() { // JPAのために必要
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

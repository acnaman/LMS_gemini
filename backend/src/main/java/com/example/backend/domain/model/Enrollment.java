package com.example.backend.domain.model;

public class Enrollment {
    private Long id;
    private User user;
    private Course course;

    public Enrollment(Long id, User user, Course course) {
        this.id = id;
        this.user = user;
        this.course = course;
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
}

package com.example.backend.domain.model;

public class User {
    private Long id;
    private String username;
    private Role role;

    public User(Long id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }
}

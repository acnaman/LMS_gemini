package com.example.backend.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    void testUserCreation() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setRole(Role.USER);
        assertEquals(1L, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals(Role.USER, user.getRole());
    }
}

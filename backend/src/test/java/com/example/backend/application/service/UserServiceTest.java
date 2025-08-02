package com.example.backend.application.service;

import com.example.backend.domain.model.User;
import com.example.backend.domain.model.Role;
import com.example.backend.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // passwordEncoder.encode()が呼び出されたときに、常に"hashedPassword"を返すように設定
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");
    }

    @Test
    void createUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("rawPassword");
        user.setRole(Role.USER);

        // saveメソッドが呼び出されたときに、引数のUserオブジェクトのパスワードがハッシュ化されていることを確認
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            assertEquals("hashedPassword", savedUser.getPassword());
            return savedUser;
        });

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(1L, createdUser.getId());
        assertEquals("testuser", createdUser.getUsername());
        assertEquals(Role.USER, createdUser.getRole());
        verify(passwordEncoder, times(1)).encode("rawPassword");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getUserById_found() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("hashedPassword");
        user.setRole(Role.USER);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserById(1L);

        assertTrue(foundUser.isPresent());
        assertEquals(1L, foundUser.get().getId());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUserById_notFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> foundUser = userService.getUserById(1L);

        assertFalse(foundUser.isPresent());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void findByUsername_found() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("hashedPassword");
        user.setRole(Role.USER);

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findByUsername("testuser");

        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void findByUsername_notFound() {
        when(userRepository.findByUsername("nonexistent")).thenReturn(Optional.empty());

        Optional<User> foundUser = userService.findByUsername("nonexistent");

        assertFalse(foundUser.isPresent());
        verify(userRepository, times(1)).findByUsername("nonexistent");
    }

    @Test
    void getAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("testuser1");
        user1.setPassword("hashedPassword1");
        user1.setRole(Role.USER);

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("testuser2");
        user2.setPassword("hashedPassword2");
        user2.setRole(Role.ADMIN);

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> foundUsers = userService.getAllUsers();

        assertNotNull(foundUsers);
        assertEquals(2, foundUsers.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("updateduser");
        user.setPassword("newRawPassword");
        user.setRole(Role.ADMIN);

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            assertEquals("hashedPassword", savedUser.getPassword());
            return savedUser;
        });

        User updatedUser = userService.updateUser(user);

        assertNotNull(updatedUser);
        assertEquals(1L, updatedUser.getId());
        assertEquals("updateduser", updatedUser.getUsername());
        assertEquals(Role.ADMIN, updatedUser.getRole());
        verify(passwordEncoder, times(1)).encode("newRawPassword");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void deleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}

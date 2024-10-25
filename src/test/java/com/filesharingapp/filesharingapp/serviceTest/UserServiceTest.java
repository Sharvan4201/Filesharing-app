package com.filesharingapp.filesharingapp.serviceTest;

import com.filesharingapp.filesharingapp.models.User;
import com.filesharingapp.filesharingapp.repositories.UserRepository;
import com.filesharingapp.filesharingapp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should get user successfully")
    public void shouldGetUserSuccessfully() {
        when(userRepository.findById(any())).thenReturn(Optional.of(new User()));

        User result = userService.getUser(1L);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Should fail to get user when user not found")
    public void shouldFailToGetUserWhenUserNotFound() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUser(1L));
    }

    @Test
    @DisplayName("Should get all users successfully")
    public void shouldGetAllUsersSuccessfully() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(new User(), new User()));

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Should get user by username successfully")
    public void shouldGetUserByUsernameSuccessfully() {
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(new User()));

        User result = userService.getUserByUsername("test");

        assertNotNull(result);
    }

    @Test
    @DisplayName("Should fail to get user by username when user not found")
    public void shouldFailToGetUserByUsernameWhenUserNotFound() {
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUserByUsername("test"));
    }
}
package com.example.user.service.service.impl;

import com.example.user.service.dto.UserDto;
import com.example.user.service.model.User;
import com.example.user.service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    public static final String TEST_EMAIL = "email@email.com";
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void getUserTest() {
        User user = createUser();
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(user));
        UserDto userDto = userService.getUser(TEST_EMAIL);

        assertEquals(userDto.getEmail(), user.getEmail());
    }

    private User createUser() {
        User user = new User();
        user.setFirstName("firstName");
        user.setEmail(TEST_EMAIL);
        return user;
    }
}
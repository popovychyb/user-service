package com.example.user.service.service;

import com.example.user.service.dto.UserDto;

public interface UserService {
    UserDto getUser(String email);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(String email, UserDto userDto);

    void deleteUser(String email);
}

package com.example.user.service.service.impl;

import com.example.user.service.dto.UserDto;
import com.example.user.service.exception.UserNotFoundException;
import com.example.user.service.model.User;
import com.example.user.service.repository.UserRepository;
import com.example.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUser(String email) {
        log.info("getUser by email {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("creating user in database {}", userDto);
        User user = mapUserDtoToUser(userDto);
        user = userRepository.save(user);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        log.info("updating User in database {}", userDto);
        User user = mapUserDtoToUser(userDto);
        if (!userRepository.existsByEmail(email)) {
            throw new UserNotFoundException();
        }
        user = userRepository.save(user);
        return mapUserToUserDto(user);
    }

    @Override
    public void deleteUser(String email) {
        log.info("deleting user by email {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    private User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}

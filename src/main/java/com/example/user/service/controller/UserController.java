package com.example.user.service.controller;

import com.example.user.service.dto.UserDto;
import com.example.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{email}")
    public UserDto getUser(@PathVariable String email) {
        return userService.getUser(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        log.info("Create user: {}", userDto);
        return userService.createUser(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{email}")
    public UserDto updateUser(@PathVariable String email, @RequestBody UserDto userDto) {
        return userService.updateUser(email, userDto);
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
         userService.deleteUser(email);
         return ResponseEntity.noContent().build();
    }
}

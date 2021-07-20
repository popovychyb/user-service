package com.example.user.service.api;

import com.example.user.service.controller.model.UserModel;
import com.example.user.service.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "User management API")
@RequestMapping("/api/v1/users")
public interface UserApi {

    @ApiOperation("Get user from database")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{email}")
    UserModel getUser(@PathVariable String email);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    UserModel createUser(@Valid @RequestBody UserDto userDto);

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{email}")
    UserModel updateUser(@PathVariable String email, @RequestBody UserDto userDto);

    @RequestMapping(value = "/{email}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUser(@PathVariable String email);
}

package com.example.user.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private String firstName;
    @NotNull(message = "should be not null")
    private String lastName;
    @NotNull
    @Email
    private String email;
    private String password;
    private String repeatPassword;
}

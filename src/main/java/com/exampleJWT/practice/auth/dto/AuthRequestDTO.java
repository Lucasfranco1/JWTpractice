package com.exampleJWT.practice.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthRequestDTO {
    private String username;
    private String password;
}

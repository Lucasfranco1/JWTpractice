package com.exampleJWT.practice.auth.service;

import com.exampleJWT.practice.auth.dto.UserDTO;
import com.exampleJWT.practice.entity.UserEntity;

public interface UserService {

    UserEntity saveUser(UserDTO userDTO);
}

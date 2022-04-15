
package com.exampleJWT.practice.auth.service;

import com.exampleJWT.practice.auth.dto.UserDTO;
import com.exampleJWT.practice.entity.UserEntity;
import com.exampleJWT.practice.enums.Roles;
import com.exampleJWT.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity saveUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setRole(Roles.USER);
        userEntity = this.userRepository.save(userEntity);

        return userEntity;
    }
}

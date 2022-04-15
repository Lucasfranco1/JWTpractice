package com.exampleJWT.practice.auth.controller;

import com.exampleJWT.practice.auth.dto.AuthRequestDTO;
import com.exampleJWT.practice.auth.dto.AuthResponseDTO;
import com.exampleJWT.practice.auth.dto.UserDTO;
import com.exampleJWT.practice.auth.service.JwtUtils;
import com.exampleJWT.practice.auth.service.UserDetailsCustomService;
import com.exampleJWT.practice.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserAuthenticationController {

    private UserDetailsCustomService userDetailsCustomService;
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtTokenUtil;

    public UserAuthenticationController(UserDetailsCustomService userDetailsCustomService, UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtTokenUtil) {
        this.userDetailsCustomService = userDetailsCustomService;
        this.userService=userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /*
    //SIGNUP
    Modification of the registration method so that it generates the token and there is an automatic login.
    Return jwt.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO>signup(@RequestBody @Valid UserDTO userDTO){
        userService.saveUser(userDTO);
        Authentication auth;
        auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        final String jwt = jwtTokenUtil.generateToken(auth);
        return ResponseEntity.ok(new AuthResponseDTO(jwt));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO>login(@RequestBody @Valid AuthRequestDTO authRequest){
        Authentication auth;
        auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        final String jwt = jwtTokenUtil.generateToken(auth);
        return ResponseEntity.ok(new AuthResponseDTO(jwt));
    }

}

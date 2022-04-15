package com.exampleJWT.practice.auth.service;

import com.exampleJWT.practice.entity.UserEntity;
import com.exampleJWT.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Transactional
@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity= userRepository.findByEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority>authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+userEntity.getRole()));

        return new org.springframework.security.core.userdetails
                .User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}

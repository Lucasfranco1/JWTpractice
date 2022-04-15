package com.exampleJWT.practice.seeder;

import com.exampleJWT.practice.entity.UserEntity;
import com.exampleJWT.practice.enums.Roles;
import com.exampleJWT.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        loadUser();

    }

    //create users if they don't exist
    private void loadUser() {
        if(userRepository.count() == 0){
            loadUserSeed();
        }
    }

    private void loadUserSeed() {

        //ADMIN
        userRepository.save(buildUser("Pedro","Papers","pepers@ngov.au","L12345678", Roles.ADMIN));
        userRepository.save(buildUser("Lucas","Franco","lucas@gmial.com","Lucas12345678", Roles.ADMIN));
        userRepository.save(buildUser("Raynor","Creese","rcreese2@blog.com","6r0Jfdkddd", Roles.ADMIN));
        userRepository.save(buildUser("Madel","Charteris","mcharteris3@themeforest.net","OGfiQ5zbxs", Roles.ADMIN ));
        userRepository.save(buildUser("Cedric","Meyrick","cmeyrick4@amazon.co.jp","2myWZWtb6Am", Roles.ADMIN ));
        userRepository.save(buildUser("Fitzgerald","Mersh","fmersh5@pcworld.com","D1PH35Kxds", Roles.ADMIN ));
        userRepository.save(buildUser("Johnath","Origan","jorigan5@storify.com","R2oxqEfElMln", Roles.ADMIN ));
        userRepository.save(buildUser("Donni","Extance","dextance6@wisc.edu","wTahWubXxx", Roles.ADMIN ));
        userRepository.save(buildUser("Corbie","Lapsley","clapsley7@microsoft.com","qKpql1Ounzwo", Roles.ADMIN ));
        userRepository.save(buildUser("Leisha","Belin","lbelin8@pcworld.com","w77p77wGSs", Roles.ADMIN ));

        //USER
        userRepository.save(buildUser("Mirtha","Fossord","mirta@mirta.com","Mirta12345678", Roles.USER ));
        userRepository.save(buildUser("Cristian","Inkpen","cinkpen0@joomla.org","4VHcXxp86sB", Roles.USER ));
        userRepository.save(buildUser("Cristiano","Foston","cfoston1@japanpost.jp","9wf9KkPkxsx", Roles.USER ));
        userRepository.save(buildUser("Odele","Clynmans","oclynmans2@bravesites.com","DTUMZGlDjxdd", Roles.USER ));
        userRepository.save(buildUser("Will","MacRonald","wmacronald3@jalbum.net","KW1LpoXehdo", Roles.USER ));
        userRepository.save(buildUser("Skylar","Larter","slarter5@ovh.net","olpArkfRD2", Roles.USER));
        userRepository.save(buildUser("Heidi","Saffill","hsaffill6@huffingtonpost.com","DZfgtHxxxs", Roles.USER));
        userRepository.save(buildUser("Sharity","Penreth","spenreth7@linkedin.com","7QWax89xxs", Roles.USER));
        userRepository.save(buildUser("Yasmin","Lapsley","yjoicey8@theglobeandmail.com","r79Sfnpxxsx", Roles.USER));
        userRepository.save(buildUser("Charissa","MacGillreich","cmacgillreicha@sbwire.com","7MOQwvVOHz2p", Roles.USER));
    }

    private UserEntity buildUser(String firstName, String lastName, String email, String password, Roles role) {
        return new UserEntity(firstName,lastName,email,passwordEncoder.encode(password),role);
    }

}


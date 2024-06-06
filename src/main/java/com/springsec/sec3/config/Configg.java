package com.springsec.sec3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class Configg {

    @Bean //steps 1
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

 /*   @Bean //steps 2
    public UserDetailsService inmmyUserDet(){
        UserDetails user = User.builder()
                .username("user")
                .password(encoder().encode("user1"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder().encode("Admin1"))
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }*/
}

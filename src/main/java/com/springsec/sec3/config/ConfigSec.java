package com.springsec.sec3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfigSec  {


    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.authorizeRequests().requestMatchers("/home").permitAll()
                .and().authorizeRequests().requestMatchers("/secret").authenticated()
                .and().formLogin();

        return http.build();

    }

}

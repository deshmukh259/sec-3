//package com.springsec.sec3.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecCOnfig {
//
//    @Autowired
//    private JwtAuthEntryPoint entryPoint;
//
//    @Autowired
//    private JwtAuthFilter jwtAuthFilter;
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
//
//        security.csrf(cs->cs.disable())
//                .authorizeRequests().requestMatchers("/secret").authenticated()
//                .requestMatchers("/login").permitAll()
//                .anyRequest().authenticated()
//                .and().exceptionHandling(e-> e.authenticationEntryPoint(entryPoint))
//                .sessionManagement(se->se.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//         security.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//        return security.build();
//    }
//}

package com.springsec.sec3.config;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
//every req came here
    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private UserDetailsService userDetailsService;

    //steps 5
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String authorization = request.getHeader("Authorization");
        var v = "AbcdddddsdsXXXSASsdsdmsd";

        String username = null;
        String token = null;
        if (authorization != null && authorization.startsWith("Bearer")) {
            token = authorization.substring(7);
            System.out.println("token " + token);
            try {
                username = jwtManager.getUsername(token);
            } catch (IllegalArgumentException e) {
                System.out.println("1 " + e.getMessage());
            } catch (ExpiredJwtException e) {
                System.out.println("2 " + e.getMessage());
            } catch (Exception e) {
                System.out.println("3 " + e.getMessage());
            }
        } else {
            System.out.println("invalid auth");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Boolean validateToken = jwtManager.validateToken(token, userDetails);
            if (validateToken) {
                UsernamePasswordAuthenticationToken au = new UsernamePasswordAuthenticationToken(username, null,userDetails.getAuthorities());
                au.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(au);

            } else {
                System.out.println("validation fail");
            }
        }
        filterChain.doFilter(request, response);
    }
}

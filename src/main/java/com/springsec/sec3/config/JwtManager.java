package com.springsec.sec3.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtManager {

    public static long jwt_validity = 55 * 60 * 60*10;

    private String secret = "xpqdj3Lpx8zVFJ6eHxN77p0UKmwdUklUYl59vpGRRR5vmaMBQSQBVjtScyevvRtJCJccvcvcvcvccvcvcvcvcvcvcviF5Ohwb4OZ4agSAeoxAccxcnbxvncvnxbcvnbxcnbvcxnvbnxcnvcbnvnxcvbncnvbcnnnnnncvcvcvccvcvcvxcvcxcxcvcvcmvncvfjjdsfjdsjfdsjfjsdjfjdjdjjfjdfjcjvcjvcjvjcvjcvcjhcjjcjjcjcjcjjc";

    public String getUsername(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpiry(String token) {
        return getClaimFromToken(token,Claims::getExpiration);
    }


    private <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver) {
        Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){
        Date d = getExpiry(token);
        return d.before(new Date());
    }

    public String createToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return generateToekn(claims,userDetails.getUsername());

    }
    //steps 3
    private String generateToekn(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date()).setIssuer("my-service")
                .setExpiration(new Date(System.currentTimeMillis()+jwt_validity))
                .signWith(SignatureAlgorithm.HS512,secret).compact();
    }
    //steps 4
    public Boolean validateToken(String token, UserDetails userDetails){
        String user = getUsername(token);
        return (user.equals(userDetails.getUsername())
                && !isTokenExpired(token));

    }
}

package com.springsec.sec3.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class JwtResponse {

    private String token;
    private String username;

}

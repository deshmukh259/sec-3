package com.springsec.sec3.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class JwtRequest {

    private String username;
    private String password;
}

package com.springsec.sec3;

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

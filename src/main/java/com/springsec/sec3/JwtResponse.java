package com.springsec.sec3;

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

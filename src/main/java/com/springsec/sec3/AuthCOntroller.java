package com.springsec.sec3;

import com.springsec.sec3.config.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthCOntroller {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtManager jwtManager;

    @PostMapping(value = "/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest req){

        doAuthenticate(req.getUsername(),req.getPassword());

        UserDetails ud = userDetailsService.loadUserByUsername(req.getUsername());
        String token = jwtManager.createToken(ud);
        JwtResponse res = JwtResponse.builder().token(token).username(ud.getUsername()).build();
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username,password);

        try{
            manager.authenticate(auth);
        }catch (BadCredentialsException e){
            throw new RuntimeException("Invalid Username password");
        }

    }
}

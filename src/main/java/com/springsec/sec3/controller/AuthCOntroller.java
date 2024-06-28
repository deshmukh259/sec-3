package com.springsec.sec3.controller;


import com.springsec.sec3.UserInfoRepository;
import com.springsec.sec3.dto.JwtRequest;
import com.springsec.sec3.dto.JwtResponse;
import com.springsec.sec3.config.JwtManager;
import com.springsec.sec3.dto.UserDto;
import com.springsec.sec3.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UserInfoRepository userInfoRepository;

    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //steps 7
    @PostMapping(value = "/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest req) {

        doAuthenticate(req.getUsername(), req.getPassword());

        UserDetails ud = userDetailsService.loadUserByUsername(req.getUsername());
        String token = jwtManager.createToken(ud);
        JwtResponse res = JwtResponse.builder().token(token).username(ud.getUsername()).build();
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    //steps 6
    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password);

        try {
            manager.authenticate(auth);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid Username password");
        }

    }

    @PostMapping(value = "/adduser")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(userInfo.getEmail());
        userInfo.setName(userDto.getUsername());
        userInfo.setRoles(userDto.getRoles());

        userInfo.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userInfoRepository.save(userInfo);
        return ResponseEntity.ok("User Added Successfully");

    }
}

package com.springsec.sec3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class OneController {

    @GetMapping(value = "/home")
    public String home(){
        return "Welcome Home!!";
    }

    @GetMapping(value = "/secret")
    public String secret(){
        return "This is secret ....!!!!";
    }
}

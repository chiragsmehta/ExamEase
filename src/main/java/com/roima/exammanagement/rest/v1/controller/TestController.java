package com.roima.exammanagement.rest.v1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<String> sayHello(){
        return  new ResponseEntity<>("Hello", HttpStatus.OK);
    }
}

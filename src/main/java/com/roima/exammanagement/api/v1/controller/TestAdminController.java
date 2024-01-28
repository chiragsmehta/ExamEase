package com.roima.exammanagement.api.v1.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/admin")
public class TestAdminController {

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("Hello Admin", HttpStatus.OK);
    }
}

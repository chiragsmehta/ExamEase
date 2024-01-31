package com.roima.exammanagement.rest.v1.controller.admin;


import com.roima.exammanagement.model.McqQuestion;
import com.roima.exammanagement.rest.v1.dto.McqQuestionDTO;
import com.roima.exammanagement.rest.v1.service.McqQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mcq")
@RequiredArgsConstructor
public class McqQuestionController {
    private final McqQuestionService mcqQuestionService;


    @GetMapping("/all")
    public ResponseEntity<List<McqQuestionDTO>> findAllMcq(){
        return new ResponseEntity<>(mcqQuestionService.findAll(), HttpStatus.OK);
    }




}

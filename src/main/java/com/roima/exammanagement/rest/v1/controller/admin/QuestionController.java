package com.roima.exammanagement.rest.v1.controller.admin;


import com.roima.exammanagement.rest.v1.dto.QuestionDTO;
import com.roima.exammanagement.rest.v1.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;


    @GetMapping("/all")
    public ResponseEntity<List<QuestionDTO>> findAllMcqQuestion(){
        return new ResponseEntity<>(questionService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<QuestionDTO> createMcqQuestion(@RequestBody QuestionDTO questionDTO){
        try{
            return new ResponseEntity<>(questionService.save(questionDTO),HttpStatus.CREATED);
        }catch (Exception e){
//            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/{mcq_id}/assign_exam/{exam_id}")
    public ResponseEntity<Boolean> assignMcqQuestionToExam(@PathVariable("mcq_id") Long mcqId,@PathVariable("exam_id") Long examId){
        Boolean isAssigned = questionService.assignMcqQuestionToExamById(mcqId,examId);
        if(isAssigned){
            return new ResponseEntity<>(true,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
    }



}

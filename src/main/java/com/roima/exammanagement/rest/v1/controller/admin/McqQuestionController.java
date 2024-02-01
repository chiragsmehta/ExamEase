package com.roima.exammanagement.rest.v1.controller.admin;


import com.roima.exammanagement.rest.v1.dto.McqQuestionDTO;
import com.roima.exammanagement.rest.v1.service.McqQuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/mcq")
@RequiredArgsConstructor
public class McqQuestionController {
    private final McqQuestionService mcqQuestionService;


    @GetMapping("/all")
    public ResponseEntity<List<McqQuestionDTO>> findAllMcqQuestion(){
        return new ResponseEntity<>(mcqQuestionService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<McqQuestionDTO> createMcqQuestion(@RequestBody McqQuestionDTO mcqQuestionDTO){
        try{
            return new ResponseEntity<>(mcqQuestionService.save(mcqQuestionDTO),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/{mcq_id}/assign_exam/{exam_id}")
    public ResponseEntity<Boolean> assignMcqQuestionToExam(@PathVariable("mcq_id") Long mcqId,@PathVariable("exam_id") Long examId){
        Boolean isAssigned = mcqQuestionService.assignMcqQuestionToExamById(mcqId,examId);
        if(isAssigned){
            return new ResponseEntity<>(true,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
    }



}

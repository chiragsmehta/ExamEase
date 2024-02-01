package com.roima.exammanagement.rest.v1.controller.admin;


import com.roima.exammanagement.rest.v1.dto.ProgrammingQuestionDTO;
import com.roima.exammanagement.rest.v1.service.ProgrammingQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin/programming-question")
public class ProgrammingQuestionController {

    private final ProgrammingQuestionService programmingQuestionService;


    @GetMapping("/all")
    public ResponseEntity<List<ProgrammingQuestionDTO>> findAll(){
        try{
            return new ResponseEntity<>(programmingQuestionService.findAll(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public ResponseEntity<ProgrammingQuestionDTO> createProgrammingQuestion(@RequestBody ProgrammingQuestionDTO programmingQuestionDTO){
        try{
            return new ResponseEntity<>(programmingQuestionService.save(programmingQuestionDTO), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{programming_question_id}/assign_exam/{exam_id}")
    public ResponseEntity<Boolean> assignProgrammingQuestionToExam(
            @PathVariable("programming_question_id") Long programmingQuestionId,
            @PathVariable("exam_id") Long examId
            ){
        try{
            programmingQuestionService.addProgrammingQuestionToExamById(programmingQuestionId,examId);
            return new ResponseEntity<>(true,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
    }
}

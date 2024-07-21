package com.roima.exammanagement.rest.v1.controller.admin;


import com.roima.exammanagement.rest.v1.dto.QuestionDTO;
import com.roima.exammanagement.rest.v1.service.QuestionService;
import com.roima.exammanagement.rest.v1.service.QuestionsAddingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.BadAttributeValueExpException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/question")
@RequiredArgsConstructor
@CrossOrigin
public class QuestionController {
    private final QuestionService questionService;


    @GetMapping("/all")
    public ResponseEntity<List<QuestionDTO>> findAllQuestion(){
        return new ResponseEntity<>(questionService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<QuestionDTO> createMcqQuestion(@RequestBody QuestionDTO questionDTO){
        try{

            return new ResponseEntity<>(questionService.save(questionDTO),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("")
    public ResponseEntity<QuestionDTO> update(@RequestBody QuestionDTO questionDTO) throws BadAttributeValueExpException {
//        try{
//            System.out.println(questionDTO);
            return ResponseEntity.ok(questionService.update(questionDTO));
//        }catch (Exception e){
//            System.out.println(e.toString());
//            return  ResponseEntity.badRequest().build();
//        }
    }

    @DeleteMapping("/{mcq_id}")
    public  ResponseEntity<Boolean> deleteMcqById(@PathVariable("mcq_id") Long mcqId){
        try {
            return new ResponseEntity<>(questionService.deleteQuestionById(mcqId),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

package com.roima.exammanagement.rest.v1.controller.user;


import com.roima.exammanagement.rest.v1.dto.UserExamStatusDTO;
import com.roima.exammanagement.rest.v1.service.UserExamStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/exams/{exam_id}/user-exam-status")
@RequiredArgsConstructor
public class UserExamStatusController {
    private final UserExamStatusService userExamStatusService;
    @GetMapping("")
    public ResponseEntity<UserExamStatusDTO> getByExamId(@PathVariable("exam_id") Long examId){
       try{
           return ResponseEntity.ok(userExamStatusService.findByExamId(examId));
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
       }
    }

    @PutMapping("")
    public ResponseEntity<UserExamStatusDTO> submitExam(@RequestBody UserExamStatusDTO userExamStatusDTO){
        try{
            return  ResponseEntity.ok(userExamStatusService.submitExam(userExamStatusDTO));
        }catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }





}

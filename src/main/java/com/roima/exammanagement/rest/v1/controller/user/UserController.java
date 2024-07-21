package com.roima.exammanagement.rest.v1.controller.user;


import com.roima.exammanagement.rest.v1.dto.ExamDTO;
import com.roima.exammanagement.rest.v1.dto.UserDTO;
import com.roima.exammanagement.rest.v1.dto.UserExamStatusDTO;
import com.roima.exammanagement.rest.v1.dto.UserQuestionSubmissionDTO;
import com.roima.exammanagement.rest.v1.service.ExamService;
import com.roima.exammanagement.rest.v1.service.UserExamStatusService;
import com.roima.exammanagement.rest.v1.service.UserQuestionSubmissionService;
import com.roima.exammanagement.rest.v1.service.UserService;
import com.roima.exammanagement.rest.v1.service.utilitymodels.UserExamSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final ExamService examService;
    private final UserExamStatusService userExamStatusService;
    private final UserQuestionSubmissionService userQuestionSubmissionService;


    @GetMapping("")
    public ResponseEntity<UserDTO> getCurrentUser(){
        return new ResponseEntity<>(userService.getCurrentUser(), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<UserDTO> updateCurrentUser(@RequestBody UserDTO userDTO){
        try{
            UserDTO user = userService.update(userDTO);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }catch (Exception e){
//            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/exams")
    public ResponseEntity<List<ExamDTO>> getCurrentUserExams(){
        return ResponseEntity.ok(examService.findCurrentUserExams());
    }

    @PostMapping("/exams/{exam_id}/start-exam")
    public ResponseEntity<UserExamStatusDTO> startExam(@PathVariable("exam_id") Long examId){
        try{
            return  ResponseEntity.ok( userExamStatusService.startExam(examId));
        }catch (NoSuchElementException exception){
            return  ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/exams/update-exam-status")
    public  ResponseEntity<UserExamStatusDTO> updateExamStatus(@RequestBody UserExamStatusDTO userExamStatusDTO){
//        System.out.println(userExamStatusDTO);
        try{
            return ResponseEntity.ok( userExamStatusService.updateUserExamStatus(userExamStatusDTO));
        }catch (Exception e){
//            System.out.println(e.getMessage());
            return  ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/exams/submit-question")
    public ResponseEntity<Boolean> submitQuestion(@RequestBody UserQuestionSubmissionDTO userQuestionSubmissionDTO){
        try {
            return ResponseEntity.ok(examService.submitQuestion(userQuestionSubmissionDTO));
        }catch (Exception e){
//            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/exams/{exam_id}/submission/{question_id}")
    public  ResponseEntity<UserQuestionSubmissionDTO> getUserQuestionSubmission(@PathVariable("exam_id") Long examId,
             @PathVariable("question_id") Long questionId){
        try{
            return ResponseEntity.ok(userQuestionSubmissionService.findByExamAndQuestionId(examId,questionId));
        }catch (Exception e){
            return ResponseEntity.ok(null);
        }
    }

    @PutMapping("/exams/submit")
    public ResponseEntity<UserExamStatusDTO> submitExam(@RequestBody UserExamStatusDTO userExamStatusDTO){
        try{
            return  ResponseEntity.ok(userExamStatusService.submitExam(userExamStatusDTO));
        }catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("exams/{exam_id}/malpractice-found")
    public ResponseEntity<UserExamStatusDTO> registerMalPractice(@PathVariable("exam_id") Long examID){
        try{
            return  ResponseEntity.ok(userExamStatusService.registerMalPractice(examID));
        }catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("exam/{exam_id}/summary")
    public ResponseEntity<UserExamSummary> getUserExamSummary(@PathVariable("exam_id") Long examId){
        try{
            return ResponseEntity.ok(examService.getIndividualUserExamSummary(examId));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}

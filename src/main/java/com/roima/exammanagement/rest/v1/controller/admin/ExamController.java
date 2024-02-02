package com.roima.exammanagement.rest.v1.controller.admin;


import com.roima.exammanagement.rest.v1.dto.ExamDTO;
import com.roima.exammanagement.rest.v1.dto.QuestionDTO;
import com.roima.exammanagement.rest.v1.dto.UserDTO;
import com.roima.exammanagement.rest.v1.service.ExamService;
import com.roima.exammanagement.rest.v1.service.QuestionService;
import com.roima.exammanagement.rest.v1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;
    private final UserService userService;
    private final QuestionService questionService;
    @PostMapping("")
    public ResponseEntity<Boolean> saveExam(@RequestBody ExamDTO examDTO){
        return new ResponseEntity<>(examService.save(examDTO), HttpStatus.OK);
    }

    @PostMapping("/{exam_id}/assign_user/{user_id}")
    public ResponseEntity<Boolean> assignUserToExam(@PathVariable("exam_id") Long examId,@PathVariable("user_id") Long userId){
        return new ResponseEntity<>(examService.assignExamToUserById(userId,examId),HttpStatus.CREATED);
    }

    @GetMapping("/{exam_id}/users")
    public ResponseEntity<List<UserDTO>> findUserByExamId(@PathVariable("exam_id") Long examId){
        return new ResponseEntity<List<UserDTO>>(userService.findByExamId(examId),HttpStatus.OK);
    }

    @GetMapping("/{exam_id}/questions")
    public ResponseEntity<List<QuestionDTO>> findMcqQuestionsByExamId(@PathVariable("exam_id") Long examId){
        return new ResponseEntity<>(examService.findQuestionByExamId(examId),HttpStatus.OK);
    }
}

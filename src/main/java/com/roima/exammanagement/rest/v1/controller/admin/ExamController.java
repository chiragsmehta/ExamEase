package com.roima.exammanagement.rest.v1.controller.admin;


import com.roima.exammanagement.model.McqQuestion;
import com.roima.exammanagement.repository.ExamRepository;
import com.roima.exammanagement.rest.v1.dto.ExamDTO;
import com.roima.exammanagement.rest.v1.dto.McqQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.UserDTO;
import com.roima.exammanagement.rest.v1.service.ExamService;
import com.roima.exammanagement.rest.v1.service.McqQuestionService;
import com.roima.exammanagement.rest.v1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/exam")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;
    private final UserService userService;
    private final McqQuestionService mcqQuestionService;
    private static Logger logger = LoggerFactory.getLogger(ExamController.class);

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
    public ResponseEntity<List<McqQuestionDTO>> findMcqQuestionsByExamId(@PathVariable("exam_id") Long examId){
        return new ResponseEntity<>(examService.findMcqQuestionByExamId(examId),HttpStatus.OK);
    }
}

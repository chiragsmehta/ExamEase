package com.roima.exammanagement.rest.v1.controller.admin;


import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.repository.ExamRepository;
import com.roima.exammanagement.rest.v1.dto.ExamDTO;
import com.roima.exammanagement.rest.v1.dto.QuestionDTO;
import com.roima.exammanagement.rest.v1.dto.UserDTO;
import com.roima.exammanagement.rest.v1.mapper.ExamMapper;
import com.roima.exammanagement.rest.v1.service.ExamService;
import com.roima.exammanagement.rest.v1.service.QuestionService;
import com.roima.exammanagement.rest.v1.service.UserService;
import com.sipios.springsearch.SpecificationImpl;
import com.sipios.springsearch.anotation.SearchSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("")
    public ResponseEntity<List<ExamDTO>> find(@SearchSpec Specification<Exam> specs){
        return ResponseEntity.ok(examService.findBySpec(specs));
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

package com.roima.exammanagement.rest.v1.controller.admin;


import com.roima.exammanagement.rest.v1.dto.UserQuestionSubmissionDTO;
import com.roima.exammanagement.rest.v1.service.UserQuestionSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/user-question-submission")
@RequiredArgsConstructor
public class UserQuestionSubmissionController {

    private final UserQuestionSubmissionService userQuestionSubmissionService;
    @PutMapping("")
    public ResponseEntity<UserQuestionSubmissionDTO> update(@RequestBody UserQuestionSubmissionDTO userQuestionSubmissionDTO){
     UserQuestionSubmissionDTO userQuestionSubmissionDTO1 = userQuestionSubmissionService.update(userQuestionSubmissionDTO);
     return  ResponseEntity.ok(userQuestionSubmissionDTO1);

    }
}

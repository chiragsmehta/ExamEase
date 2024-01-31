package com.roima.exammanagement.rest.v1.dto;


import com.roima.exammanagement.rest.v1.dto.simple.SimpleProgrammingQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleUserDTO;
import lombok.Data;

@Data
public class UserProgrammingQuestionSubmissionDTO {
    private Long id;
    private SimpleUserDTO user;
    private SimpleProgrammingQuestionDTO programmingQuestion;
    private String userSolution;
}

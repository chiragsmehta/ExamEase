package com.roima.exammanagement.rest.v1.dto;


import lombok.Data;

@Data
public class UserProgrammingQuestionSubmissionDTO {
    private Long id;
    private UserDTO userDTO;
    private ProgrammingQuestionDTO programmingQuestionDTO;
    private String userSolution;
}

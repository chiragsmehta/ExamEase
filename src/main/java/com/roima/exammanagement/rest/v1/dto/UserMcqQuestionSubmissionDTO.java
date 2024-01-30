package com.roima.exammanagement.rest.v1.dto;

import lombok.Data;

@Data
public class UserMcqQuestionSubmissionDTO {
    private Long id;
    private UserDTO userDTO;
    private McqQuestionDTO mcqQuestionDTO;
    private OptionDTO optionDTO;
}

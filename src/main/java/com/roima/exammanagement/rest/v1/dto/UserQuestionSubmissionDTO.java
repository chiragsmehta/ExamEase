package com.roima.exammanagement.rest.v1.dto;

import com.roima.exammanagement.rest.v1.dto.simple.SimpleQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleOptionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleUserDTO;
import lombok.Data;

@Data
public class UserQuestionSubmissionDTO {
    private Long id;
    private SimpleUserDTO user;
    private SimpleQuestionDTO mcqQuestion;
    private SimpleOptionDTO option;
    private String answer;
}

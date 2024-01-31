package com.roima.exammanagement.rest.v1.dto;

import com.roima.exammanagement.rest.v1.dto.simple.SimpleMcqQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleOptionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleUserDTO;
import lombok.Data;

@Data
public class UserMcqQuestionSubmissionDTO {
    private Long id;
    private SimpleUserDTO user;
    private SimpleMcqQuestionDTO mcqQuestion;
    private SimpleOptionDTO option;
}

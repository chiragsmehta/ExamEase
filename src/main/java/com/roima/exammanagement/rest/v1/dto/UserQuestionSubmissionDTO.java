package com.roima.exammanagement.rest.v1.dto;

import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleExamDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleOptionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleUserDTO;
import lombok.Data;

@Data
public class UserQuestionSubmissionDTO {
    private Long id;
    private SimpleUserDTO user;
    private SimpleQuestionDTO question;
    private SimpleOptionDTO option;
    private String answer;
    private SimpleExamDTO exam;
    private int gainedMarks;
    private Boolean isChecked;

}

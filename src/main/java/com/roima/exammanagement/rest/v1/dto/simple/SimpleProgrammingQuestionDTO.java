package com.roima.exammanagement.rest.v1.dto.simple;

import lombok.Data;

import java.util.List;

@Data
public class SimpleProgrammingQuestionDTO {
    private Long id;
    private String question;
    private String description;
    private String answer;
    private int marks;
}

package com.roima.exammanagement.rest.v1.dto.simple;

import lombok.Data;

@Data
public class SimpleUserQuestionSubmissionDTO {
    private Long id;
    private int gainedMarks;
    private Boolean isChecked;

}

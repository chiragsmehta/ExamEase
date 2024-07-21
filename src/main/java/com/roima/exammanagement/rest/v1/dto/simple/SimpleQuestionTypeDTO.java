package com.roima.exammanagement.rest.v1.dto.simple;


import lombok.Data;

@Data
public class SimpleQuestionTypeDTO {
    private Long id;
    private String name;
    private Boolean isOptionRequired;
    private int marks;
}

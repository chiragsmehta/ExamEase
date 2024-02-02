package com.roima.exammanagement.rest.v1.dto.simple;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleQuestionDTO {
    private Long id;
    private String question;
    private String description;
    private int marks;

}

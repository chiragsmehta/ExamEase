package com.roima.exammanagement.rest.v1.dto.simple;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleMcqQuestionDTO {
    private Long id;
    private String question;
    private int marks;

}

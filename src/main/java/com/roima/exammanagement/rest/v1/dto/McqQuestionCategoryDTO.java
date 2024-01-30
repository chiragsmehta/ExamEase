package com.roima.exammanagement.rest.v1.dto;


import lombok.Data;

import java.util.List;

@Data
public class McqQuestionCategoryDTO {
    private Long id;
    private String name;
    private List<McqQuestionDTO> mcqQuestionDTOS;
}

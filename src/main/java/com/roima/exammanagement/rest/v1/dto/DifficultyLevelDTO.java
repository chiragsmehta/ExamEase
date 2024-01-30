package com.roima.exammanagement.rest.v1.dto;

import lombok.Data;

import java.util.List;

@Data
public class DifficultyLevelDTO {
    private Long id;
    private String name;
    private List<ProgrammingQuestionDTO> programmingQuestionDTOS;
    private List<McqQuestionDTO> mcqQuestionDTOS;
}

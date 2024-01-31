package com.roima.exammanagement.rest.v1.dto;

import com.roima.exammanagement.rest.v1.dto.simple.SimpleMcqQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleProgrammingQuestionDTO;
import lombok.Data;

import java.util.List;

@Data
public class DifficultyLevelDTO {
    private Long id;
    private String name;
    private List<SimpleProgrammingQuestionDTO> programmingQuestion;
    private List<SimpleMcqQuestionDTO> mcqQuestion;
}

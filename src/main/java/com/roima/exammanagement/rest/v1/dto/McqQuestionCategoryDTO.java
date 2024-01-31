package com.roima.exammanagement.rest.v1.dto;


import com.roima.exammanagement.rest.v1.dto.simple.SimpleMcqQuestionDTO;
import lombok.Data;

import java.util.List;

@Data
public class McqQuestionCategoryDTO {
    private Long id;
    private String name;
    private List<SimpleMcqQuestionDTO> mcqQuestion;
}

package com.roima.exammanagement.rest.v1.dto;


import com.roima.exammanagement.rest.v1.dto.simple.SimpleMcqQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimplePictureDTO;
import lombok.Data;

import java.util.List;

@Data
public class OptionDTO {
    private Long id;
    private String value;
    private Boolean isCorrect;
    private SimpleMcqQuestionDTO mcqQuestion;
    private List<SimplePictureDTO> picture;
}

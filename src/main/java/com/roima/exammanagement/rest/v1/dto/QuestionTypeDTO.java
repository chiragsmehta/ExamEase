package com.roima.exammanagement.rest.v1.dto;


import com.roima.exammanagement.rest.v1.dto.simple.SimpleQuestionDTO;
import lombok.Data;

import java.util.List;


@Data
public class QuestionTypeDTO {
    private Long id;
    private String name;
    private List<SimpleQuestionDTO> questions;
}

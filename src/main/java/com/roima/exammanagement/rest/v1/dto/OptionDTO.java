package com.roima.exammanagement.rest.v1.dto;


import lombok.Data;

import java.util.List;

@Data
public class OptionDTO {
    private Long id;
    private String value;
    private Boolean isCorrect;
    private McqQuestionDTO mcqQuestionDTO;
    private List<PictureDTO> pictureDTOS;
}

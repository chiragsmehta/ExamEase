package com.roima.exammanagement.rest.v1.dto;


import lombok.Data;

import java.util.List;

@Data
public class PictureDTO {
    private Long id;
    private String url;
    private String altText;
    private List<OptionDTO> optionDTOS;
    private List<McqQuestionDTO> mcqQuestionDTOS;
    private List<ProgrammingQuestionDTO>programmingQuestionDTOS;
}

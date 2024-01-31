package com.roima.exammanagement.rest.v1.dto;


import com.roima.exammanagement.rest.v1.dto.simple.SimpleMcqQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleOptionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleProgrammingQuestionDTO;
import lombok.Data;

import java.util.List;

@Data
public class PictureDTO {
    private Long id;
    private String url;
    private String altText;
    private List<SimpleOptionDTO> option;
    private List<SimpleMcqQuestionDTO> mcqQuestion;
    private List<SimpleProgrammingQuestionDTO>programmingQuestion;
}

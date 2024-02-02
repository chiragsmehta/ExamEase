package com.roima.exammanagement.rest.v1.dto;


import com.roima.exammanagement.rest.v1.dto.simple.SimpleQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleOptionDTO;
import lombok.Data;

import java.util.List;

@Data
public class PictureDTO {
    private Long id;
    private String url;
    private String altText;
    private List<SimpleOptionDTO> option;
    private List<SimpleQuestionDTO> question;
}

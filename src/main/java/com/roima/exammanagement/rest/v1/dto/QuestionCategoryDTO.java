package com.roima.exammanagement.rest.v1.dto;


import com.roima.exammanagement.rest.v1.dto.simple.SimpleQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleUserDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuestionCategoryDTO {
    private Long id;
    private String name;
    private List<SimpleQuestionDTO> questions;
    private SimpleUserDTO createdBy;
    private LocalDateTime createdAt;
    private SimpleUserDTO updatedBy;
    private LocalDateTime updatedAt;
}

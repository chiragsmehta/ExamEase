package com.roima.exammanagement.rest.v1.dto;


import com.roima.exammanagement.model.McqQuestionCategory;
import com.roima.exammanagement.model.Picture;
import com.roima.exammanagement.rest.v1.dto.simple.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class McqQuestionDTO {
    private Long id;
    private String question;
    private List<SimpleExamDTO> exams;
    private SimpleDifficultyLevelDTO difficulty;
    private List<SimpleOptionDTO> option;
    private SimpleMcqQuestionCategoryDTO mcqQuestionCategory;
    private List<SimplePictureDTO> picture;
    private int marks;

}

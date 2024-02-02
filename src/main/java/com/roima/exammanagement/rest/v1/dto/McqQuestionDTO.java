package com.roima.exammanagement.rest.v1.dto;


import com.roima.exammanagement.model.DifficultyLevel;
import com.roima.exammanagement.model.McqQuestionCategory;
import com.roima.exammanagement.model.Picture;
import com.roima.exammanagement.rest.v1.dto.simple.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class McqQuestionDTO {
    private Long id;
    private String question;
    private List<SimpleExamDTO> exams;
    private DifficultyLevel difficulty;
    private List<SimpleOptionDTO> options;
    private SimpleMcqQuestionCategoryDTO mcqQuestionCategory;
    private List<SimplePictureDTO> picture;
    private int marks;

    public void addOption(SimpleOptionDTO simpleOptionDTO){
        if(options == null){
            options = new ArrayList<>();
        }
        options.add(simpleOptionDTO);
    }

}

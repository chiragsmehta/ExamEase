package com.roima.exammanagement.rest.v1.dto;


import com.roima.exammanagement.model.DifficultyLevel;
import com.roima.exammanagement.rest.v1.dto.simple.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Long id;
    private String question;
    private String description;
    private List<SimpleExamDTO> exams;
    private DifficultyLevel difficulty;
    private List<SimpleOptionDTO> options;
    private List<SimpleOptionDTO> correctOptions;
    private SimpleQuestionCategoryDTO mcqQuestionCategory;
    private List<SimplePictureDTO> picture;
    private int marks;

    public void addOption(SimpleOptionDTO simpleOptionDTO){
        if(options == null){
            options = new ArrayList<>();
        }
        options.add(simpleOptionDTO);
    }

}

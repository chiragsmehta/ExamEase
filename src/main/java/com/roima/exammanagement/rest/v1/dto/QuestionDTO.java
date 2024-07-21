package com.roima.exammanagement.rest.v1.dto;


import com.roima.exammanagement.model.DifficultyLevel;
import com.roima.exammanagement.model.Option;
import com.roima.exammanagement.rest.v1.dto.simple.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Long id;
    private String question;
    private String description;
    private List<SimpleExamDTO> exams;
    private DifficultyLevel difficultyLevel;
    private List<SimpleOptionDTO> options;
    private String answer;
    private List<SimpleOptionDTO> correctOptions;
    private SimpleQuestionCategoryDTO questionCategory;
    private SimpleQuestionTypeDTO questionType;
    private List<SimplePictureDTO> pictures;
    private SimpleUserDTO createdBy;
    private LocalDateTime createdAt;
    private SimpleUserDTO updatedBy;
    private LocalDateTime updatedAt;
    private Boolean isActive;


    public void addOption(SimpleOptionDTO simpleOptionDTO){
        if(options == null){
            options = new ArrayList<>();
        }
        options.add(simpleOptionDTO);
    }

//    public List<SimpleOptionDTO> getCorrectOptions(){
//       return options.stream().filter(SimpleOptionDTO::getIsCorrect).collect(Collectors.toList());
//    }

}

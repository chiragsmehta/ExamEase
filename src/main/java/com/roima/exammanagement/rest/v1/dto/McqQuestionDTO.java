package com.roima.exammanagement.rest.v1.dto;


import com.roima.exammanagement.model.McqQuestionCategory;
import com.roima.exammanagement.model.Picture;
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
    private ExamDTO examDTO;
    private DifficultyLevelDTO difficultyLevelDTO;
    private List<OptionDTO> optionDTOS;
    private McqQuestionCategoryDTO mcqQuestionCategoryDTO;
    private List<PictureDTO> pictureDTOS;
    private int marks;

}

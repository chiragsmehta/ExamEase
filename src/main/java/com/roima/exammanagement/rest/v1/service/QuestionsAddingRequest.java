package com.roima.exammanagement.rest.v1.service;

import com.roima.exammanagement.model.DifficultyLevel;
import com.roima.exammanagement.rest.v1.dto.QuestionCategoryDTO;
import com.roima.exammanagement.rest.v1.dto.QuestionTypeDTO;
import lombok.Data;


@Data
public class QuestionsAddingRequest {
    private QuestionCategoryDTO questionCategory;
    private QuestionTypeDTO questionType;
    private DifficultyLevel difficultyLevel;
    private Long examId;
    private int count;
}

package com.roima.exammanagement.rest.v1.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProgrammingQuestionDTO {
    private Long id;
    private String question;
    private String description;
    private String answer;
    private DifficultyLevelDTO difficultyLevelDTO;
    private ExamDTO examDTO;
    private List<PictureDTO> pictureDTOS;
    private int marks;
}

package com.roima.exammanagement.rest.v1.dto;

import com.roima.exammanagement.rest.v1.dto.simple.SimpleDifficultyLevelDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleExamDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimplePictureDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProgrammingQuestionDTO {
    private Long id;
    private String question;
    private String description;
    private String answer;
    private SimpleDifficultyLevelDTO difficultyLevel;
    private SimpleExamDTO exam;
    private List<SimplePictureDTO> picture;
    private int marks;
}

package com.roima.exammanagement.rest.v1.dto.simple;


import com.roima.exammanagement.model.DifficultyLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleQuestionDTO {
    private Long id;
    private String question;
    private String description;
    private String answer;
    private Boolean isActive;
    private DifficultyLevel difficultyLevel;
//    private List<SimpleOptionDTO> options;
    private List<SimpleOptionDTO> correctOptions;
//private List<SimpleOptionDTO> correctOptions = options.stream()
//        .filter(SimpleOptionDTO::getIsCorrect)
//        .collect(Collectors.toList());

}

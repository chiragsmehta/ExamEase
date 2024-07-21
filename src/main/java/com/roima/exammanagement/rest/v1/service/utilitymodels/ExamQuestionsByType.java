package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.rest.v1.dto.QuestionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleQuestionTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamQuestionsByType {
    private SimpleQuestionTypeDTO questionType;
    private List<QuestionDTO>  questions;
}

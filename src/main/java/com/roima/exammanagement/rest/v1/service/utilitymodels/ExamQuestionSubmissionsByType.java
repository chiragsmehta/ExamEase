package com.roima.exammanagement.rest.v1.service.utilitymodels;


import com.roima.exammanagement.rest.v1.dto.UserQuestionSubmissionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleQuestionTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamQuestionSubmissionsByType {
    private SimpleQuestionTypeDTO questionType;
    private List<UserQuestionSubmissionDTO> userQuestionSubmissions;
}

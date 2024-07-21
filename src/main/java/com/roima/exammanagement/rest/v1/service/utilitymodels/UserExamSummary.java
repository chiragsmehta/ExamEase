package com.roima.exammanagement.rest.v1.service.utilitymodels;


import com.roima.exammanagement.rest.v1.dto.ExamDTO;
import com.roima.exammanagement.rest.v1.dto.UserDTO;
import com.roima.exammanagement.rest.v1.dto.UserExamStatusDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleExamDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleQuestionTypeDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleUserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserExamSummary {
    private SimpleUserDTO user;
    private SimpleExamDTO exam;
    private UserExamStatusDTO userExamStatus;
    private HashMap<String,Integer> totalMarksByQuestionType;
    private Integer totalGainedMarks;
    private Boolean areAllQuestionsChecked;
}

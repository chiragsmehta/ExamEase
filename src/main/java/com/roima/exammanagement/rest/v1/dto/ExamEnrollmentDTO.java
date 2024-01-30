package com.roima.exammanagement.rest.v1.dto;


import com.roima.exammanagement.model.UserExamStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamEnrollmentDTO {
    private Long id;
    private UserDTO userDTO;
    private ExamDTO examDTO;
    private Duration examDuration;
    private UserExamStatusDTO userExamStatusDTO;
}

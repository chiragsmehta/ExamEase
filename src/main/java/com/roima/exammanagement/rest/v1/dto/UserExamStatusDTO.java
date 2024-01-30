package com.roima.exammanagement.rest.v1.dto;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
public class UserExamStatusDTO {
    private Long id;
    private ExamEnrollmentDTO examEnrollmentDTO;
    private LocalDateTime startDateTime;
    private LocalDateTime submissionDateTime;
    private Boolean hasSubmitted;
    private Duration remainingDuration;
}

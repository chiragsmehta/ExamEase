package com.roima.exammanagement.rest.v1.dto.simple;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
public class SimpleUserExamStatusDTO {
    private Long id;
    private LocalDateTime startDateTime;
    private LocalDateTime submissionDateTime;
    private Boolean hasSubmitted;
    private Duration remainingDuration;
    private Boolean hasDoneMalpractice;
}

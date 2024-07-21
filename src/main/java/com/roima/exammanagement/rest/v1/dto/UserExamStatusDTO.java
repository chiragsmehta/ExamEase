package com.roima.exammanagement.rest.v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleExamEnrollmentDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
public class UserExamStatusDTO {
    private Long id;
    private SimpleExamEnrollmentDTO examEnrollment;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime submissionDateTime;
    private Boolean hasSubmitted;
    private Duration remainingDuration;
    private Boolean hasDoneMalpractice;
}

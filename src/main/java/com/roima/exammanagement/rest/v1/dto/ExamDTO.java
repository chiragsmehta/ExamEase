package com.roima.exammanagement.rest.v1.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.roima.exammanagement.model.UserQuestionSubmission;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleExamEnrollmentDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleUserDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExamDTO {
    private String name;
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDateTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")

    private LocalDateTime endDateTime;

    private Duration duration;
    private List<SimpleQuestionDTO> question;
    private List<SimpleExamEnrollmentDTO> examEnrollments;
    private String instructions;
    private SimpleUserDTO createdBy;
    private LocalDateTime createdAt;
    private SimpleUserDTO updatedBy;
    private LocalDateTime updatedAt;
    private int currentMarks;
    private Boolean isActive;
    private int totalMarks;
    private int passingMarks = (int)Math.round(totalMarks-totalMarks*0.4);
    private List<UserQuestionSubmissionDTO> userQuestionSubmissions;
}

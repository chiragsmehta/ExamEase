package com.roima.exammanagement.rest.v1.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleExamEnrollmentDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleProgrammingQuestionDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExamDTO {
    private String name;
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDateTime;

    private Duration duration;
    private List<SimpleProgrammingQuestionDTO> programmingQuestion;
    private List<SimpleQuestionDTO> mcqQuestion;
    private List<SimpleExamEnrollmentDTO> examEnrollment;
    private String instructions;
    private int totalMarks;
}

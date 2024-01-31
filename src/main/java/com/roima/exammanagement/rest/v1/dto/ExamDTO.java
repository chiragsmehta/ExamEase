package com.roima.exammanagement.rest.v1.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.roima.exammanagement.model.McqQuestion;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleExamEnrollmentDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleMcqQuestionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleProgrammingQuestionDTO;
import com.roima.exammanagement.rest.v1.serializer.MyDurationSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private List<SimpleMcqQuestionDTO> mcqQuestion;
    private List<SimpleExamEnrollmentDTO> examEnrollment;
    private int totalMarks;
}

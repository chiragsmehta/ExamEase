package com.roima.exammanagement.rest.v1.dto;


import com.roima.exammanagement.model.McqQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExamDTO {
    private Long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Duration duration;
    private List<ProgrammingQuestionDTO> programmingQuestionDTOS;
    private List<McqQuestionDTO> mcqQuestionDTOS;
    private List<ExamEnrollmentDTO> examEnrollmentDTOS;
    private int totalMarks;
}

package com.roima.exammanagement.rest.v1.dto.simple;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SimpleExamDTO {
    private String name;
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")

    private LocalDateTime startDateTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")

    private LocalDateTime endDateTime;
    private Duration duration;
    private int currentMarks;
    private int totalMarks;
    private int passingMarks = (int)Math.round(totalMarks-totalMarks*0.4);
    private Boolean isActive;
}

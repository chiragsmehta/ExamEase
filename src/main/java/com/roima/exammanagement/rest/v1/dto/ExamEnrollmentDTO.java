package com.roima.exammanagement.rest.v1.dto;


import com.roima.exammanagement.model.UserExamStatus;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleExamDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleUserDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleUserExamStatusDTO;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
    private SimpleUserDTO user;
    private SimpleExamDTO exam;
    private SimpleUserExamStatusDTO userExamStatus;
}

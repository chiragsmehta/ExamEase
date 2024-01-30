package com.roima.exammanagement.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserExamStatus extends  BaseEntity {

    @OneToOne
    private ExamEnrollment examEnrollment;

    private LocalDateTime startDateTime;

    private LocalDateTime submissionDateTime;

    private Boolean hasSubmitted;

    private Duration remainingDuration;
}

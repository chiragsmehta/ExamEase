package com.roima.exammanagement.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserExamStatus extends  BaseEntity {

    @OneToOne
    @JoinColumn(name = "exam_enrollment_id")
    private ExamEnrollment examEnrollment;

    private LocalDateTime startDateTime;

    private LocalDateTime submissionDateTime;

    private Boolean hasSubmitted = false;

    private Duration remainingDuration;
}

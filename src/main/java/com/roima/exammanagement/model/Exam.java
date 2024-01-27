package com.roima.exammanagement.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Exam extends BaseEntity{
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Duration duration;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<ProgrammingQuestion> programmingQuestions;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<McqQuestion> mcqQuestions;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<ExamEnrollment> examEnrollments;
}

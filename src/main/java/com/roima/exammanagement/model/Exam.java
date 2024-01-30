package com.roima.exammanagement.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
    private int totalMarks;

    @ManyToMany
    @JoinTable(
            name = "exam_programming_question",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "programming_question_id")
    )
    private List<ProgrammingQuestion> programmingQuestions;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<McqQuestion> mcqQuestions;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<ExamEnrollment> examEnrollments;

    public int getTotalMarks(){
        int totalMarks = 0;
        totalMarks += programmingQuestions.stream().map(ProgrammingQuestion::getMarks).mapToInt(Integer::intValue).sum();
        totalMarks += mcqQuestions.stream().map(McqQuestion::getMarks).mapToInt(Integer::intValue).sum();
        return totalMarks;
    }
}

package com.roima.exammanagement.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDateTime;

    // PT40M (40 minutes)
    // https://www.logicbig.com/how-to/code-snippets/jcode-java-8-date-time-api-duration-parse.html
    private Duration duration;
    private int totalMarks;

    @ManyToMany
    @JoinTable(
            name = "exam_programming_question",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "programming_question_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"exam_id","programming_question_id"})}
    )
    private List<ProgrammingQuestion> programmingQuestions;

//    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    @ManyToMany
    @JoinTable(
            name = "exam_mcq_question",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "mcq_question_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"exam_id","mcq_question_id"})}
    )
    private List<McqQuestion> mcqQuestions;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<ExamEnrollment> examEnrollments;

//    public int getTotalMarks(){
//        int totalMarks = 0;
//        totalMarks += programmingQuestions.stream().map(ProgrammingQuestion::getMarks).mapToInt(Integer::intValue).sum();
//        totalMarks += mcqQuestions.stream().map(McqQuestion::getMarks).mapToInt(Integer::intValue).sum();
//        return totalMarks;
//    }

}

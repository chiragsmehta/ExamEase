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
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Exam extends BaseEntity{


    private String name;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDateTime;

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDateTime;

    // PT40M (40 minutes)
    // https://www.logicbig.com/how-to/code-snippets/jcode-java-8-date-time-api-duration-parse.html
    private Duration duration;

//    private int totalMarks;

    @Lob
    private String instructions;


    @ManyToMany
    @JoinTable(
            name = "exam_question",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"exam_id","question_id"})}
    )
    private List<Question> questions;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<ExamEnrollment> examEnrollments;

    @ManyToOne
    @JoinColumn(name = "created_by_admin_id")
    private User createdBy;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "updated_by_admin_id")
    private User updatedBy;
    private LocalDateTime updatedAt;
    private Boolean isActive;
    private int currentMarks;
    private  int totalMarks;
    private int passingMarks = (int)Math.round(totalMarks-totalMarks*0.4);
    @OneToMany(mappedBy = "exam")
    private List<UserQuestionSubmission> userQuestionSubmissions;

    public int getCurrentMarks(){
        return questions.stream().map(question -> question.getQuestionType().getMarks()).mapToInt(Integer::intValue).sum();
    }




}

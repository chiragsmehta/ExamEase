package com.roima.exammanagement.model;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProgrammingQuestion  extends BaseEntity{

    @Lob
    private  String question;

    @Lob
    private String description;

    @Lob
    private String answer;

    private DifficultyLevel difficultyLevel;

    @ManyToMany
    @JoinTable(
            name = "exam_programming_question",
            inverseJoinColumns = @JoinColumn(name = "exam_id"),
            joinColumns = @JoinColumn(name = "programming_question_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"exam_id","programming_question_id"})}
    )
    private List<Exam> exam;

    private int marks;

    @ManyToMany
    @JoinTable(
            name = "programming_question_picture",
            joinColumns = @JoinColumn(name = "programming_question_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id")
    )
    private List<Picture> pictures;

    @OneToMany(mappedBy = "programmingQuestion")
    private List<UserProgrammingQuestionSubmission> userProgrammingQuestionSubmissions;
}

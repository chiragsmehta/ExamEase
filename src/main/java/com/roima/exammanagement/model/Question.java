package com.roima.exammanagement.model;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Question extends BaseEntity{

    @Lob
    private String question;

    @Lob
    private String description;

    @Lob
    private String answer;



    @ManyToMany
    @JoinTable(
            name = "exam_question",
            inverseJoinColumns = @JoinColumn(name = "exam_id"),
            joinColumns = @JoinColumn(name = "question_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"exam_id","question_id"})}
    )
    private List<Exam> exams = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Option> options = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Option> correctOptions;

    @ManyToOne
    @JoinColumn(name = "question_category_id", nullable = false)
    private QuestionCategory questionCategory;

    @ManyToOne
    @JoinColumn(name = "question_type_id", nullable = false)
    private QuestionType questionType;

    private DifficultyLevel difficultyLevel;

    private int marks;

    @ManyToMany
    @JoinTable(
            name = "question_picture",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id")
    )
    private List<Picture> pictures =new ArrayList<>();

    @OneToMany(mappedBy = "question")
    private List<UserQuestionSubmission> userQuestionSubmissions;


}

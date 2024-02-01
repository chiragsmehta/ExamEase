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
public class McqQuestion extends BaseEntity{

    @Lob
    private String question;

    @ManyToMany
    @JoinTable(
            name = "exam_mcq_question",
            inverseJoinColumns = @JoinColumn(name = "exam_id"),
            joinColumns = @JoinColumn(name = "mcq_question_id")
    )
    private List<Exam> exams = new ArrayList<>();

    @OneToMany(mappedBy = "mcqQuestion", cascade = CascadeType.ALL)
    private List<Option> options = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "mcq_question_category_id")
    private McqQuestionCategory mcqQuestionCategory;

    @ManyToOne
    @JoinColumn(name = "difficulty_level")
    private DifficultyLevel difficultyLevel;

    private int marks;

    @ManyToMany
    @JoinTable(
            name = "mcq_question_picture",
            joinColumns = @JoinColumn(name = "mcq_question_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id")
    )
    private List<Picture> pictures =new ArrayList<>();

    @OneToMany(mappedBy = "mcqQuestion")
    private List<UserMcqQuestionSubmission> userMcqQuestionSubmissions;


}

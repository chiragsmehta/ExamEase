package com.roima.exammanagement.model;


import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "difficulty_level_id")
    private DifficultyLevel difficultyLevel;

    @ManyToOne
    @JoinColumn(name="exam_id")
    private Exam exam;

    @ManyToMany
    @JoinTable(
            name = "programming_question_picture",
            joinColumns = @JoinColumn(name = "programming_question_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id")
    )
    private List<Picture> pictures;
}

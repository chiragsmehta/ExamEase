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
@NoArgsConstructor
@AllArgsConstructor
public class McqQuestion extends BaseEntity{

    @Lob
    private String question;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @OneToMany(mappedBy = "mcqQuestion", cascade = CascadeType.ALL)
    private List<Option> options;

    @ManyToOne
    @JoinColumn(name = "mcq_question_category_id")
    private McqQuestionCategory mcqQuestionCategory;

    @ManyToMany
    @JoinTable(
            name = "mcq_question_picture",
            joinColumns = @JoinColumn(name = "mcq_question_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id")
    )
    private List<Picture> pictures;


}

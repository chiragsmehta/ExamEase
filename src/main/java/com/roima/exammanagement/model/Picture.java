package com.roima.exammanagement.model;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Picture extends BaseEntity{

    private String url;

    private String altText;

    @ManyToMany
    @JoinTable(
            name = "option_picture",
            joinColumns = @JoinColumn(name = "picture_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option> options;

    @ManyToMany
    @JoinTable(
            name = "mcq_question_picture",
            joinColumns = @JoinColumn(name = "picture_id"),
            inverseJoinColumns = @JoinColumn(name = "mcq_question_id")
    )
    private List<McqQuestion> mcqQuestions;

    @ManyToMany
    @JoinTable(
            name = "programming_question_picture",
            joinColumns = @JoinColumn(name = "picture_id"),
            inverseJoinColumns = @JoinColumn(name = "programming_question_id")
    )
    private List<ProgrammingQuestion> programmingQuestions;
}

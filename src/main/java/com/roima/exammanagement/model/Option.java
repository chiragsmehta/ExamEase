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
@NoArgsConstructor
@AllArgsConstructor
public class Option extends BaseEntity{
    private String value;
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "mcq_question_id")
    private McqQuestion mcqQuestion;

    @ManyToMany
    @JoinTable(
            name = "option_picture",
            joinColumns = @JoinColumn(name = "option_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id")
    )
    private List<Picture> pictures;

    @OneToMany(mappedBy = "option")
    private List<UserMcqQuestionSubmission> userMcqQuestionSubmissions;

}

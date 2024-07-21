package com.roima.exammanagement.model;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class UserQuestionSubmission extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id" )
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option option;

    @Lob
    private String answer;

    @ManyToOne
    @JoinColumn(name="exam_id")
    private Exam exam;

    private int gainedMarks;
    private Boolean isChecked = false;

}

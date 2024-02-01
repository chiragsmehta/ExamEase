package com.roima.exammanagement.model;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserMcqQuestionSubmission extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id" )
    private User user;

    @ManyToOne
    @JoinColumn(name = "mcq_question_id")
    private McqQuestion mcqQuestion;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option option;

}

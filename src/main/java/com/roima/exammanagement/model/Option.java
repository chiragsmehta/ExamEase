package com.roima.exammanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

}

package com.roima.exammanagement.model;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
public class McqQuestionCategory extends BaseEntity{
    private String name;

    @OneToMany(mappedBy = "mcqQuestionCategory")
    private List<McqQuestion> mcqQuestions;
}

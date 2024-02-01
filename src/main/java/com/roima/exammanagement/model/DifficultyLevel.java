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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DifficultyLevel extends BaseEntity{
    private String name;

    @OneToMany(mappedBy = "difficultyLevel")
    private List<ProgrammingQuestion> programmingQuestions;

    @OneToMany(mappedBy = "difficultyLevel")
    private List<McqQuestion> mcqQuestions;
}

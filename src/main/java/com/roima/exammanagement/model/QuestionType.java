package com.roima.exammanagement.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class QuestionType extends BaseEntity{
    private String name;

    @OneToMany(mappedBy = "questionType")
    private List<Question> questions;
}

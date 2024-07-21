package com.roima.exammanagement.model;


import javax.persistence.*;

import com.roima.exammanagement.config.DifficultyLevelEnumConverter;
import lombok.*;
import org.springframework.data.convert.ValueConverter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Question extends BaseEntity{

    @Lob
    private String question;

    @Lob
    private String description;

    @Lob
    private String answer;



    @ManyToMany
    @JoinTable(
            name = "exam_question",
            inverseJoinColumns = @JoinColumn(name = "exam_id"),
            joinColumns = @JoinColumn(name = "question_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"exam_id","question_id"})}
    )
    @ToString.Exclude
    private List<Exam> exams = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Option> options = new ArrayList<>();

    @Transient
    @ToString.Exclude
    private List<Option> correctOptions;

    @ManyToOne
    @JoinColumn(name = "question_category_id", nullable = false)
    @ToString.Exclude
    private QuestionCategory questionCategory;

    @ManyToOne
    @JoinColumn(name = "question_type_id", nullable = false)
    @ToString.Exclude
    private QuestionType questionType;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;


    @ManyToMany
    @JoinTable(
            name = "question_picture",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id")
    )
    private List<Picture> pictures =new ArrayList<>();

    @OneToMany(mappedBy = "question")
    private List<UserQuestionSubmission> userQuestionSubmissions;

//    public List<Option> getCorrectOptions(){
//        return options.stream().filter(Option::getIsCorrect).collect(Collectors.toList());
//    }

    @ManyToOne
    @JoinColumn(name = "created_by_admin_id")
    private User createdBy;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "updated_by_admin_id")
    private User updatedBy;
    private LocalDateTime updatedAt;
    private Boolean isActive;

    @Override
    public String toString(){
        return this.getId() + " " + this.getQuestion();
    }

    @PostLoad
    private void calculateCorrectOptions(){
        this.correctOptions =  options.stream().filter(Option::getIsCorrect).collect(Collectors.toList());
    }




}

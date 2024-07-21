package com.roima.exammanagement.model;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class QuestionType extends BaseEntity{
    private String name;

    @OneToMany(mappedBy = "questionType")
    @ToString.Exclude
    private List<Question> questions;

    @Column(nullable = false)
    private Boolean isOptionRequired;

    @ManyToOne
    @JoinColumn(name = "created_by_admin_id")
    private User createdBy;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "updated_by_admin_id")
    private User updatedBy;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime updatedAt;
    private Boolean isActive;
    private  int marks;
}

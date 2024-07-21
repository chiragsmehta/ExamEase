package com.roima.exammanagement.model;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class QuestionCategory extends BaseEntity{
    private String name;

    @OneToMany(mappedBy = "questionCategory")
    @ToString.Exclude
    private List<Question> questions;

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
}

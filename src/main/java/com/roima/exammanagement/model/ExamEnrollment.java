package com.roima.exammanagement.model;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id","exam_id"})
})
public class ExamEnrollment extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "user_exam_status_id")
    private UserExamStatus userExamStatus;

    @ManyToOne
    @JoinColumn(name = "created_by_admin_id")
    private User createdBy;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "updated_by_admin_id")
    private User updatedBy;
    private LocalDateTime updatedAt;
    private Boolean isActive;
}

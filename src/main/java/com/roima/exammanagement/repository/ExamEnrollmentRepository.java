package com.roima.exammanagement.repository;

import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.model.ExamEnrollment;
import com.roima.exammanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamEnrollmentRepository extends JpaRepository<ExamEnrollment,Long>, JpaSpecificationExecutor<ExamEnrollment> {
    public ExamEnrollment findByUserAndExam(User user, Exam exam);
}

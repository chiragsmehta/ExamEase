package com.roima.exammanagement.repository;

import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.model.ExamEnrollment;
import com.roima.exammanagement.model.UserExamStatus;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExamStatusRepository extends JpaRepository<UserExamStatus,Long>, JpaSpecificationExecutor<UserExamStatus> {
    public UserExamStatus findByExamEnrollment(ExamEnrollment examEnrollment);



}

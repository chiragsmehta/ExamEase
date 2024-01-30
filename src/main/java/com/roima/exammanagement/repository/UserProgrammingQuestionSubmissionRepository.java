package com.roima.exammanagement.repository;

import com.roima.exammanagement.model.UserProgrammingQuestionSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProgrammingQuestionSubmissionRepository extends JpaRepository<UserProgrammingQuestionSubmission,Long> {
}

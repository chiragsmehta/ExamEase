package com.roima.exammanagement.repository;

import com.roima.exammanagement.model.UserQuestionSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuestionSubmissionRepository extends JpaRepository<UserQuestionSubmission,Long> {
}

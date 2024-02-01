package com.roima.exammanagement.repository;

import com.roima.exammanagement.model.McqQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface McqQuestionRepository extends JpaRepository<McqQuestion,Long> {

    public List<McqQuestion> findMcqByExams(Long examId);
}

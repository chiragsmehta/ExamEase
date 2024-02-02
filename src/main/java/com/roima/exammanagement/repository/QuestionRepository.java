package com.roima.exammanagement.repository;

import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long>, JpaSpecificationExecutor<Question> {

    public List<Question> findMcqByExams(Long examId);
}

package com.roima.exammanagement.repository;

import  com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long>, JpaSpecificationExecutor<Exam> {



    @Query(value = "SELECT e FROM Exam e " +
            "JOIN e.examEnrollments er " +
            "WHERE er.user = :user")
    public List<Exam> findByUser(@Param("user") User user);
}

package com.roima.exammanagement.repository;

import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);

    @Query(value = "SELECT u from User u " +
            "JOIN u.examEnrollments er " +
            "WHERE er.exam.id = :exam_id ")
    List<User> findAllByExamId(@Param("exam_id") Long examId);


    @Query(value = "SELECT  u from User u " +
            " WHERE :exam NOT IN " +
            "(SELECT er.exam FROM ExamEnrollment er WHERE " +
            "u = er.user ) " +
            "AND u.isActive = true")
    List<User> findAllNotInExamEnrollment(@Param("exam") Exam exam);
}

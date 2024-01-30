package com.roima.exammanagement.repository;

import com.roima.exammanagement.model.DifficultyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DifficultyLevelRepository extends JpaRepository<DifficultyLevel,Long> {
}

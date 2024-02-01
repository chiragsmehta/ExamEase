package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.repository.DifficultyLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DifficultyLevelService {
    private final DifficultyLevelRepository difficultyLevelRepository;


}

package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.model.ExamEnrollment;
import com.roima.exammanagement.model.User;
import com.roima.exammanagement.model.UserExamStatus;
import com.roima.exammanagement.repository.ExamEnrollementRepository;
import com.roima.exammanagement.repository.ExamRepository;
import com.roima.exammanagement.repository.UserRepository;
import com.roima.exammanagement.rest.v1.dto.ExamDTO;
import com.roima.exammanagement.rest.v1.mapper.ExamMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;
    private final UserRepository userRepository;
    private final ExamEnrollementRepository examEnrollementRepository;

    public ExamDTO findExamById(Long id) throws ChangeSetPersister.NotFoundException {
        Exam exam = examRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return examMapper.toDTO(exam);
    }

    public Boolean assignExamToUserById(@NonNull Long userId, @NonNull Long examId){
        try{
            Exam exam = examRepository.findById(examId).orElseThrow();
            User user = userRepository.findById(userId).orElseThrow();
            ExamEnrollment examEnrollment = new ExamEnrollment(user,exam, UserExamStatus.builder().remainingDuration(exam.getDuration()).build());
            examEnrollementRepository.save(examEnrollment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean saveExam(@NonNull ExamDTO examDTO) {
        System.out.println(examDTO);
        Exam exam = examMapper.toEntity(examDTO);
        try{
            examRepository.save(exam);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

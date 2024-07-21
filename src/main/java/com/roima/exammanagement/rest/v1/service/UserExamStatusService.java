package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.model.ExamEnrollment;
import com.roima.exammanagement.model.User;
import com.roima.exammanagement.model.UserExamStatus;
import com.roima.exammanagement.repository.ExamEnrollmentRepository;
import com.roima.exammanagement.repository.ExamRepository;
import com.roima.exammanagement.repository.UserExamStatusRepository;
import com.roima.exammanagement.repository.UserRepository;
import com.roima.exammanagement.rest.v1.dto.UserDTO;
import com.roima.exammanagement.rest.v1.dto.UserExamStatusDTO;
import com.roima.exammanagement.rest.v1.mapper.ExamMapper;
import com.roima.exammanagement.rest.v1.mapper.UserExamStatusMapper;
import com.roima.exammanagement.rest.v1.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserExamStatusService{

    private final UserExamStatusRepository userExamStatusRepository;
    private final ExamRepository examRepository;
    private final ExamEnrollmentRepository examEnrollmentRepository;
    private final UserExamStatusMapper userExamStatusMapper;
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ExamMapper examMapper;


    public UserExamStatusDTO findByUserAndExamId(Long userId,Long examId){
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Exam exam = examRepository.findById(examId).orElseThrow(NoSuchElementException::new);
        ExamEnrollment examEnrollment = examEnrollmentRepository.findByUserAndExam(user,exam);
        UserExamStatus userExamStatus = userExamStatusRepository.findByExamEnrollment(examEnrollment);

//        System.out.println(userExamStatusMapper.toDTO(userExamStatus));
        if(userExamStatus == null){
            throw  new NoSuchElementException();
        }else{
            return  userExamStatusMapper.toDTO(userExamStatus);
        }
    }

    public UserExamStatusDTO findByExamId(Long examId){
        ExamEnrollment examEnrollment = examEnrollmentRepository.findByUserAndExam(userMapper.toEntity(userService.getCurrentUser()),
                examRepository.findById(examId).orElse(null));
        UserExamStatus userExamStatus = userExamStatusRepository.findByExamEnrollment(examEnrollment);
        Duration userDuration = userExamStatus.getRemainingDuration();
        Duration examLeftDuration = Duration.between(LocalDateTime.now(),examRepository.findById(examId).orElseThrow(NoSuchElementException::new).getEndDateTime());
        userExamStatus.setRemainingDuration(Duration.ofMillis(Math.min(userDuration.toMillis(),examLeftDuration.toMillis())));
        return userExamStatusMapper.toDTO(userExamStatus);

    }

    public UserExamStatusDTO startExam(Long examId){
        User user = userMapper.toEntity(userService.getCurrentUser());
        Exam exam = examRepository.findById(examId).orElse(null);
        if(exam.getEndDateTime().isBefore(LocalDateTime.now())){
            throw new NoSuchElementException("Exam Has Ended");
        }
        try{
            UserExamStatusDTO userExamStatusDTO = findByExamId(examId);
            if(userExamStatusDTO == null){
                throw new NullPointerException("could have used an if else instead");
            }
            return userExamStatusDTO;
        }catch (NullPointerException exception){
            UserExamStatus userExamStatus = new UserExamStatus();
            userExamStatus.setExamEnrollment(examEnrollmentRepository.findByUserAndExam(user,exam));
            userExamStatus.setHasSubmitted(false);
            userExamStatus.setStartDateTime(LocalDateTime.now());
            assert exam != null;
            Duration userDuration = exam.getDuration();
            Duration examLeftDuration = Duration.between(LocalDateTime.now(),exam.getEndDateTime());
            userExamStatus.setRemainingDuration(Duration.ofMillis(Math.min(userDuration.toMillis(),examLeftDuration.toMillis())));
            userExamStatusRepository.save(userExamStatus);
            return findByExamId(examId);
        }
    }

    public UserExamStatusDTO updateUserExamStatus(UserExamStatusDTO userExamStatusDTO){
        UserExamStatus userExamStatus = userExamStatusRepository.findById(userExamStatusDTO.getId()).orElseThrow(NoSuchElementException::new);
        userExamStatusMapper.updateSourceFromTarget(userExamStatusDTO,userExamStatus);
        userExamStatusRepository.save(userExamStatus);
        return  userExamStatusMapper.toDTO(userExamStatus);
    }

    public UserExamStatusDTO submitExam(UserExamStatusDTO userExamStatusDTO){
        UserExamStatus userExamStatus = userExamStatusRepository.findById(userExamStatusDTO.getId())
                .orElseThrow(NoSuchElementException::new);
        userExamStatus.setRemainingDuration(Duration.ZERO);
        userExamStatus.setHasSubmitted(true);
        userExamStatus.setSubmissionDateTime(LocalDateTime.now());
        userExamStatusRepository.save(userExamStatus);
        return userExamStatusMapper.toDTO(userExamStatus);
    }

    public UserExamStatusDTO registerMalPractice(Long examId){
        Exam exam = examRepository.findById(examId).orElseThrow(NoSuchElementException::new);
        User user = userRepository.findById(userService.getCurrentUser().getId()).orElseThrow(NoSuchElementException::new);
        ExamEnrollment examEnrollment = examEnrollmentRepository.findByUserAndExam(user,exam);
        UserExamStatus userExamStatus = userExamStatusRepository.findByExamEnrollment(examEnrollment);
        if(userExamStatus != null ){
//            userExamStatus.setSubmissionDateTime(LocalDateTime.now());
            userExamStatus.setHasDoneMalpractice(true);
//            userExamStatus.setHasSubmitted(true);
            userExamStatusRepository.save(userExamStatus);
            return  userExamStatusMapper.toDTO(userExamStatus);
        }else{
            userExamStatus = new UserExamStatus();
            userExamStatus.setExamEnrollment(examEnrollment);
//            userExamStatus.setSubmissionDateTime(LocalDateTime.now());
            userExamStatus.setHasDoneMalpractice(true);
//            userExamStatus.setHasSubmitted(true);
//            userExamStatus.setStartDateTime(LocalDateTime.now());
            userExamStatusRepository.save(userExamStatus);
            return  userExamStatusMapper.toDTO(userExamStatus);
        }
    }

}

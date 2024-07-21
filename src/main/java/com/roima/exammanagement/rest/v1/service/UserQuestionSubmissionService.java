package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.model.*;
import com.roima.exammanagement.repository.ExamRepository;
import com.roima.exammanagement.repository.QuestionTypeRepository;
import com.roima.exammanagement.repository.UserQuestionSubmissionRepository;
import com.roima.exammanagement.repository.UserRepository;
import com.roima.exammanagement.rest.v1.dto.UserDTO;
import com.roima.exammanagement.rest.v1.dto.UserQuestionSubmissionDTO;
import com.roima.exammanagement.rest.v1.mapper.UserQuestionSubmissionMapper;
import com.roima.exammanagement.rest.v1.mapper.simple.SimpleQuestionTypeMapper;
import com.roima.exammanagement.rest.v1.service.utilitymodels.ExamQuestionSubmissionsByType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserQuestionSubmissionService {
    private final UserQuestionSubmissionRepository userQuestionSubmissionRepository;
    private final UserService userService;
    private final UserQuestionSubmissionMapper userQuestionSubmissionMapper;
    private final QuestionTypeRepository questionTypeRepository;
    private final ExamRepository examRepository;
    private final SimpleQuestionTypeMapper simpleQuestionTypeMapper;
    private  final UserRepository userRepository;


    public UserQuestionSubmissionDTO findByExamAndQuestionId(Long examId,Long questionID){
        UserDTO user = userService.getCurrentUser();
        return  userQuestionSubmissionMapper
                .toDTO(userQuestionSubmissionRepository.findByUserAndExamAndQuestion(user.getId(),examId,questionID));
    }

    public List<UserQuestionSubmissionDTO>  findAllByUserAndExam(Long examId, Long userId){
        List<UserQuestionSubmission> userQuestionSubmissions =  userQuestionSubmissionRepository
                .findByExamAndUser(userId,examId);
        return  userQuestionSubmissions.stream().map(userQuestionSubmissionMapper::toDTO).collect(Collectors.toList());
    }

    public UserQuestionSubmissionDTO update(UserQuestionSubmissionDTO userQuestionSubmissionDTO){
        UserQuestionSubmission userQuestionSubmission = userQuestionSubmissionRepository
                .findById(userQuestionSubmissionDTO
                        .getId()).orElse(null);
        userQuestionSubmissionMapper.updateSourceFromTarget(userQuestionSubmissionDTO,userQuestionSubmission);
        userQuestionSubmissionRepository.save(userQuestionSubmission);
        return  userQuestionSubmissionMapper.toDTO(userQuestionSubmission);
    }

    public List<ExamQuestionSubmissionsByType> findAllFromExamAndUserByQuestionType(Long userId, Long examId){
//        System.out.println(userQuestionSubmissionRepository.findByExamAndUser(userId,examId).size());
        List<ExamQuestionSubmissionsByType> examQuestionSubmissionsByTypes = new ArrayList<>();
        List<QuestionType> questionTypes = questionTypeRepository.findAll();

        questionTypes.forEach(type->{
            ExamQuestionSubmissionsByType s = new ExamQuestionSubmissionsByType();
            s.setQuestionType(simpleQuestionTypeMapper.toDTO(type));
            s.setUserQuestionSubmissions(userQuestionSubmissionRepository.findByExamAndUser(userId,examId)
                    .stream().filter(userQuestionSubmission -> {
                        return userQuestionSubmission.getQuestion().
                                getQuestionType().equals(type);
                    }).
                    map(userQuestionSubmissionMapper::toDTO).collect(Collectors.toList()));
            examQuestionSubmissionsByTypes.add(s);
        });
//        System.out.println(examQuestionSubmissionsByTypes);
        return  examQuestionSubmissionsByTypes;
    }



}

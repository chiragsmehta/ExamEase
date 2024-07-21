package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.model.*;
import com.roima.exammanagement.repository.*;
import com.roima.exammanagement.rest.v1.dto.*;
import com.roima.exammanagement.rest.v1.mapper.*;
import com.roima.exammanagement.rest.v1.mapper.simple.SimpleExamMapper;
import com.roima.exammanagement.rest.v1.mapper.simple.SimpleQuestionTypeMapper;
import com.roima.exammanagement.rest.v1.mapper.simple.SimpleUserMapper;
import com.roima.exammanagement.rest.v1.service.utilitymodels.UserExamSummary;
import com.sipios.springsearch.anotation.SearchSpec;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.server.MethodNotAllowedException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;
    private final UserRepository userRepository;
    private final ExamEnrollmentRepository examEnrollmentRepository;
    private final QuestionRepository questionRepository;
    private final UserExamStatusRepository userExamStatusRepository;
    private final QuestionMapper questionMapper;
    private final UserService userService;
    private final UserMapper userMapper;
    private final OptionRepository optionRepository;
    private final UserQuestionSubmissionRepository userQuestionSubmissionRepository;
    private final UserQuestionSubmissionMapper userQuestionSubmissionMapper;
    private final UserExamStatusMapper userExamStatusMapper;
    private final SimpleExamMapper simpleExamMapper;
    private final SimpleUserMapper simpleUserMapper;
    private final QuestionTypeRepository questionTypeRepository;
    private final SimpleQuestionTypeMapper simpleQuestionTypeMapper;

    public ExamDTO findExamById(Long id) throws ChangeSetPersister.NotFoundException {
        Exam exam = examRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        try{
//            System.out.println(exam.getQuestions().size());
        }catch (Exception e){
        }
        return examMapper.toDTO(exam);
    }

    public List<ExamDTO> findBySpec(@SearchSpec Specification<Exam> specs){
        List<Exam> exams = examRepository.findAll(specs);
        return exams.stream().map(examMapper::toDTO).collect(Collectors.toList());
    }

    public Boolean deleteExam(@NonNull Long examId){
        Exam exam = examRepository.findById(examId).orElseThrow(NoSuchElementException::new);
        if(exam.getStartDateTime().isBefore(LocalDateTime.now()) && exam.getIsActive()){
            throw new UnsupportedOperationException("Exam started so can not delete now");
        }
        examRepository.deleteById(examId);
        return  true;
    }

    public Boolean assignExamToUserById(@NonNull Long userId, @NonNull Long examId){
        try{
            Exam exam = examRepository.findById(examId).orElse(null);
            User user = userRepository.findById(userId).orElse(null);
            User admin = null;
            try{
               admin= userMapper.toEntity(userService.getCurrentUser());
            }catch (Exception e){
//                System.out.println("NO ADMIN");
            }
            ExamEnrollment examEnrollment = new ExamEnrollment(user,exam,null,admin,LocalDateTime.now(),admin,LocalDateTime.now(),true);
            examEnrollmentRepository.save(examEnrollment);
//            UserExamStatus userExamStatus = UserExamStatus.builder()
//                    .examEnrollment(examEnrollment)
//                    .hasSubmitted(false)
//                    .remainingDuration(exam.getDuration())
//                    .build();
//            userExamStatusRepository.save(userExamStatus);
//            examEnrollment.setUserExamStatus(userExamStatus);
            examEnrollmentRepository.save(examEnrollment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean removeUsersFromExam(@NonNull Long userId, @NonNull Long examId){
        try{
            Exam exam = examRepository.findById(examId).orElse(null);
            User user = userRepository.findById(userId).orElse(null);
            ExamEnrollment examEnrollment = examEnrollmentRepository.findByUserAndExam(user,exam);
            UserExamStatus userExamStatus = userExamStatusRepository.findByExamEnrollment(examEnrollment);
            examEnrollmentRepository.delete(examEnrollment);
            userExamStatusRepository.delete(userExamStatus);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean save(@NonNull ExamDTO examDTO) {
//        System.out.println(examDTO);
        User admin = null;
        try{
            admin = userMapper.toEntity(userService.getCurrentUser());
        }catch (Exception e){
//            System.out.println("NO ADMIN");
        }
        Exam exam = examMapper.toEntity(examDTO);
        try{
            exam.setIsActive(false);
            exam.setCreatedBy(admin);
            exam.setUpdatedBy(admin);
            exam.setCreatedAt(LocalDateTime.now());
            exam.setUpdatedAt(LocalDateTime.now());
            examRepository.save(exam);

            return true;
        }catch (Exception e){
            return false;
        }
    }


    public ExamDTO update(ExamDTO examDTO) {
//        System.out.println("Update request received.");
        Exam exam = examRepository.findById(examDTO.getId()).orElse(null);
        if(exam == null){
            throw new NoSuchElementException("Question Type does not exist");
        }
        examMapper.updateSourceFromTarget(examDTO,exam);
        User admin = null;
        try{
            admin = userMapper.toEntity(userService.getCurrentUser());
        }catch (Exception e){
//            System.out.println("NO ADMIN");
        }
        if(exam.getCurrentMarks() == exam.getTotalMarks()){
            exam.setIsActive(true);
        }else {
            exam.setIsActive(false);
        }
        exam.setUpdatedAt(LocalDateTime.now());
        exam.setUpdatedBy(admin);
        examRepository.save(exam);
        return examMapper.toDTO(exam);
    }

    public List<QuestionDTO> findQuestionByExamId(@NonNull Long examId){
        List<Question> questions = questionRepository.findByExamId(examId);
        return questions.stream().map(questionMapper::toDTO).collect(Collectors.toList());
    }

    public List<QuestionDTO> findQuestionByExamIdWithoutAnswers(@NonNull Long examId){
        List<Question> questions = questionRepository.findByExamId(examId);
        questions.forEach(question -> {
            question.setAnswer("");
            question.getOptions().forEach(option -> {
                option.setIsCorrect(false);
            });
            question.setCorrectOptions(new ArrayList<>());
        });
        return questions.stream().map(questionMapper::toDTO).collect(Collectors.toList());
    }
    public Boolean removeQuestionFromExam(Long examId,Long questionId){
        try{
            Exam exam = examRepository.findById(examId).orElseThrow(NoSuchElementException::new);
            Question question = questionRepository.findById(questionId).orElseThrow(NoSuchElementException::new);
            exam.getQuestions().remove(question);
            exam.setIsActive(exam.getCurrentMarks() >= exam.getTotalMarks());
            examRepository.save(exam);
            return  true;
        }catch (Exception e){
            return false;
        }
    }

    public List<UserDTO> findUsersUnEnrolledByExamId(Long examID){
        Exam exam = examRepository.findById(examID).orElse(null);
        List<User> users = userRepository.findAllNotInExamEnrollment(exam);
        return  users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    public  List<ExamDTO> findCurrentUserExams(){
        User user = userMapper.toEntity(userService.getCurrentUser());
//        System.out.println(user.getEmail());
        return examRepository.findByUser(user).stream().map(examMapper::toDTO).collect(Collectors.toList());
    }

    public Boolean submitQuestion(UserQuestionSubmissionDTO userQuestionSubmissionDTO){
//        Exam exam = examRepository.findById(userQuestionSubmissionDTO.getExam().getId()).orElse(null);
//        Question question = questionRepository.findById(userQuestionSubmissionDTO.getQuestion().getId()).orElse(null);
//        Option option;
//        if(question!=null && question.getQuestionType().getIsOptionRequired()){
//            option = optionRepository.findById((userQuestionSubmissionDTO.getOption().getId())).orElse(null);
//        }else{
//            option = null;
//        }
//        User user = userMapper.toEntity(userService.getCurrentUser());
//        UserQuestionSubmission userQuestionSubmission = UserQuestionSubmission.builder()
//                .option(option)
//                .question(question)
//                .user(user)
//                .exam(exam)
//                .answer(userQuestionSubmissionDTO.getAnswer()).build();
        try{
            UserDTO userDTO = userService.getCurrentUser();
            UserQuestionSubmission userQuestionSubmission = userQuestionSubmissionRepository.findByUserAndExamAndQuestion(userDTO.getId(),
                    userQuestionSubmissionDTO.getExam().getId(),
                    userQuestionSubmissionDTO.getQuestion().getId());

            if(userQuestionSubmission == null){
                userQuestionSubmission = userQuestionSubmissionMapper.toEntity(userQuestionSubmissionDTO);
                int questionTypeMarks = questionRepository.findById(userQuestionSubmissionDTO.getQuestion().getId())
                        .orElse(null)
                        .getQuestionType().getMarks();

               if(userQuestionSubmission.getOption() != null){


                   Option sOption = optionRepository.findById(userQuestionSubmission.getOption().getId()).orElse(null);
                   userQuestionSubmission.setIsChecked(true);
                   if(sOption.getIsCorrect()){
                       userQuestionSubmission.setGainedMarks(questionTypeMarks);
                   }else{
                       userQuestionSubmission.setGainedMarks(0);
                   }
               }else{
                   userQuestionSubmission.setIsChecked(false);
               }

                userQuestionSubmission.setUser(userMapper.toEntity(userDTO));
            }else{
                if(userQuestionSubmissionDTO.getOption()!=null){
                    Option selectedOption = optionRepository.findById(userQuestionSubmissionDTO.getOption()
                            .getId()).orElse(null);

                    userQuestionSubmission.setOption(selectedOption);
                    userQuestionSubmission.setIsChecked(true);
                    if (userQuestionSubmission.getOption().getIsCorrect()) {
                        userQuestionSubmission.setGainedMarks(userQuestionSubmission.getQuestion()
                                .getQuestionType().getMarks());
                    } else {
                        userQuestionSubmission.setGainedMarks(0);
                    }
                }else{
                    userQuestionSubmission.setIsChecked(false);
                    userQuestionSubmission.setAnswer(userQuestionSubmissionDTO.getAnswer());

                }
            }
            userQuestionSubmissionRepository.save(userQuestionSubmission);
            return  true;
        }catch (Exception e){
//            System.out.println(e.toString());
            return false;
        }
    }
    public Boolean areAllQuestionsChecked(Long userId,Long examId){
        List<UserQuestionSubmissionDTO>  userQuestionSubmissionDTOS =
                userQuestionSubmissionRepository.findByExamAndUser(userId,examId)
                        .stream().map(userQuestionSubmissionMapper::toDTO).collect(Collectors.toList());
        UserQuestionSubmissionDTO unChecked =  userQuestionSubmissionDTOS.stream().filter(sub-> sub.getIsChecked().equals(false)).
                findFirst().orElse(null);
        return unChecked == null;
    }

    public UserExamSummary getUserExamSummary(Long userId,Long examId){
        List<UserQuestionSubmission> userQuestionSubmissions = userQuestionSubmissionRepository
                .findByExamAndUser(examId,userId);
        Exam exam = examRepository.findById(examId).orElseThrow(NoSuchElementException::new);
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        ExamEnrollment examEnrollment = examEnrollmentRepository
                .findByUserAndExam(user, exam);
        UserExamSummary userExamSummary = UserExamSummary.builder()
                .userExamStatus(userExamStatusMapper
                        .toDTO(userExamStatusRepository.findByExamEnrollment(examEnrollment)))
                .exam(simpleExamMapper.toDTO(exam))
                .user(simpleUserMapper.toDTO(user))
                .totalGainedMarks(userQuestionSubmissionRepository.getTotalMarks(userId,examId))
                .totalMarksByQuestionType(new HashMap<>())
                .areAllQuestionsChecked(areAllQuestionsChecked(userId,examId))
                .build();
        List<QuestionType> questionTypes = questionTypeRepository.findAll();
        questionTypes.forEach(type->{
            userExamSummary.getTotalMarksByQuestionType()
                    .put(type.getName(),userQuestionSubmissionRepository.getTotalMarksByQuestionType(userId,examId,type.getId()));
        });
        return userExamSummary;
    }
    public List<UserExamSummary> findAllUserExamSummary(Long examId){
        List<UserExamSummary> userExamSummaries = new ArrayList<>();
        List<User> users = userRepository.findAllByExamId(examId);
        users.forEach(user -> {
            userExamSummaries.add(getUserExamSummary(user.getId(),examId));
        });
        return  userExamSummaries;
    }
    public UserExamSummary getIndividualUserExamSummary(Long examId){
        Long userId = userService.getCurrentUser().getId();
        List<UserQuestionSubmission> userQuestionSubmissions = userQuestionSubmissionRepository
                .findByExamAndUser(examId,userId);
        Exam exam = examRepository.findById(examId).orElseThrow(NoSuchElementException::new);
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        ExamEnrollment examEnrollment = examEnrollmentRepository
                .findByUserAndExam(user, exam);
        UserExamStatus userExamStatus = userExamStatusRepository.findByExamEnrollment(examEnrollment);
        if(userExamStatus == null){
            throw  new NoSuchElementException();
        }
        UserExamSummary userExamSummary = UserExamSummary.builder()
                .userExamStatus(userExamStatusMapper
                        .toDTO(userExamStatusRepository.findByExamEnrollment(examEnrollment)))
                .exam(simpleExamMapper.toDTO(exam))
                .user(simpleUserMapper.toDTO(user))
                .totalGainedMarks(userQuestionSubmissionRepository.getTotalMarks(userId,examId))
                .totalMarksByQuestionType(new HashMap<>())
                .areAllQuestionsChecked(areAllQuestionsChecked(userId,examId))
                .build();
        List<QuestionType> questionTypes = questionTypeRepository.findAll();
        questionTypes.forEach(type->{
            userExamSummary.getTotalMarksByQuestionType()
                    .put(type.getName(),userQuestionSubmissionRepository.getTotalMarksByQuestionType(userId,examId,type.getId()));
        });
        return userExamSummary;
    }
}

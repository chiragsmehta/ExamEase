package com.roima.exammanagement.rest.v1.controller.admin;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.roima.exammanagement.model.*;
import com.roima.exammanagement.rest.v1.dto.*;
import com.roima.exammanagement.rest.v1.service.*;
import com.roima.exammanagement.rest.v1.service.utilitymodels.ExamQuestionSubmissionsByType;
import com.roima.exammanagement.rest.v1.service.utilitymodels.ExamQuestionsByType;
import com.roima.exammanagement.rest.v1.service.utilitymodels.UserExamSummary;
import com.sipios.springsearch.anotation.SearchSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/admin/exam")
@RequiredArgsConstructor
@CrossOrigin
public class ExamController {

    private final ExamService examService;
    private final UserService userService;
    private final QuestionService questionService;
    private final UserQuestionSubmissionService userQuestionSubmissionService;
    private  final UserExamStatusService userExamStatusService;

    @PostMapping("")
    public ResponseEntity<Boolean> saveExam(@RequestBody ExamDTO examDTO){
        return new ResponseEntity<>(examService.save(examDTO), HttpStatus.OK);
    }

    @PostMapping("/{exam_id}/add-questions")
    public ResponseEntity<Boolean> test(@RequestBody QuestionsAddingRequest questionsAddingRequest,
                                        @PathVariable("exam_id") Long examId){
        Boolean done = questionService.assignQuestionsToExam(questionsAddingRequest,examId);
        if(done){
            return ResponseEntity.ok(done);
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @PutMapping("/{exam_id}")
    public ResponseEntity<ExamDTO> updateExam(@RequestBody ExamDTO examDTO){

        return new ResponseEntity<>(examService.update(examDTO), HttpStatus.OK);
    }

//    @GetMapping("/{exam_id}/marks")
//    public ResponseEntity<>
    @GetMapping("")
    public ResponseEntity<List<ExamDTO>> find(@SearchSpec Specification<Exam> specs){
        return ResponseEntity.ok(examService.findBySpec(specs));
    }

    @GetMapping("/{exam_id}")
    public ResponseEntity<ExamDTO> findById(@PathVariable("exam_id") Long id){
        try {
            return ResponseEntity.ok(examService.findExamById(id));
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/{exam_id}/assign-user/{user_id}")
    public ResponseEntity<Boolean> assignUserToExam(@PathVariable("exam_id") Long examId,@PathVariable("user_id") Long userId){
        return new ResponseEntity<>(examService.assignExamToUserById(userId,examId),HttpStatus.CREATED);
    }

    @DeleteMapping("/{exam_id}/assign-user/{user_id}")
    public ResponseEntity<Boolean> removeUserFromExam(@PathVariable("exam_id") Long examId,@PathVariable("user_id") Long userId){
        return new ResponseEntity<>(examService.removeUsersFromExam(userId,examId),HttpStatus.OK);
    }
    @DeleteMapping("/{exam_id}")
    public ResponseEntity<Boolean> deleteExam(@PathVariable("exam_id") Long examId){
       try {
           return new ResponseEntity<>(examService.deleteExam(examId),HttpStatus.OK);
       }catch (Exception e){
           return ResponseEntity.badRequest().build();
       }
    }

    @GetMapping("/{exam_id}/users")
    public ResponseEntity<List<UserDTO>> findUserByExamId(@PathVariable("exam_id") Long examId){
        return new ResponseEntity<List<UserDTO>>(userService.findByExamId(examId),HttpStatus.OK);
    }

    @GetMapping("/{exam_id}/questions")
    public ResponseEntity<List<QuestionDTO>> findQuestionsByExamId(@PathVariable("exam_id") Long examId){
        return new ResponseEntity<>(examService.findQuestionByExamId(examId),HttpStatus.OK);
    }

    @GetMapping("/{exam_id}/questions-types")
    public ResponseEntity<List<ExamQuestionsByType>> findExamQuestionsGroupByType(@PathVariable("exam_id") Long examId){
        return ResponseEntity.ok(questionService.findExamQuestionsGroupedByType(examId));
    }
    @GetMapping("/{exam_id}/questions-types/without-answers")
    public ResponseEntity<List<ExamQuestionsByType>> findExamQuestionsGroupByTypeWithoutAnswers(@PathVariable("exam_id") Long examId){
        return ResponseEntity.ok(questionService.findExamQuestionsGroupedByTypeWithoutAnswers(examId));
    }

    @PostMapping("/{exam_id}/remove-question/{question_id}")
    public ResponseEntity<Boolean> removeQuestionFromExam(@PathVariable("exam_id") Long examId,
                                                          @PathVariable("question_id") Long questionId){
        Boolean isRemoved = examService.removeQuestionFromExam(examId,questionId);
        if(isRemoved){
            return ResponseEntity.ok(true);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{exam_id}/unassigned-users")
    public ResponseEntity<List<UserDTO>> getUsersNotEnrolledByExam(@PathVariable("exam_id") Long examId){
        return ResponseEntity.ok(examService.findUsersUnEnrolledByExamId(examId));
    }

    @GetMapping("/{exam_id}/user-submissions/{user_id}")
    public ResponseEntity<List<ExamQuestionSubmissionsByType>> getUserExamSubmissions(@PathVariable("exam_id") Long examId,
                                                                                      @PathVariable("user_id") Long userId){
        return  ResponseEntity.ok(userQuestionSubmissionService.findAllFromExamAndUserByQuestionType(userId,
                examId));
    }


    @GetMapping("/{exam_id}/user-exam-status/{user_id}")
    public  ResponseEntity<UserExamStatusDTO> getUserExamStatusByUserAndExam(@PathVariable("user_id") Long userId,
                                                                             @PathVariable("exam_id") Long examId){
        try{
            return ResponseEntity.ok(userExamStatusService.findByUserAndExamId(userId,examId));
        } catch (Exception e){

            return  ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{exam_id}/user-exam-status/{user_id}/are-all-questions-checked")
    public  ResponseEntity<Boolean> getAreAllQuestionsChecked(@PathVariable("user_id") Long userId,
                                                              @PathVariable("exam_id") Long examId){
        try{
            return ResponseEntity.ok(examService.areAllQuestionsChecked(userId,examId));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{exam_id}/user-exam-status/{user_id}/summary")
    public  ResponseEntity<UserExamSummary> getUserExamSummary(@PathVariable("user_id") Long userId,
                                                          @PathVariable("exam_id") Long examId){
        try{
            UserExamSummary userExamSummary = examService.getUserExamSummary(userId,examId);
            return ResponseEntity.ok(userExamSummary);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{exam_id}/results")
    public ResponseEntity<List<UserExamSummary>> getResults( @PathVariable("exam_id") Long examId){
        try{
            return ResponseEntity.ok(examService.findAllUserExamSummary(examId));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }



}

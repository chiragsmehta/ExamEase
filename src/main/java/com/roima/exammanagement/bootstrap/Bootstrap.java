package com.roima.exammanagement.bootstrap;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.model.Question;
import com.roima.exammanagement.model.Role;
import com.roima.exammanagement.model.User;
import com.roima.exammanagement.repository.UserRepository;
import com.roima.exammanagement.rest.v1.dto.*;
import com.roima.exammanagement.rest.v1.service.*;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.management.BadAttributeValueExpException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
class Bootstrap implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final QuestionCategoryService questionCategoryService;
    private final QuestionTypeService questionTypeService;
    private final ExamService examService;
    private final UserService userService;
    private final QuestionService questionService;

    @Override
    public void run(String... args) throws Exception {
//        User admin = User.builder()
//                .name("admin")
//                .email("admin@admin.com")
//                .password(passwordEncoder.encode("admin"))
//                .role(Role.ADMIN)
//                .build();
        User admin = new User();
        admin.setName("admin");
        admin.setEmail("admin@admin.com");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRole(Role.ADMIN);
        if (userRepository.findByEmail(admin.getEmail()).orElse(null) == null) {
            userRepository.save(admin);
        } else {
//            System.out.println("Already Exists");
        }


//        User shubham = new User();
//        shubham.setRole(Role.USER);
//        shubham.setEmail("shubham@gmail.com");
//        shubham.setPassword(passwordEncoder.encode("shubham"));
//        shubham.setName("Shubham Jani");
//        shubham.setCreatedBy(admin);
//
//        shubham.setIsActive(true);
//        if (userRepository.findByEmail(shubham.getEmail()).orElse(null) == null) {
//            userRepository.save(shubham);
//        } else {
////            System.out.println("Already Exists");
//        }
//
//        QuestionCategoryDTO questionCategoryDTO = new QuestionCategoryDTO();
//        questionCategoryDTO.setName("Computer Networks");
//        questionCategoryService.save(questionCategoryDTO);
//
//        QuestionCategoryDTO dsa = new QuestionCategoryDTO();
//        dsa.setName("DSA");
//        questionCategoryService.save(dsa);
//
//
//
//        QuestionTypeDTO questionTypeDTO = new QuestionTypeDTO();
//        questionTypeDTO.setName("mcq");
//        questionTypeDTO.setMarks(1);
//        questionTypeDTO.setIsOptionRequired(true);
//        questionTypeService.save(questionTypeDTO);
//
//        QuestionTypeDTO program = new QuestionTypeDTO();
//        program.setIsOptionRequired(false);
//        program.setName("program");
//        program.setMarks(15);
//        questionTypeService.save(program);
//
//        ExamDTO exam = new ExamDTO();
//        exam.setName("test");
//        exam.setInstructions("noice");
//        exam.setDuration(Duration.ofHours(12));
//        exam.setStartDateTime(LocalDateTime.now());
//        exam.setEndDateTime(LocalDateTime.now().plusDays(3));
//        exam.setTotalMarks(100);
//        examService.save(exam);
    }
}

package com.roima.exammanagement.bootstrap;

import com.roima.exammanagement.api.v1.mapper.UserMapper;
import com.roima.exammanagement.model.Exam;
import com.roima.exammanagement.model.ExamEnrollment;
import com.roima.exammanagement.model.Role;
import com.roima.exammanagement.model.User;
import com.roima.exammanagement.repository.ExamEnrollementRepository;
import com.roima.exammanagement.repository.ExamRepository;
import com.roima.exammanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class Bootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ExamRepository examRepository;
    private final ExamEnrollementRepository examEnrollementRepository;
    private final UserMapper userMapper;

    public Bootstrap(UserRepository userRepository, ExamRepository examRepository, ExamEnrollementRepository examEnrollementRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.examRepository = examRepository;
        this.examEnrollementRepository = examEnrollementRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void run(String... args) throws Exception {

        List<User> users = new ArrayList<>();

        users.add(new User("shubham", "hehe@gmail.com", "NONONO", false, Role.USER, new ArrayList<>()));
//        users.add(new User("john_doe", "john.doe@example.com", "P@ssw0rd", true, new ArrayList<>()));
//        users.add(new User("alice_smith", "alice.smith@example.com", "Secret123", false, new ArrayList<>()));
//        users.add(new User("bob_jackson", "bob.jackson@example.com", "SecurePwd", true, new ArrayList<>()));
//        users.add(new User("emma_jones", "emma.jones@example.com", "12345678", false, new ArrayList<>()));
//        users.add(new User("michael_lee", "michael.lee@example.com", "Pass123!", true, new ArrayList<>()));
//        users.add(new User("olivia_martin", "olivia.martin@example.com", "StrongPwd", false, new ArrayList<>()));
//        users.add(new User("david_williams", "david.williams@example.com", "MyPass", true, new ArrayList<>()));
//        users.add(new User("sophia_thomas", "sophia.thomas@example.com", "SecurePass123", false, new ArrayList<>()));
//        users.add(new User("samuel_brown", "samuel.brown@example.com", "Password123", true, new ArrayList<>()));


        Exam exam = new Exam("final", LocalDateTime.now(),LocalDateTime.now(), Duration.ofHours(2),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        System.out.println();
        examRepository.save(exam);
        userRepository.saveAll(users);
        ExamEnrollment examEnrollment = new ExamEnrollment(userRepository.findById(1L).orElse(null),exam,false,Duration.ofHours(1));
        examEnrollementRepository.save(examEnrollment);
//        System.out.println(userRepository.findById(1L).orElse(null));
        System.out.println(userMapper.userToUserDTO(userRepository.findById(1L).orElse(null)));

    }
}
